import Hilos.HiloConductor;
import Hilos.HiloVisitante;
import VariablesCompartidas.TiempoMedio;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1); //numero de entradas que tiene el museo
        Semaphore aforo = new Semaphore(3); //segurata que comprueba el aforo de 3
        Semaphore vc = new Semaphore(1); //segurata que va a estar en la sala importante del museo
        TiempoMedio tm = new TiempoMedio(); //variable compartida

        Semaphore espera = new Semaphore(0);
        Semaphore conductor = new Semaphore(0);


        HiloVisitante h1 = new HiloVisitante(mutex, tm, aforo, vc, espera, conductor);
        HiloVisitante h2 = new HiloVisitante(mutex, tm, aforo, vc, espera, conductor);
        HiloVisitante h3 = new HiloVisitante(mutex, tm, aforo, vc, espera, conductor);
        HiloVisitante h4 = new HiloVisitante(mutex, tm, aforo, vc, espera, conductor);
        HiloVisitante h5 = new HiloVisitante(mutex, tm, aforo, vc, espera, conductor);

        HiloConductor hc = new HiloConductor(conductor, espera, tm);


        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

        hc.start();

        try {
            h1.join(); h2.join(); h3.join(); h4.join(); h5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } //hasta que no se vayan todos los visitantes no muestra esta media

        System.out.println("\nTiempo medio de los semaforos = "+(tm.getTiempoMedio()/ tm.getContador())/10000);
    }
}