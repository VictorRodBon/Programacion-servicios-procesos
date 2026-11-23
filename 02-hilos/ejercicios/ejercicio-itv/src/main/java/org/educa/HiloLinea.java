package org.educa;

import java.util.concurrent.Semaphore;

public class HiloLinea extends Thread{

    Semaphore espera = null;
    Semaphore mutex = null;
    BeamITV beamITV = null;
    int numeroLinea=0;

    public HiloLinea(Semaphore espera, Semaphore mutex, BeamITV beamITV, int numero) {
        this.espera = espera;
        this.mutex = mutex;
        this.beamITV = beamITV;
        this.numeroLinea=numero;
    }

    @Override
    public void run(){
        //desbloqueo
        //reviso
        //apunto

        while(beamITV.total<10){
            try {
                mutex.acquire();
                    espera.release();
                    beamITV.total++;
                    if(numeroLinea==1){
                        beamITV.linea1++;
                        System.out.println("entra en linea1 y llevo "+beamITV.linea1+" coches");
                    } else if (numeroLinea==2) {
                        beamITV.linea2++;
                        System.out.println("entra en linea2 y llevo "+beamITV.linea2+" coches");
                    }else{
                        beamITV.linea3++;
                        System.out.println("entra en linea3 y llevo "+beamITV.linea3+" coches");
                    }
                mutex.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
