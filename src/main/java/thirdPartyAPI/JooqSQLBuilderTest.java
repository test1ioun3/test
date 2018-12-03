package thirdPartyAPI;

import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
import static org.jooq.impl.DSL.*;


public class JooqSQLBuilderTest {

    public static void main(String[] args) {
        // List<String> futureIdList = new ArrayList<>(Arrays.asList("IF1808", "IF1809", "IF1812", "IF1903"));
//        List<Field>  fields       = new ArrayList<>();
        Settings settings = new Settings();
        settings.setRenderNameStyle(RenderNameStyle.AS_IS);
        DefaultConfiguration defaultConfiguration = new DefaultConfiguration();
        defaultConfiguration.set(settings);

        String sqlStatements =
                using(defaultConfiguration).
                select(sum(field("SHARES_IN_INDEX").cast(Integer.TYPE)
                           .multiply(field("CLOSE").cast(Float.TYPE)))
                      .as("totalMV"))
                .from(table("I_INDEX_WEIGHTNEXTDAY"))
                .where(field("INDEX_STKID").equal("000016")).toString();
        System.out.println(sqlStatements);

        String sql2 =
                using(defaultConfiguration)
               .select(field("*"))
                .from(table("i_MDDIVIDEND"))
                .where(field("drdate")
                      .equal(select(field("tradedate")).from(table("pb_sysconfig"))
                            .where(field("fundtype").equal(1)))).toString();

        System.out.println(sql2);

    }

}
