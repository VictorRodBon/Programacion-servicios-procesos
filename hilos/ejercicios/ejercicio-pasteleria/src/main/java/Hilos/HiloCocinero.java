package Hilos;

import VariablesCompartidas.Estanteria;
import java.util.concurrent.Semaphore;

public class HiloCocinero extends Thread {
    private Estanteria estanteria = null;
    private Semaphore semaphore; // semaphoreCocinero (controla huecos)
    private Semaphore semaphoreEstanteria; // Mutex
    private Semaphore semaphoreCamarero; // Controla pasteles
    private int cocinados = 0;

    public HiloCocinero(Estanteria estanteria, Semaphore semaphore, Semaphore semaphoreEstanteria, Semaphore semaphoreCamarero) {
        this.estanteria = estanteria;
        this.semaphore = semaphore;
        this.semaphoreEstanteria = semaphoreEstanteria;
        this.semaphoreCamarero = semaphoreCamarero;
    }

    public void run() {
        while (cocinados < 12) {
            try {
                // 1. Esperar a que haya un hueco libre
                semaphore.acquire();

                // 2. Sección crítica
                semaphoreEstanteria.acquire();

                estanteria.setPasteles(estanteria.getPasteles() + 1);
                System.out.println("nuevo pastel en estanteria ("+estanteria.getPasteles()+")");
                cocinados++;

                semaphoreEstanteria.release();

                // 3. Avisar al camarero de que hay un nuevo pastel (SOLO UNA VEZ)
                semaphoreCamarero.release();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}