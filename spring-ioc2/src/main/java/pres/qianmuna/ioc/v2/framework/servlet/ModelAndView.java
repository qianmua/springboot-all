package pres.qianmuna.ioc.v2.framework.servlet;

import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/16  20:25
 * @description : ModelAndView
 */
public class ModelAndView {

    /**
     * view Name
     */
    private String viewName;

    /**
     *  model
     */
    private Map<String,?> model;


    /**
     * view Name
     * @param viewName view Name
     */
    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public ModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    /**
     * view Name
     * @return viewName
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * get model
     * @return model
     */
    public Map<String,?> getModel() {
        return this.model;
    }
}
