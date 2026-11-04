package org.educa;

public class HiloVariableComun extends Thread{
    Contador c=null;

    public HiloVariableComun(Contador c){
        this.c=c;
    }

    @Override
    public void run() {
        System.out.println("Antes: " + c.numero + " " + Thread.currentThread());
        c.numero++;
        System.out.println("Despues: " + c.numero + " " + Thread.currentThread());
    }
}
