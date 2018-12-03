package algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PythonStyleFormat {

    public static void main(String[] args) throws Exception {

        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        Thread a = new ThreadA(lock, condition);
        a.start();
        Thread.sleep(3000);
        Thread b = new ThreadB(lock, condition);
        b.start();
    }
}

class ThreadA extends Thread {

    private ReentrantLock lock;
    private Condition condition;

    public ThreadA(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        super.run();
        try {
            lock.lock();
            System.out.println("ThreadA start awaiting, lock holdCount is " + lock.getHoldCount()
                               + " condition is " + condition.toString());
            condition.await();
            System.out.println("ThreadA is waked up by ThreadB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class ThreadB extends Thread {

    private ReentrantLock lock;
    private Condition condition;

    public ThreadB(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        super.run();
        lock.lock();
        System.out.println("ThreadB start to wake up ThreadA: lock is " + lock.toString()
                           + " condition is " + condition.toString());
        condition.signal();
        lock.unlock();
    }
}