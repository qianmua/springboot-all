package pres.qm.excel.po;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/28  21:05
 * @description :
 */
@Data
public class ComplexHeadData {

    @ExcelProperty( {"主标题"  , "字符串1"})
    private String str1;
    @ExcelProperty( {"主标题"  , "字符串2"})
    private String str2;
    @ExcelProperty( {"主标题"  , "字符串3"})
    private String str3;
}
