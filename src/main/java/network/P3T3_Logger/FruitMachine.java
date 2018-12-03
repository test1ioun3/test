package network.P3T3_Logger;

public class FruitMachine {
    private final FruitLogger logger = FruitLogger.getInstance();
    private final String remoteUrl;
    public FruitMachine(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public void sendMessage(String message) {
        logger.logString(message);
    }

}
