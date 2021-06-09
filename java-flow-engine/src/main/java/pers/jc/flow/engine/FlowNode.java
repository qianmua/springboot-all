package pers.jc.flow.engine;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/9  9:38
 */
public class FlowNode {

    private Map<String , NodeConf> nodeMap = new LinkedHashMap<>();

    public void add(String groupName , Class<?> nodeName , NodeConf nodeConf){
        String key = getKey(groupName, nodeName);
        if (nodeMap.containsKey(key)){
            return;
        }
        nodeMap.put(key ,nodeConf);
    }

    public void add(Class<?> nodeName ,NodeConf nodeConf){
        add(nodeName.getName() , nodeName , nodeConf);
    }

    public void replace(String groupName , Class<?> nodeName , NodeConf nodeConf) {
        String key = getKey(groupName, nodeName);
        if (nodeMap.containsKey(key)){
            return;
        }
        nodeMap.put(key ,nodeConf);
    }

    public void replace(Class<?> nodeName , NodeConf nodeConf) {
        replace(nodeName.getName() , nodeName , nodeConf);
    }

    public void remove(String groupName , Class<?> nodeName){
        String key = getKey(groupName,nodeName);
        nodeMap.remove(key);
    }

    public void remove(Class<?> nodeName) {
        remove(nodeName.getName() , nodeName);
    }

    public Map<String, NodeConf> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<String, NodeConf> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public Set<String> getNodeList(){
        return nodeMap.keySet();
    }

    private String getKey(String groupName, Class<?> nodeName) {
        String key = null;
        if (StringUtils.isNoneBlank(groupName)) {
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }
        return key;
    }

    public static class NodeConf {
        private int timeout = 300;

        public NodeConf() {}

        public NodeConf(int timeout) {
            this.timeout = timeout;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }
    }
}
