import java.util.concurrent.Semaphore;

public class Pastelero extends Thread{
    Semaphore camareroSemaphore;
    Semaphore pasteleroSemaphore;
    Semaphore mutex;
    Repisa repisa;

    public Pastelero(Semaphore camareroSemaphore, Semaphore pasteleroSemaphore, Semaphore mutex, Repisa repisa) {
        this.camareroSemaphore = camareroSemaphore;
        this.pasteleroSemaphore = pasteleroSemaphore;
        this.mutex = mutex;
        this.repisa=repisa;
    }

    @Override
    public void run() {
        while(repisa.vendidos<12) {
            if (repisa.pasteles == 5) {
                try {
                    System.out.println("Pastelero bloquea");
                    pasteleroSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                mutex.acquire();
                if (repisa.pasteles == 0) {
                    System.out.println("Libero pastelero "+repisa.pasteles);
                    camareroSemaphore.release();
                }
                System.out.println("Hago pastel "+repisa.pasteles);
                repisa.pasteles++;
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
