package org.educa;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore monstruoSemaphore = new Semaphore(0);
        Semaphore almaceneroSemaphore = new Semaphore(0);
        Semaphore almacenSemaphore = new Semaphore(1); // Mutex para controlar acceso al almacén (permisos = 1)
        BeamAlmacen almacen = new BeamAlmacen();

        Random rand = new Random();

        Almacenero almacenero = new Almacenero(almacenSemaphore, almaceneroSemaphore, monstruoSemaphore, almacen);

        // Pasamos correctamente los 3 semáforos a cada monstruo
        Monstruo monstruo1 = new Monstruo(almacenSemaphore, almaceneroSemaphore, monstruoSemaphore, almacen, (rand.nextInt(5) + 1));
        Monstruo monstruo2 = new Monstruo(almacenSemaphore, almaceneroSemaphore, monstruoSemaphore, almacen, (rand.nextInt(5) + 1));
        Monstruo monstruo3 = new Monstruo(almacenSemaphore, almaceneroSemaphore, monstruoSemaphore, almacen, (rand.nextInt(5) + 1));

        almacenero.start();
        monstruo1.start();
        monstruo2.start();
        monstruo3.start();
    }
}