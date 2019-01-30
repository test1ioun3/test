package esper;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class LastEventTest {
    public static void main(String[] args) {
        EPServiceProvider provider = EPServiceProviderManager.getDefaultProvider();

        provider.getEPAdministrator().getConfiguration().addEventType(TestEvent.class);

//        String epl = "select * from TestEvent#length(3) t where t.number between 3 and 5 having window(t.number).allOf(x => x>2)";

        String epl = "select * from TestEvent#length(3) t where t.number between 3 and 6 having count(*)=1";


        EPStatement statement = provider.getEPAdministrator().createEPL(epl);
        statement.setSubscriber(new LastEventTest(), "printer");

        provider.getEPRuntime().sendEvent(new TestEvent("A", 1));
        provider.getEPRuntime().sendEvent(new TestEvent("B", 2));
        provider.getEPRuntime().sendEvent(new TestEvent("C", 3));
        provider.getEPRuntime().sendEvent(new TestEvent("D", 4));
        provider.getEPRuntime().sendEvent(new TestEvent("E", 5));
        provider.getEPRuntime().sendEvent(new TestEvent("F", 6));
        provider.getEPRuntime().sendEvent(new TestEvent("G", 7));

    }

    public void printer(Object o) {
        System.out.println(o);

        String[] stringList = (String[]) o;
        for (String s : stringList) {
            System.out.printf("%s ", s);
        }
        System.out.println();
    }

    public static class TestEvent {
        private String name;
        private int number;

        public TestEvent(String name, int number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "name: " + name + " number: " + number;
        }
    }
}
