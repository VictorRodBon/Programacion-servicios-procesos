package Hilos;

import VariablesCompartidas.Estanteria;
import java.util.concurrent.Semaphore;

public class HiloCamarero extends Thread {
    private Estanteria estanteria = null;
    private Semaphore semaphore; // semaphoreCamarero (controla pasteles)
    private Semaphore semaphoreEstanteria; // Mutex
    private Semaphore semaphoreCocinero; // Controla huecos

    public HiloCamarero(Estanteria estanteria, Semaphore semaphore, Semaphore semaphoreEstanteria, Semaphore semaphoreCocinero) {
        this.estanteria = estanteria;
        this.semaphore = semaphore;
        this.semaphoreEstanteria = semaphoreEstanteria;
        this.semaphoreCocinero = semaphoreCocinero;
    }

    public void run() {
        // Condición para terminar cuando se hayan vendido las 12 tartas
        while (estanteria.getServidos() < 12) {
            try {
                semaphore.acquire();

                if (estanteria.getServidos() >= 12) {
                    semaphore.release();
                    break;
                }

                semaphoreEstanteria.acquire();

                estanteria.setServidos(estanteria.getServidos() + 1);
                estanteria.setPasteles(estanteria.getPasteles() - 1);
                System.out.println("nuevo pastel vendido (" + estanteria.getServidos() + ")");

                semaphoreEstanteria.release();

                semaphoreCocinero.release();

            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}