package org.educa;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Inicialización correcta de las listas
        ArrayList<Integer> pares = new ArrayList<>();
        ArrayList<Integer> impares = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                pares.add(i);
            } else {
                impares.add(i);
            }
        }

        MiHilo h1 = new MiHilo(pares);
        MiHilo h2 = new MiHilo(impares);

        h1.start();
        h2.start();
    }
}