package edu.tjpu;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    static AtomicInteger i;
    public static void main(String[] args) throws InterruptedException {
        int j=0;
        while (j<100){
            i=new AtomicInteger(0);
            Thread thread1=new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 1000; i1++) {
                        i.getAndIncrement();
                    }

                }
            });
            Thread thread2=new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 1000; i1++) {
                        i.getAndIncrement();
                    }
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("i的最终值："+i.get());
            j++;
        }
    }
}
