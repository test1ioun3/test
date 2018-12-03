import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestException {

    @Benchmark
    public void testException() {
        try {;
            Thread.sleep(1000);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception{

        Options opt = new OptionsBuilder()
                .include(TestException.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
