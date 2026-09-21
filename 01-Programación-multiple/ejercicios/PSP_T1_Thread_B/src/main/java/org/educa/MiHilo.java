package org.educa;

import java.util.ArrayList;

public class MiHilo extends Thread{
    public ArrayList<Integer> numeros;
    public MiHilo(ArrayList<Integer> numeros){
        this.numeros=numeros;
    }
    @Override
    public void run() {
        for (int i = 0; i < numeros.size(); i++) {
            System.out.println(numeros.get(i));
        }
    }
}
