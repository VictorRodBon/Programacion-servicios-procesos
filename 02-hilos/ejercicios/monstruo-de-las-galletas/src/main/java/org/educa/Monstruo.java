package org.educa;

import java.util.concurrent.Semaphore;

public class Monstruo extends Thread {
    Semaphore almacenSemaphore;
    Semaphore almaceneroSemaphore;
    Semaphore monstruoSemaphore;
    BeamAlmacen almacen;
    int galletas;

    public Monstruo(Semaphore almacenSemaphore, Semaphore almaceneroSemaphore, Semaphore monstruoSemaphore, BeamAlmacen almacen, int galletas) {
        this.almacenSemaphore = almacenSemaphore;
        this.almaceneroSemaphore = almaceneroSemaphore;
        this.monstruoSemaphore = monstruoSemaphore;
        this.almacen = almacen;
        this.galletas = galletas;
    }

    @Override
    public void run() {
        try {
            while (almacen.repuesto < 10) {
                almacenSemaphore.acquire(); // Entrar a la sección crítica (Mutex)

                // Si no hay suficientes galletas para este monstruo
                if (almacen.galletas < galletas) {
                    System.out.println(getName() + " - No hay suficientes galletas (Quedan " + almacen.galletas + ", necesito " + galletas + "). Me bloqueo.");
                    almacen.galletas=0;

                    almaceneroSemaphore.release(); // Avisar al almacenero que necesitamos galletas
                    almacenSemaphore.release();    // IMPORTANTE: Liberar el mutex del almacén para que el almacenero pueda trabajar

                    monstruoSemaphore.acquire();   // Esperar a que el almacenero termine de reponer

                    continue; // Vuelve al inicio del while para reintentar coger galletas
                }

                // Comer galletas
                System.out.println(getName() + " - Me como " + galletas + " galletas. Quedaban: " + almacen.galletas);
                almacen.galletas -= galletas;
                almacenSemaphore.release(); // Salir de la sección crítica

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}