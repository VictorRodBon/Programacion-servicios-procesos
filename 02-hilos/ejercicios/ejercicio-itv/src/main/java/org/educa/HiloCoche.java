package org.educa;

import java.util.concurrent.Semaphore;

public class HiloCoche extends Thread{

    Semaphore espera = null;
    Semaphore mutex = null;
    BeamITV beamITV = null;
    int numCoche=0;

    public HiloCoche(Semaphore espera, Semaphore mutex, BeamITV beamITV, int numCoche) {
        this.beamITV=beamITV;
        this.espera=espera;
        this.mutex=mutex;
        this.numCoche=numCoche;
    }

    @Override
    public void run() {
        // esperar a que atiendan
        System.out.println("soy "+numCoche+" y voy a pasar la itv");
        try {
            espera.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
