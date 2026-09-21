package org.educa;

public class MiHilo extends Thread{
    String piloto;

    public MiHilo(String piloto){
        this.piloto=piloto;
    }

    @Override
    public void run() {
        for(int i =1;i<=78;i++){
            System.out.println(piloto);
            try {
                Thread.sleep((long) (Math.random() * 20 + 5));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(">>> ¡" + piloto + " ha terminado la carrera!");
    }
}
