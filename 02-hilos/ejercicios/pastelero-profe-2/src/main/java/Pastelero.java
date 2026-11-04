import java.util.concurrent.Semaphore;

public class Pastelero extends Thread {
    Semaphore camareroSemaphore;
    Semaphore pasteleroSemaphore;
    Semaphore mutex;
    Repisa repisa1;
    Repisa repisa2;

    public Pastelero(Semaphore camareroSemaphore, Semaphore pasteleroSemaphore, Semaphore mutex, Repisa repisa1, Repisa repisa2) {
        this.camareroSemaphore = camareroSemaphore;
        this.pasteleroSemaphore = pasteleroSemaphore;
        this.mutex = mutex;
        this.repisa1 = repisa1;
        this.repisa2 = repisa2;
    }

    @Override
    public void run() {
        while ((repisa1.vendidos + repisa2.vendidos) <= 10) {
            if (repisa1.pasteles == 5 && repisa2.pasteles == 5) {
                try {
                    System.out.println("Pastelero bloquea");
                    pasteleroSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                mutex.acquire();
                if (repisa1.pasteles == 0 && repisa2.pasteles==0) {
                    System.out.println("Libero pastelero " + (repisa1.pasteles+repisa2.pasteles));
                    camareroSemaphore.release();
                }
                System.out.println("Hago pastel " + (repisa1.pasteles+ repisa1.pasteles));
                if (repisa1.pasteles<5){
                    repisa1.pasteles++;
                }else{
                    repisa2.pasteles++;
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


