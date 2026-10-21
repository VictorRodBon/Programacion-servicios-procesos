package Hilos;

import VariablesCompartidas.Estanteria;

import java.util.concurrent.Semaphore;

public class HiloCamarero extends Thread{
    private Estanteria estanteria = null;
    private Semaphore semaphore;

    public HiloCamarero(Estanteria estanteria, Semaphore semaphore) {
        this.estanteria = estanteria;
        this.semaphore = semaphore;
    }
}