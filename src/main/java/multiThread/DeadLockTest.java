package multiThread;

public class DeadLockTest {

    public static void main(String[] args) {
        try {
            DeadThread t1 = new DeadThread();
            t1.setFlag("a");
            Thread thread1 = new Thread(t1);
            thread1.start();
            Thread.sleep(100);
            t1.setFlag("b");
            Thread thread2 = new Thread(t1);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DeadThread implements Runnable {

    private       String userName;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    void setFlag(String userName) {

        this.userName = userName;
    }

    @Override
    public void run() {

        if (userName.equals("a"))
            synchronized (lock1) {
                try {
                    System.out.println("username= " + userName);
                    Thread.sleep(3000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("execution order: lock1 -> lock2");
                }
            }

        if (userName.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username= " + userName);
                    Thread.sleep(3000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("execution order: lock2 -> lock1");
                }
            }
        }
    }
}