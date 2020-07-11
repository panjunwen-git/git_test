package edu.tjpu;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWriteLockDemo {
    private HashMap<String,String> map=new HashMap<String,String>();
    private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private Lock readLock=readWriteLock.readLock();
    private Lock writeLock=readWriteLock.writeLock();

    public Object get(String key){
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + "读操作开始执行......");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "读操作执行完成......");
        }

    }
    public void put(String key,String value){
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + "写操作开始执行......");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "写操作执行完成......");
        }
    }

    public static void main(String[] args) {
        final ReadAndWriteLockDemo readAndWriteLockDemo= new ReadAndWriteLockDemo();
        readAndWriteLockDemo.put("name","junwenpan");
        new Thread("线程读操作1"){
            @Override
            public void run() {
                readAndWriteLockDemo.get("name");
            }
        }.start();
        new Thread("线程读操作2"){
            @Override
            public void run() {
                readAndWriteLockDemo.get("name");
            }
        }.start();
        new Thread("线程读操作3"){
            @Override
            public void run() {
                readAndWriteLockDemo.get("name");
            }
        }.start();
    }
}
