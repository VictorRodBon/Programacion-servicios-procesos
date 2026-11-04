import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore camareroSepaphore = new Semaphore(0);
        Semaphore pasteleroSemaphore = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);

        Repisa repisa = new Repisa();

        Camarero hc = new Camarero(camareroSepaphore, pasteleroSemaphore, mutex,repisa);
        Pastelero hp = new Pastelero(camareroSepaphore, pasteleroSemaphore, mutex,repisa);

        hp.start();
        hc.start();
    }
}
