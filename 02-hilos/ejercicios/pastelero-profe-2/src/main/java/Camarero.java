import java.util.concurrent.Semaphore;

public class Camarero extends Thread{
    Semaphore camareroSemaphore;
    Semaphore pasteleroSemaphore;
    Semaphore mutex;
    Repisa repisa1;
    Repisa repisa2;

    public Camarero(Semaphore camareroSemaphore, Semaphore pasteleroSemaphore, Semaphore mutex, Repisa repisa1, Repisa repisa2) {
        this.camareroSemaphore = camareroSemaphore;
        this.pasteleroSemaphore = pasteleroSemaphore;
        this.mutex = mutex;
        this.repisa1 = repisa1;
        this.repisa2 = repisa2;
    }

    @Override
    public void run() {
        while((repisa1.vendidos + repisa2.vendidos) <= 10){
            if (repisa1.pasteles==0 && repisa2.pasteles==0){
                System.out.println("Camarero bloquea " + (repisa1.pasteles+repisa2.pasteles));
                try {
                    camareroSemaphore.acquire();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            try {
                mutex.acquire();
                if (repisa1.pasteles==5 && repisa2.pasteles==5){
                    System.out.println("Libero camarero "+(repisa1.pasteles+repisa2.pasteles));
                    pasteleroSemaphore.release();
                }
                System.out.println("Vendo pastel "+(repisa1.vendidos+repisa2.vendidos));
                if (repisa1.pasteles>0){

                    repisa1.pasteles--;
                    repisa1.vendidos++;
                }else {
                    repisa2.pasteles--;
                    repisa2.vendidos++;
                }

                mutex.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
