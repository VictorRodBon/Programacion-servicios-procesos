package Hilos;

import VariablesCompartidas.Estanteria;

import java.util.concurrent.Semaphore;

public class HiloCocinero extends Thread{
    private Estanteria estanteria = null;
    private Semaphore semaphore;

    public HiloCocinero(Estanteria estanteria, Semaphore semaphore) {
        this.estanteria = estanteria;
        this.semaphore = semaphore;
    }
}