package pres.qm.excel.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;
import pres.qm.excel.custom.CustomStringStringConverter;

import java.util.Date;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/28  21:24
 * @description :
 */
@Data
public class ConverterData {

    @ExcelProperty( value = "str1" , converter = CustomStringStringConverter.class )
    private String str;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("日期")
    private Date date;

    @NumberFormat("#.##%")
    @ExcelProperty("数字")
    private Double aDouble;

}
