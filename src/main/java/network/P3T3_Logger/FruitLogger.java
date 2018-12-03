package network.P3T3_Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FruitLogger {
    private static final FruitLogger INSTANCE = new FruitLogger();
    private static final Logger log = LoggerFactory.getLogger(FruitLogger.class);
    private FruitLogger() {}

    public static FruitLogger getInstance() {
        return INSTANCE;
    }
    public void logString(String logMessage) {
        if (!logMessage.isEmpty()) {
            log.info(logMessage);
        }
    }
}
