package Hilos;

import VariablesCompartidas.TiempoMedio;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class HiloVisitante extends Thread{

    Semaphore mutex = null; //semaforo para entrar o no al museo
    TiempoMedio tiempoMedio = null; //variable compartida
    Semaphore vc = null; //semaforo para modificar la variable compartida
    Semaphore aforo = null; //semaforo para aforo

    public HiloVisitante(Semaphore mutex, TiempoMedio tiempoMedio, Semaphore aforo,
                         Semaphore vc){
        this.mutex = mutex;
        this.tiempoMedio = tiempoMedio;
        this.aforo = aforo;
        this.vc = vc;
    }

    public void run(){
        try {
            mutex.acquire();//llega a la puerta
            mutex.release();//entra al museo

            aforo.acquire();//comprueba el aforo y entra cuando pueda

            System.out.println("Voy a entrar al museo "+Thread.currentThread());

            if (aforo.availablePermits()==0){
                System.out.println("Soy el ultimo en entrar"+Thread.currentThread());
            }

            System.out.println("Esta visitando el museo "+Thread.currentThread());
            Random rd = new Random();
            int tiempo = (rd.nextInt(1,6)*1000); // tiempo que esta ejecutando en el museo
            Thread.sleep((long)tiempo);
            System.out.println("Tiempo que ha estado visitando el museo : "+tiempo/1000);
            vc.acquire(); //accede a la variable compartida (sala importante del museo)
            tiempoMedio.setContador(tiempoMedio.getContador()+1);
            tiempoMedio.setTiempoMedio(tiempoMedio.getTiempoMedio() + tiempo) ;
            vc.release(); //sale de la sala importante (solo podia entrar uno)

            aforo.release(); //renueva el aforo


            mutex.acquire(); // llega a la puerta de salida
                System.out.println("Sale del museo"+Thread.currentThread());
            mutex.release(); //sale del museo
        } catch (InterruptedException e) {
            System.err.println("Acceso denegado al museo");
            throw new RuntimeException(e);
        }
    }

}
