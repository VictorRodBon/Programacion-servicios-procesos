package org.educa;

public class Main {
    public static void main(String[] args) {
        Contador c = new Contador();

        HiloVariableComun h1 = new HiloVariableComun(c);
        HiloVariableComun h2 = new HiloVariableComun(c);

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("fin de programa");
    }
}
