package edu.tjpu;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> n;
    public static void main(String[] args) throws InterruptedException {
        int j = 0;
        while(j<100){
            n = new AtomicStampedReference<Integer>(0,0);
            Thread t1 = new Thread(){
                public void run(){
                    for(int i=0; i<1000; i++){
                        Integer reference;
                        int stamp;
                        do {
                             reference = n.getReference();
                            stamp = n.getStamp();
                        }while (!(n.compareAndSet(reference,reference+1,stamp,stamp+1)));
                    }
                }
            };
            Thread t2 = new Thread(){
                public void run(){
                    for(int i=0; i<1000; i++){
                        Integer reference;
                        int stamp;
                        do {
                            reference = n.getReference();
                            stamp = n.getStamp();
                        }while (!(n.compareAndSet(reference,reference+1,stamp,stamp+1)));
                    }
                }
            };
            t1.start();
            t2.start();
            t1.join();//加入主线程
            t2.join();
            System.out.println("n的最终值是："+n.getReference());
            j++;
        }

    }
}
