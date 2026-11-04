import java.util.concurrent.Semaphore;

public class Camarero extends Thread{
    Semaphore camareroSemaphore;
    Semaphore pasteleroSemaphore;
    Semaphore mutex;
    Repisa repisa;

    public Camarero(Semaphore camareroSemaphore, Semaphore pasteleroSemaphore, Semaphore mutex, Repisa repisa) {
        this.camareroSemaphore = camareroSemaphore;
        this.pasteleroSemaphore = pasteleroSemaphore;
        this.mutex = mutex;
        this.repisa = repisa;
    }

    @Override
    public void run() {
        while(repisa.vendidos<12){
            if (repisa.pasteles==0){
                System.out.println("Camarero bloquea " + repisa.pasteles);
                try {
                    camareroSemaphore.acquire();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            try {
                mutex.acquire();
                if (repisa.pasteles==5){
                    System.out.println("Libero camarero "+repisa.pasteles);
                    pasteleroSemaphore.release();
                }
                System.out.println("Vendo pastel "+repisa.vendidos);

                repisa.pasteles--;
                repisa.vendidos++;

                mutex.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
