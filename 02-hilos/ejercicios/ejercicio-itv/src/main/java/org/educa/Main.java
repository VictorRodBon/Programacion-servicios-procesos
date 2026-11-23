package org.educa;

import java.util.concurrent.Semaphore;

/*
 * Diseña un programa que reproduzca el comportamiento de una ITV.
 * A la ITV acuden todas las mañanas 10 coches a pasar la revisió.
 * El coche deberá seleccionar una de las 3 lineas de inspección disponibles
 * en ese momento.Las líneas de inspección revisarán los coches y cuando
 * terminen liberarán al coche inspeccionado para acoger a uno nuevo.
 * Las lineas terminarán cuando se hayan supervisado los 10 coches.
 * Todos los coches entrarán y saldran por la misma puerta al recinto
 *
 */

public class Main {
    public static void main(String[] args) {

        Semaphore espera = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);
        BeamITV beamITV = new BeamITV();

        HiloCoche h1 = new HiloCoche(espera, mutex, beamITV, 1);
        HiloCoche h2 = new HiloCoche(espera, mutex, beamITV, 2);
        HiloCoche h3 = new HiloCoche(espera, mutex, beamITV, 3);
        HiloCoche h4 = new HiloCoche(espera, mutex, beamITV, 4);
        HiloCoche h5 = new HiloCoche(espera, mutex, beamITV, 5);
        HiloCoche h6 = new HiloCoche(espera, mutex, beamITV, 6);
        HiloCoche h7 = new HiloCoche(espera, mutex, beamITV, 7);
        HiloCoche h8 = new HiloCoche(espera, mutex, beamITV, 8);
        HiloCoche h9 = new HiloCoche(espera, mutex, beamITV, 9);
        HiloCoche h10 = new HiloCoche(espera, mutex, beamITV, 10);

        HiloLinea l1=new HiloLinea(espera, mutex, beamITV, 1);
        HiloLinea l2=new HiloLinea(espera, mutex, beamITV, 2);
        HiloLinea l3=new HiloLinea(espera, mutex, beamITV, 3);

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        h6.start();
        h7.start();
        h8.start();
        h9.start();
        h10.start();

        l1.start();
        l2.start();
        l3.start();

    }
}

