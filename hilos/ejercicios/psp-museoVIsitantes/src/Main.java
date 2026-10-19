import Hilos.HiloVisitante;
import VariablesCompartidas.TiempoMedio;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(1); //numero de entradas que tiene el museo
        Semaphore aforo = new Semaphore(3); //segurata que comprueba el aforo de 3
        Semaphore vc = new Semaphore(1); //segurata que va a estar en la sala importante del museo
        TiempoMedio tm = new TiempoMedio(); //variable compartida

        HiloVisitante h1 = new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h2 = new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h3 = new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h4 = new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h5 = new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h6 = new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h7 = new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h8 = new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h9= new HiloVisitante(mutex, tm, aforo, vc);
        HiloVisitante h10 = new HiloVisitante(mutex, tm, aforo, vc);

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

        try {
            h1.join(); h2.join(); h3.join(); h4.join(); h5.join();
            h9.join(); h6.join(); h7.join(); h8.join(); h10.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } //hasta que no se vayan todos los visitantes no muestra esta media

        System.out.println("Tiempo medio de los semaforos = "+(tm.getTiempoMedio()/ tm.getContador())/10000);
    }
}