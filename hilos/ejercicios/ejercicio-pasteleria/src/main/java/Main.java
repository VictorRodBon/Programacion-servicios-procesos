import Hilos.HiloCamarero;
import Hilos.HiloCocinero;
import VariablesCompartidas.Estanteria;

import java.util.concurrent.Semaphore;

public class Main {

    /*
    * Crea un programa que tenga un HiloCamarero que recoge pasteles de una variable compartida Estanteria y los vende, y
    * un HiloCocinero que cocina los pasteles y los pone en la variable compartida Estanteria.
    *
    * Cuando no hay pasteles en la estantería el camarero se bloquea, y se desbloquea cuando hay un nuevo pastel.
    * Cuando no hay hueco para más pasteles en la estantería el cocinero se bloquea, y se desbloquea cuando hay un nuevo hueco.
    *
    * Cuando se vendan 12 tartas se termina el bloqueo.
    *
    * Pastelero: if pasteles == 5 aquire
    * Pastelero: if pasteles == 5 aquire
    *
    * */

    public static void main(String[] args) {
        Estanteria estanteria = new Estanteria(0,0);

        Semaphore semaphoreEstanteria = new Semaphore(1);
        Semaphore semaphoreCocinero = new Semaphore(0);
        Semaphore semaphoreCamarero = new Semaphore(0);

        // creamos el hilo cocinero
        HiloCocinero cocinero = new HiloCocinero(estanteria, semaphoreCocinero);
        // creamos el hilo camarero
        HiloCamarero camarero = new HiloCamarero(estanteria, semaphoreCamarero);

        // empezamos el hilo cocinero
        cocinero.start();
        // empezamos el hilo camarero
        camarero.start();
    }
}
