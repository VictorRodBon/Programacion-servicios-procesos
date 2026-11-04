package org.educa;

import java.util.concurrent.Semaphore;

public class HiloVariableComun extends Thread{

    Contador c=null;
    Semaphore mutex =null;

    public HiloVariableComun(Contador c, Semaphore mutex){
        this.c=c;
        this.mutex=mutex;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            System.out.println("Antes: " + c.numero + " " + Thread.currentThread());
            c.numero++;
            System.out.println("Despues: " + c.numero + " " + Thread.currentThread());
            mutex.release();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
