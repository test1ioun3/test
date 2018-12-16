package esper.groupBy;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class GroupByTest {

    public static void main(String[] args) {

        EPServiceProvider provider = EPServiceProviderManager.getDefaultProvider();
        provider.getEPAdministrator().getConfiguration().addEventType(TestEvent.class);
        String createWindow = "create window testWindow#keepall select * from TestEvent";
        String insertWindow = "insert into testWindow select * from TestEvent";
        String groupBy = "on TestEvent t select count(*) from testWindow w group by w.stkId, w.exchId";
        provider.getEPAdministrator().createEPL(createWindow);
        provider.getEPAdministrator().createEPL(insertWindow);
        EPStatement statement = provider.getEPAdministrator().createEPL(groupBy);
        statement.addListener(new GroupByListener());

        provider.getEPRuntime().sendEvent(new TestEvent("600000", "0", 100));
        provider.getEPRuntime().sendEvent(new TestEvent("600000", "0", 100));
        provider.getEPRuntime().sendEvent(new TestEvent("000001", "1", 100));
        provider.getEPRuntime().sendEvent(new TestEvent("000000", "1", 100));
        provider.getEPRuntime().sendEvent(new TestEvent("000001", "1", 100));
        provider.getEPRuntime().sendEvent(new TestEvent("600000", "0", 101));
    }
}
