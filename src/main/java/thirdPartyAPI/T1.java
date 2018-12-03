package thirdPartyAPI;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class T1 {

    public static void main(String[] args) {

        Table<String, String, Integer> excelTable = HashBasedTable.create();
        excelTable.put("row0","column0",1);
        excelTable.put("row0","column1",2);
        excelTable.put("row0","column2",3);

        int a = excelTable.row("row0").values().stream().reduce(0, Integer::sum);
        System.out.println(a);
        System.out.println(excelTable.row("row0"));
    }
}
