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
    *
    * Camarero: si estanteria llena y quito un pastel -> desbloqueo cocinero
    * Cocinero: si estantería vacia y añado un pastel -> desbloqueo camarero
    *
    * */

    public static void main(String[] args) {
        Estanteria beamEstanteria = new Estanteria(0,0);

        Semaphore semaphoreEstanteria = new Semaphore(1);

        Semaphore semaphoreCocinero = new Semaphore(5);

        Semaphore semaphoreCamarero = new Semaphore(0);

        // creamos el hilo cocinero
        HiloCocinero cocinero = new HiloCocinero(beamEstanteria, semaphoreCocinero, semaphoreEstanteria, semaphoreCamarero);
        // creamos el hilo camarero
        HiloCamarero camarero = new HiloCamarero(beamEstanteria, semaphoreCamarero, semaphoreEstanteria, semaphoreCocinero);

        // empezamos el hilo cocinero
        cocinero.start();
        // empezamos el hilo camarero
        camarero.start();

        try{
            cocinero.join();
            camarero.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=== FINAL DEL PROGRAMA ===");
    }
}
