package pres.qm.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import pres.qm.excel.listener.DemoDataListener;
import pres.qm.excel.read.DemoDate;

import java.io.File;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/29  14:04
 * @description :
 */
public class TestRead {

    @Test
    public void m1(){
        String fileName = "filePath/" + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoDate.class, new DemoDataListener()).sheet().doRead();
    }
}
