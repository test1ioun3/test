package esper.groupBy;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class GroupByListener implements UpdateListener {

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        System.out.println("eventBean number:  " + newEvents.length);
        System.out.println(newEvents[0].get("stkId"));
        System.out.println(newEvents[0].get("exchId"));
        System.out.println(newEvents[0].get("qty"));
        System.out.println("-***-*-*-*-**-*-*-*-*-*");
    }
}
