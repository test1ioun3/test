package esper.groupBy;

public class TestEvent {
    String stkId;
    String exchId;
    int quantity;

    public TestEvent(String stkId, String exchId, int quantity) {
        this.stkId = stkId;
        this.exchId = exchId;
        this.quantity = quantity;
    }

    public String getStkId() {

        return stkId;
    }

    public void setStkId(String stkId) {

        this.stkId = stkId;
    }

    public String getExchId() {

        return exchId;
    }

    public void setExchId(String exchId) {

        this.exchId = exchId;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }
}
