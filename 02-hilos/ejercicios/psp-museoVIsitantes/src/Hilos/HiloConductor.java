package Hilos;

import VariablesCompartidas.TiempoMedio;

import java.util.concurrent.Semaphore;

public class HiloConductor extends Thread{
    public Semaphore conductor = null;
    Semaphore espera = null;
    TiempoMedio tm = null;

    public HiloConductor(Semaphore conductor) {
        this.conductor = conductor;
    }

    public HiloConductor(Semaphore conductor, Semaphore espera, TiempoMedio tm){
        this.conductor = conductor;
        this.espera = espera;
        this.tm=tm;
    }

    public void run(){
        try {
            conductor.acquire();

            for(int i =0; i<5; i++){
                espera.release();
            }

            System.out.println("Llegado al destino");

        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
