package pers.jc.flow.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/9  9:36
 */
public class Context {
    /**
     * cache result
     */
    private Map<String ,Object> resultMap = new HashMap<>();

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
