package org.educa;

import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        String[] pilotos={"Hamilton", "Vettel", "Raikkonen", "Alonso", "Sainz", "Bottas"};
        for (String piloto : pilotos){
            MiHilo hilo = new MiHilo(piloto);
            hilo.start();
        }
    }
}
