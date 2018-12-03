import network.P3T3_Logger.FruitLogger;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.slf4j.LoggerFactory;

public class TestLog {
    private static Logger                      fruityLogger;
    private static ListAppender<ILoggingEvent> fruityLoggerAppender;

    @BeforeClass
    public static void setupBeforeClass() {
        LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
        fruityLogger = context.getLogger(FruitLogger.class);
        fruityLoggerAppender = new ListAppender<>();
        fruityLoggerAppender.start();
        fruityLogger.addAppender(fruityLoggerAppender);

    }

    @Before
    public void setup() {
        fruityLogger.setLevel(Level.ALL);
        fruityLoggerAppender.clearAllFilters();
        fruityLoggerAppender.list.clear();
    }

    @Test
    public void verifyLog() {
        assertThat(0, is(fruityLoggerAppender.list.size()));

        FruitLogger INSTANCE = FruitLogger.getInstance();
        String fruityMessage = "apples, oranges";
        INSTANCE.logString(fruityMessage);

        assertThat(1, is(fruityLoggerAppender.list.size()));

        ILoggingEvent loggedEvent = fruityLoggerAppender.list.get(0);
        assertThat(loggedEvent.getLevel(), is(Level.INFO));
        assertThat(loggedEvent.getFormattedMessage(), containsString("apples"));
        assertThat(loggedEvent.getFormattedMessage(), containsString("oranges"));
    }
}
