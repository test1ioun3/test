package multiThread;


public class mtl {

    public static void main(String[] args) {
        Object lock = new Object();
        WaitThread waitThread1 = new WaitThread(lock);
        waitThread1.setName("waitThread-1");
        WaitThread waitThread2 = new WaitThread(lock);
        waitThread2.setName("waitThread-2");
        NotifyThread notifyThread = new NotifyThread(lock);
        waitThread1.start();
        waitThread2.start();
        notifyThread.start();
    }
}

class WaitThread extends Thread {
    private final Object lock;
    public WaitThread(Object lock) {
        this.lock = lock;
    }
    public void waiting() {
        try {
            synchronized (lock) {
                System.out.println(currentThread().getName() +
                                   " start waiting");
                lock.wait(2000);
                System.out.println(currentThread().getName() +
                                   " stop waiting");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        waiting();
    }
}

class NotifyThread extends Thread {
    private final Object lock;
    public NotifyThread(Object lock) {
        this.lock = lock;
    }
    public void notifying() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(3000);
            notifying();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

