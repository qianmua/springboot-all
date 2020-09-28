package pres.qm.excel.po;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/28  20:46
 * @description :
 */
@Data
public class TestData {

    /**
     * @param index  第几行
     */
//    @ExcelProperty(value = "字符串标题" , index = 0)
    @ExcelProperty(value = "字符串标题" )
    private String str1;

//    @ExcelProperty(value = "时间标题" , index = 1)
    @ExcelProperty(value = "时间标题" )
    private Date date;

    @ExcelProperty(value = "数字标题")
//    @ExcelProperty(value = "数字标题" , index = 2)
    private Double aDouble;

    @ExcelIgnore
    private String msg;
}
