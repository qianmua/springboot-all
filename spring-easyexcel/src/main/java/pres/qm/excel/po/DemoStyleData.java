package pres.qm.excel.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.util.Date;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/28  21:57
 * @description :
 */
@Data
//@HeadStyle( fillPatternType = FillPatternType.SOLID_FOREGROUND , fillForegroundColor = 10)
@HeadFontStyle( fontHeightInPoints = 20)
//@ContentStyle( fillPatternType = FillPatternType.SOLID_FOREGROUND , fillForegroundColor = 17)
@HeadRowHeight(40)
public class DemoStyleData {

    // 字符串的头背景设置成粉红 IndexedColors.PINK.getIndex()
//    @HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 14)
    // 字符串的头字体设置成20
//    @HeadFontStyle(fontHeightInPoints = 30)
//     字符串的内容的背景设置成天蓝 IndexedColors.SKY_BLUE.getIndex()
    @ContentStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 40)
    // 字符串的内容字体设置成20
//    @ContentFontStyle(fontHeightInPoints = 30)
    @ExcelProperty({ "客户"})
    private String string;

    @ExcelProperty({  "订单号"})
    private String no1;
    @ExcelProperty({  "货号"})
    private String no2;
    @ExcelProperty({ "数量"})
    private Integer cnumber;
    @ExcelProperty({  "工厂"})
    private String factory;
    @ExcelProperty({ "工厂交期"})
    private String ft;

    @ExcelProperty({"船期"})
    private Date date;

    @ExcelProperty({"数字标题"})
    private Double doubleData;
}
