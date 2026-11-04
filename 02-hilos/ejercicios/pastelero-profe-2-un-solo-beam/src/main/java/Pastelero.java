import java.util.concurrent.Semaphore;

public class Pastelero extends Thread {
    Semaphore camareroSemaphore;
    Semaphore pasteleroSemaphore;
    Semaphore mutex;
    Repisa repisa1;

    public Pastelero(Semaphore camareroSemaphore, Semaphore pasteleroSemaphore, Semaphore mutex, Repisa repisa1) {
        this.camareroSemaphore = camareroSemaphore;
        this.pasteleroSemaphore = pasteleroSemaphore;
        this.mutex = mutex;
        this.repisa1 = repisa1;
    }

    @Override
    public void run() {
        while ((repisa1.vendidos1 + repisa1.vendidos2) <= 10) {
            if (repisa1.pasteles1 == 5 && repisa1.pasteles2 == 5) {
                try {
                    System.out.println("Pastelero bloquea");
                    pasteleroSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                mutex.acquire();
                if (repisa1.pasteles1 == 0 && repisa1.pasteles2==0) {
                    System.out.println("Libero pastelero " + (repisa1.pasteles1+repisa1.pasteles2));
                    camareroSemaphore.release();
                }
                System.out.println("Hago pastel " + (repisa1.pasteles1+ repisa1.pasteles2));
                if (repisa1.pasteles1<5){
                    repisa1.pasteles1++;
                }else{
                    repisa1.pasteles2++;
                }
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


