import java.util.concurrent.Semaphore;

public class Camarero extends Thread{
    Semaphore camareroSemaphore;
    Semaphore pasteleroSemaphore;
    Semaphore mutex;
    Repisa repisa1;

    public Camarero(Semaphore camareroSemaphore, Semaphore pasteleroSemaphore, Semaphore mutex, Repisa repisa1) {
        this.camareroSemaphore = camareroSemaphore;
        this.pasteleroSemaphore = pasteleroSemaphore;
        this.mutex = mutex;
        this.repisa1 = repisa1;
    }

    @Override
    public void run() {
        while((repisa1.vendidos1 + repisa1.vendidos2) <= 10){
            if (repisa1.pasteles1==0 && repisa1.pasteles2==0){
                System.out.println("Camarero bloquea " + (repisa1.pasteles1+repisa1.pasteles2));
                try {
                    camareroSemaphore.acquire();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            try {
                mutex.acquire();
                if (repisa1.pasteles1==5 && repisa1.pasteles2==5){
                    System.out.println("Libero camarero "+(repisa1.pasteles1+repisa1.pasteles2));
                    pasteleroSemaphore.release();
                }
                System.out.println("Vendo pastel "+(repisa1.vendidos1+repisa1.vendidos2));
                if (repisa1.pasteles1>0){

                    repisa1.pasteles1--;
                    repisa1.vendidos1++;
                }else {
                    repisa1.pasteles2--;
                    repisa1.vendidos2++;
                }

                mutex.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
