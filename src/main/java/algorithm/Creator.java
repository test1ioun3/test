package algorithm;

import com.espertech.esper.common.client.EPCompiled;
import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.compiler.client.CompilerArguments;
import com.espertech.esper.compiler.client.EPCompileException;
import com.espertech.esper.compiler.client.EPCompiler;
import com.espertech.esper.compiler.client.EPCompilerProvider;
import com.espertech.esper.runtime.client.*;



public class Creator {

    public static void main(String[] args) {

        EPCompiler compiler = EPCompilerProvider.getCompiler();
        Configuration configuration = new Configuration();
        configuration.getCommon().addEventType(Event.class);
        configuration.getCompiler().getByteCode().setAllowSubscriber(true);
        configuration.getCommon().getLogging().setEnableJDBC(false);
        configuration.getCommon().getLogging().setEnableQueryPlan(false);
        configuration.getRuntime().getLogging().setEnableExecutionDebug(false);
        configuration.getRuntime().getLogging().setEnableTimerDebug(false);

        CompilerArguments arguments = new CompilerArguments(configuration);

        String createWindow = "@name('s1') create window eventWindow#keepall as select * from Event";
        String insertIntoWindow = "@name('s2') insert into eventWindow select * from Event";
        String selectList = "@name('s3') select window(name) from eventWindow";
        String threeEPL = String.join(";", createWindow, insertIntoWindow, selectList);

        EPCompiled a = null;
        try {
            a = compiler.compile(threeEPL, arguments);
        } catch (EPCompileException ex) {
            ex.printStackTrace();
        }

        EPRuntime runtime = EPRuntimeProvider.getDefaultRuntime(configuration);
        EPDeployment deployment = null;
        try {
             deployment = runtime.getDeploymentService().deploy(a);
             EPStatement statement = runtime.getDeploymentService().getStatement(deployment.getDeploymentId(), "s3");
            statement.setSubscriber(new Creator(), "printList");
        } catch (EPDeployException ex) {
            ex.printStackTrace();
        }



        Event e1 = new Event("a", 1);
        Event e2 = new Event("n", 2);
        Event e3 = new Event("b", 3);

        runtime.getEventService().sendEventBean(e1, "Event");
        runtime.getEventService().sendEventBean(e2, "Event");
        runtime.getEventService().sendEventBean(e3, "Event");
    }



    public void printList(EPStatement statement, String[] names) {
        for (String name : names) {
            System.out.printf(name);
        }
        System.out.println();
    }
}
