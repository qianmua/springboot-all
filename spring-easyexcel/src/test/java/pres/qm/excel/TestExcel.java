package pres.qm.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import pres.qm.excel.po.TestData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/28  20:45
 * @description :
 */
public class TestExcel {

    @Test
    public void m1(){
        String s = TestExcel.class.getResource("/").getPath() + "simplyWrite" + System.currentTimeMillis() + ".xlsx";
        // write
        EasyExcel.write(s , TestData.class).sheet("样式").doWrite(data());

    }


    private List<TestData> data(){
        ArrayList<TestData> testData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestData data = new TestData();
            data.setStr1("str1");
            data.setDate(new Date());
            data.setADouble(0.05 + 0.34);
            testData.add(data);
        }
        return testData;
    }


}
