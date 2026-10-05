package org.educa;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore puerta = new Semaphore(1);
        Semaphore aforo = new Semaphore(2);

        Visitante v1 = new Visitante("Victor");
        Visitante v2 = new Visitante("Sergio");
        Visitante v3 = new Visitante("Pablo");
        Visitante v4 = new Visitante("Marcos");

        HiloCompartido h1 = new HiloCompartido(v1, puerta, aforo);
        HiloCompartido h2 = new HiloCompartido(v2, puerta, aforo);
        HiloCompartido h3 = new HiloCompartido(v3, puerta, aforo);
        HiloCompartido h4 = new HiloCompartido(v4, puerta, aforo);

        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
