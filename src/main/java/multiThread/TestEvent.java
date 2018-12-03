package multiThread;

import java.io.Serializable;

public class TestEvent implements Serializable {

    private double price;

    public TestEvent(double data) {
        this.price = data;
    }


    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

}
