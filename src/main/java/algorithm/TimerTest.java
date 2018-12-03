package algorithm;


public class TimerTest {

    public static void main(String[] args) {
        Strategy strategy = new Strategy();
        strategy.printVariable();
    }
}

class Strategy {

    // 1
    private String a = "123";
    private String b = a;

    // 2

    private String c;


    // 3
    public Strategy() {

        System.out.println("a:  " + a);
        System.out.println("b:  " + b);
        System.out.println("c:  " + c);

        c="789";
        b= "101112";
        a = "131415";

        System.out.println("a:  " + a);
        System.out.println("b:  " + b);
        System.out.println("c:  " + c);
    }

    public void printVariable() {
        System.out.println("a:  " + a);
        System.out.println("b:  " + b);
        System.out.println("c:  " + c);
    }
}
