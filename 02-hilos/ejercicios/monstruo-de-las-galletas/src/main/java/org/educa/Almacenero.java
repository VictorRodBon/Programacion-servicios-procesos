package org.educa;

import java.util.concurrent.Semaphore;

public class Almacenero extends Thread {
    Semaphore almacenSemaphore;
    Semaphore almaceneroSemaphore;
    Semaphore monstruoSemaphore;
    BeamAlmacen almacen;

    public Almacenero(Semaphore almacenSemaphore, Semaphore almaceneroSemaphore, Semaphore monstruoSemaphore, BeamAlmacen almacen) {
        this.almacenSemaphore = almacenSemaphore;
        this.almaceneroSemaphore = almaceneroSemaphore;
        this.monstruoSemaphore = monstruoSemaphore;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (almacen.repuesto < 10) {
                // Espera a que algún monstruo pida galletas
                almaceneroSemaphore.acquire();

                // Condición de salida si ya se completaron los repuestos
                if (almacen.repuesto >= 10) {
                    break;
                }

                // Sección crítica para reponer galletas
                if(almacen.galletas==0){
                    almacenSemaphore.acquire();
                    almacen.galletas += 10;
                    almacen.repuesto++;
                    System.out.println("--> [ALMACENERO] Repongo galletas (Repuesto nº " + almacen.repuesto + "). Total en almacén: " + almacen.galletas);
                    almacenSemaphore.release();
                }

                // Despierta al monstruo que estaba esperando
                monstruoSemaphore.release();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}