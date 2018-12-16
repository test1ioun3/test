package multiThread;

public class LoggingWidget extends Widget {

    public synchronized void doSomething() {
        System.out.println("calling doSomething:  ");
        super.doSomething();
    }
}
