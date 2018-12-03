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
//        provider.getEPAdministrator().getConfiguration().addEventType(TestEvent.class);
//
//        String epl1 = "insert into OHLC select first(price) as open,"
//                      + "max(price) as high,"
//                      + "min(price) as low,"
//                      + "last(price) as close from TestEvent#time_batch(5 sec)";
//
//        EPStatement statement1 = provider.getEPAdministrator().createEPL(epl1);
//        statement1.addListener(new TestListener());
//    }
//
//    public static void main(String[] args) throws Exception {
//        P5_esper instance = new P5_esper();
//        instance.init();
//        TestEvent event1 = new TestEvent(1);
//        TestEvent event2 = new TestEvent(2);
//        TestEvent event3 = new TestEvent(3);
//        TestEvent event4 = new TestEvent(4);
//        TestEvent event5 = new TestEvent(5);
//        TestEvent event6 = new TestEvent(6);
//        TestEvent event7 = new TestEvent(7);
//        TestEvent event8 = new TestEvent(8);
//        TestEvent event9 = new TestEvent(9);
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