package org.educa;

public class MiHilo extends Thread{
    String nombre;

    public MiHilo(String nombre){
        this.nombre=nombre;
    }

    public void run(){
        for (int i=0; i<10; i++){
            System.out.println("soy "+nombre);
        }

    }
}
