package pres.qianmuna.ioc.v2.framework.servlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/16  20:25
 * @description : View
 */
public class View {

    private File viewFile;

    /**
     * inif
     * @param templateFile File
     */
    public View(File templateFile) {
        this.viewFile = templateFile;
    }

    /**
     * 渲染
     * 解析 页面
     * @param model model
     * @param req req
     * @param resp resp
     */
    public void render(Map<String,?> model, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        StringBuffer buffer = new StringBuffer();
        // 文件 修饰
        RandomAccessFile ra = new RandomAccessFile(this.viewFile, "r");

        String line = "";
        // read file
        while (null != (line = ra.readLine())){
            // 编码
            line = new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            // 无正则 不架构
            // 无反射 无框架
            // 内容 非 } 以外 全部 匹配
            // 忽略大小写
            Pattern pattern = Pattern.compile("\\^\\{[^}]+}" , Pattern.CASE_INSENSITIVE);

            // 匹配
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                // 得到 分组 内容
                String pName = matcher.group();
                // 表达式 里面的 值
                pName = pName.replaceAll("\\^\\{|}" , "");

                //从model 取出
                Object value = model.get(pName);

                // 替换
                line = matcher.replaceFirst(value.toString());

                // 继续 匹配
                matcher = pattern.matcher(line);

            }
            //
            buffer.append(line);
        }

        // 页面回显
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(buffer.toString());
    }
}
