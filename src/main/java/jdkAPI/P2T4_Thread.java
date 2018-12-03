package jdkAPI;

public class P2T4_Thread {

    private static int count = 0;



    public static void main(String[] args) throws Exception{
        Thread threadA = new Thread(() -> {
            for (int i=0; i<10000; i++) {
//                System.out.println("thread A read count: " + count);
                count += 1;
//                System.out.println("thread A add count and result count is: " + count);
            }
            System.out.println("threadA done. last count is " + count);

        });

        Thread threadB = new Thread(() -> {
            for (int j=0; j<10000; j++) {
//                System.out.println("thread B read count: " + count);
                count += 1;
//                System.out.println("thread B add count and result count is: " + count);
            }
            System.out.println("threadB done. last count is " + count);
        });
        threadB.start();
        threadA.start();

//        while (threadA.isAlive() || threadB.isAlive()) {
//            // 等待线程执行完毕，轮询方法
//        }
//        System.out.println(count);
    }
}
