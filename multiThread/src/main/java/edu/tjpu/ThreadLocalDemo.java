package edu.tjpu;

public class ThreadLocalDemo {
    static class Bank{
        ThreadLocal<Integer> i=new ThreadLocal<Integer>(){

            @Override
            protected Integer initialValue() {
                return 0;
            }
        };
        public Integer get(){
            return i.get();
        }
        public void set(Integer money){
            i.set(i.get()+money);
        }
    }
    static class Transfer implements Runnable{

        private Bank bank;

        public Transfer(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                bank.set(10);
                System.out.println(Thread.currentThread().getName()+"账户余额："+bank.get());
            }
        }
    }

    public static void main(String[] args) {
        Bank bank=new Bank();
        Transfer transfer=new Transfer(bank);
        Thread thread1 = new Thread(transfer, "账户1");
        Thread thread2 = new Thread(transfer, "账户2");

        thread1.start();
        thread2.start();
    }
}
