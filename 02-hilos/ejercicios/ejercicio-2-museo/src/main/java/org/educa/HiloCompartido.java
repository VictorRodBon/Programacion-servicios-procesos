package org.educa;

import java.util.concurrent.Semaphore;

public class HiloCompartido extends Thread{
    Visitante v;
    Semaphore puerta;
    Semaphore aforo;

    public HiloCompartido(Visitante v, Semaphore puerta, Semaphore aforo){
        this.v = v;
        this.puerta = puerta;
        this.aforo = aforo;
    }

    @Override
    public void run() {
        long tiempo=(long) (Math.random() * 1000);
        try {
            aforo.acquire();
            puerta.acquire();
            System.out.println("Entro al museo (" + v.getNombre() + ")");
            puerta.release();

            Thread.sleep(tiempo);
            v.setTiempo(tiempo);

            puerta.acquire();
            System.out.println("Salgo del museo (" + v.getNombre() + ")");
            puerta.release();
            aforo.release();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
