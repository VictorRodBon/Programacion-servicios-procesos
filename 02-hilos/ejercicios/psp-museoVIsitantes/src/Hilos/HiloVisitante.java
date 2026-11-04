package Hilos;

import VariablesCompartidas.TiempoMedio;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class HiloVisitante extends Thread{

    Semaphore mutex = null; //semaforo para entrar o no al museo
    TiempoMedio tiempoMedio = null; //variable compartida
    Semaphore vc = null; //semaforo para modificar la variable compartida
    Semaphore aforo = null; //semaforo para aforo
    Semaphore espera = null;
    Semaphore conductor;

    public HiloVisitante(Semaphore mutex, TiempoMedio tiempoMedio, Semaphore aforo, Semaphore vc, Semaphore espera, Semaphore conductor){
        this.mutex = mutex;
        this.tiempoMedio = tiempoMedio;
        this.aforo = aforo;
        this.vc = vc;
        this.espera = espera;
        this.conductor = conductor;
    }

    public void run(){
        try {
            aforo.acquire();//comprueba el aforo y entra cuando pueda

            mutex.acquire();//llega a la puerta
            mutex.release();//entra al museo

            System.out.println("Voy a entrar al museo "+Thread.currentThread());

            if (aforo.availablePermits()==0){
                System.out.println("Soy el ultimo en entrar"+Thread.currentThread());
            }

            System.out.println("Esta visitando el museo "+Thread.currentThread());
            int tiempo =1;
            //Random rd = new Random();
            //tiempo = (rd.nextInt(1,6)*1000); // tiempo que esta ejecutando en el museo
            //Thread.sleep((long)tiempo);
            //System.out.println("Tiempo que ha estado visitando el museo : "+tiempo/1000);
            vc.acquire(); //accede a la variable compartida (sala importante del museo)
            tiempoMedio.setContador(tiempoMedio.getContador()+1);
            tiempoMedio.setTiempoMedio(tiempoMedio.getTiempoMedio() + tiempo) ;
            vc.release(); //sale de la sala importante (solo podia entrar uno)

            mutex.acquire(); // llega a la puerta de salida
            System.out.println("Sale del museo"+Thread.currentThread());
            mutex.release(); //sale del museo

            aforo.release(); //renueva el aforo

            //el ultimo despierta al conductor
            if(tiempoMedio.getContador()==5){
                conductor.release();
            }


            espera.acquire();

            System.out.println("llegado a casa");
        } catch (InterruptedException e) {
            System.err.println("Acceso denegado al museo");
            throw new RuntimeException(e);
        }
    }

}
