package org.educa;

import java.util.concurrent.Semaphore;

public class HiloCompartido extends Thread{
    Visitante v = null;
    Semaphore puerta = null;
    Semaphore aforo = null;

    public HiloCompartido(Visitante v, Semaphore puerta, Semaphore aforo){
        this.v = v;
        this.puerta=puerta;
        this.aforo=aforo;
    }

    @Override
    public void run() {
        try {
            aforo.acquire();
            puerta.acquire();
            System.out.println("entro al museo ("+v.nombre+")");
            puerta.release();

            Thread.sleep((long) (Math.random()*1000));

            puerta.acquire();
            System.out.println("salgo del museo ("+v.nombre+")");
            puerta.release();
            aforo.release();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
