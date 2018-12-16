//package P4;
//
//import com.espertech.esper.client.*;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class P5_esper {
//
//    private static EPServiceProvider provider = EPServiceProviderManager.getDefaultProvider();
//
//    private void init() {
//        provider.getEPAdministrator().getConfiguration().addEventType(TestRun.class);
//
//        String epl1 = "insert into OHLC select first(price) as open,"
//                      + "max(price) as high,"
//                      + "min(price) as low,"
//                      + "last(price) as close from TestRun#time_batch(5 sec)";
//
//        EPStatement statement1 = provider.getEPAdministrator().createEPL(epl1);
//        statement1.addListener(new TestListener());
//    }
//
//    public static void main(String[] args) throws Exception {
//        P5_esper instance = new P5_esper();
//        instance.init();
//        TestRun event1 = new TestRun(1);
//        TestRun event2 = new TestRun(2);
//        TestRun event3 = new TestRun(3);
//        TestRun event4 = new TestRun(4);
//        TestRun event5 = new TestRun(5);
//        TestRun event6 = new TestRun(6);
//        TestRun event7 = new TestRun(7);
//        TestRun event8 = new TestRun(8);
//        TestRun event9 = new TestRun(9);
//
//        System.out.println("中文");
//
//        provider.getEPRuntime().sendEvent(event1);
//        Thread.sleep(500);
//        provider.getEPRuntime().sendEvent(event2);
//        Thread.sleep(500);
//        provider.getEPRuntime().sendEvent(event3);
//        System.out.println("1s passed, sent 3 events");
//        System.out.println("then sleep 10s");
//        Thread.sleep(10000);
//
//        provider.getEPRuntime().sendEvent(event4);
//        while (true) {
//
//        }
//    }
//}
//
//class TestListener implements UpdateListener {
//
//    @Override
//    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
//        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss:SSS");
//        System.out.println(df.format(new Date()));
//        System.out.println("open  " + newEvents[0].get("open"));
//        System.out.println("high  " + newEvents[0].get("high"));
//        System.out.println("low  " + newEvents[0].get("low"));
//        System.out.println("close  " + newEvents[0].get("close"));
//    }
//}