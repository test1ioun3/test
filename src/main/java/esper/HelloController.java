package esper;

import com.jfinal.core.Controller;

import java.math.BigDecimal;

public class HelloController extends Controller {

    public static void main(String[] args) {


        BigDecimal a = new BigDecimal(1);
        BigDecimal b = a;
    }
}
