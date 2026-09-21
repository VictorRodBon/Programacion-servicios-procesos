package org.educa;

public class Main {
    public static void main(String[] args) {


        for (int i=0; i<15; i++){
            MiHilo hs = new MiHilo(""+i);
            hs.start();
        }

        System.out.println("hola");
    }
}
