package multiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {

    public static void main(String[] args) throws Exception {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        System.out.println("before await");
        condition.await();
        System.out.println("after await");
        lock.unlock();
    }

}
