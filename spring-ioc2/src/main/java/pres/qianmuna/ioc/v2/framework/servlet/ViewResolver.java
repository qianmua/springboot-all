package pres.qianmuna.ioc.v2.framework.servlet;

import java.io.File;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/16  20:24
 * @description :
 */
public class ViewResolver {

    /**
     * 默认后缀
     */
    public final String DEFAULE_TEMPLATE_SUFFIX = ".html";

    /**
     * 视图
     */
    private File templateRootDir;

    /**
     * 视图
     * 寻找 对应的 文件
     * @param templateRoot templateRoot
     */
    public ViewResolver(String templateRoot) {
        String file = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        this.templateRootDir = new File(file);
    }

    /**
     * view
     * // 寻找
     * @param viewName viewName
     * @return View
     */
    public View resolverViewName(String viewName) {
        if (null == viewName || "".equals(viewName.trim()))
            return null;
        // 后缀 解析
        viewName = viewName.endsWith(DEFAULE_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFAULE_TEMPLATE_SUFFIX);
        // 模板文件
        File templateFile = new File((this.templateRootDir.getParent() + "/" + viewName).replaceAll("/+" , "/"));

        return new View(templateFile);
    }
}
