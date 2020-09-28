package pres.qm.excel.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;
import lombok.Data;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/28  21:42
 * @description :
 */
@Data
@ContentRowHeight(100)
@HeadRowHeight(50)
@ColumnWidth(100 /8)
public class ImageData {

    private File file;
    private InputStream inputStream;

    /**
     * String 类型 必须指定转换器
     * string 类型就 用 StringString 就好
     */
//    @ExcelProperty( converter = StringImageConverter.class)
//    private String string;

    private byte[] bytes;

    /**
     * 根据url 到处
     */
    @ExcelProperty("图片")
    @ColumnWidth(50)
    private URL url;





}
