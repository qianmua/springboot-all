package pres.qm.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.builder.ExcelWriterTableBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import org.apache.poi.ss.usermodel.TableStyle;
import org.junit.Test;
import pres.qm.excel.po.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
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

    @Test
    public void m2(){
        String s = TestExcel.class.getResource("/").getPath() + "simplyWrite" + System.currentTimeMillis() + ".xlsx";
        // write
        EasyExcel.write(s , ComplexHeadData.class).sheet("样式").doWrite(cData());
    }

    @Test
    public void m3(){
        String s = TestExcel.class.getResource("/").getPath() + "simplyWrite" + System.currentTimeMillis() + ".xlsx";
        ExcelWriter writer = null;

        try{
            writer = EasyExcel.write(s , TestData.class).build();

            WriteSheet writeSheet = EasyExcel.writerSheet("模板啊").build();

            for (int i = 0; i < 5; i++) {
                List<TestData> data = data();
                writer.write(data , writeSheet);
            }
        }finally {
            if (writer != null){
                writer.finish();
            }
        }
    }

    @Test
    public void m4(){
        String s = TestExcel.class.getResource("/").getPath() + "simplyWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(s, ConverterData.class).sheet("模板").doWrite(data2());
    }

    @Test
    public void m5() throws IOException {
        String s = TestExcel.class.getResource("/").getPath() + "simplyWrite" + System.currentTimeMillis() + ".xlsx";

        // 如果使用流 记得关闭
        InputStream inputStream = null;
        try {
            List<ImageData> list = new ArrayList<>();
            ImageData imageData = new ImageData();
            list.add(imageData);
            String imagePath = /*TestFileUtil.getPath() +*/ "converter" + File.separator + "img.jpg";
            // 放入五种类型的图片 实际使用只要选一种即可
//            imageData.setBytes(FileUtils.readFileToByteArray(new File(imagePath)));
//            imageData.setFile(new File(imagePath));
//            imageData.setString(imagePath);
//            inputStream = FileUtils.openInputStream(new File(imagePath));
//            imageData.setInputStream(inputStream);
            imageData.setUrl(new URL(
                    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2724409193,2018760642&fm=26&gp=0.jpg"));
            EasyExcel.write(s, ImageData.class).sheet().doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Test
    public void m6() throws FileNotFoundException {
        String s = TestExcel.class.getResource("/").getPath() + "simplyWrite" + System.currentTimeMillis() + ".xlsx";
        String date = "2020年09月32日出货表";

        EasyExcel.write(s, DemoStyleData.class)
                .head(head(date))
                .sheet("模板")
                .doWrite(data3());

    }


    private List<List<String>> head(String date) {
        List<List<String>> list = new ArrayList<List<String>>();

        List<String> head0 = new ArrayList<String>();
        List<String> head1 = new ArrayList<String>();
        List<String> head2 = new ArrayList<String>();
        List<String> head3 = new ArrayList<String>();
        List<String> head4 = new ArrayList<String>();
        List<String> head5 = new ArrayList<String>();
        List<String> head6 = new ArrayList<String>();
        List<String> head7 = new ArrayList<String>();

        head0.add(date);
        head1.add(date);
        head2.add(date);
        head3.add(date);
        head4.add(date);
        head5.add(date);
        head6.add(date);
        head7.add(date);

        head0.add("客户");
        head1.add("订单号");
        head2.add("货号");
        head3.add("数量");
        head4.add("工厂");
        head5.add("工厂交期");
        head6.add("船期");
        head7.add("贸易条款");



        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);

        return list;
    }
    private List<DemoStyleData> data3(){
        ArrayList<DemoStyleData> testData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoStyleData data = new DemoStyleData();
            data.setString("str1");
            data.setDate(new Date());
            data.setDoubleData(0.05 + 0.34);
            testData.add(data);
        }
        return testData;
    }

    private List<ComplexHeadData> cData(){
        ArrayList<ComplexHeadData> testData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ComplexHeadData data = new ComplexHeadData();
            data.setStr1("str1");
            data.setStr2(new Date().toString());
            data.setStr3("strsssss");
            testData.add(data);
        }
        return testData;
    }

    private List<ConverterData> data2(){
        ArrayList<ConverterData> testData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ConverterData data = new ConverterData();
            data.setStr("str1");
            data.setDate(new Date());
            data.setADouble(0.05 + 0.34);
            testData.add(data);
        }
        return testData;
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
