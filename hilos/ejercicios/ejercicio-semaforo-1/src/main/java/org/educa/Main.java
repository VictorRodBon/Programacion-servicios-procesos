package org.educa;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1);

        Contador c = new Contador();

        HiloVariableComun h1 = new HiloVariableComun(c, mutex);
        HiloVariableComun h2 = new HiloVariableComun(c, mutex);

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
