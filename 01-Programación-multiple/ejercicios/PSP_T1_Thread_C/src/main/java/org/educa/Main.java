package org.educa;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i<100;i++){
            MiHilo h=new MiHilo(i);
            h.start();
        }
    }
}
