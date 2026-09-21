package org.educa;

public class MiHilo extends Thread{
    public int num;

    public MiHilo(int num){
        this.num=num;
    }

    @Override
    public void run() {
        System.out.println(num);
    }
}
