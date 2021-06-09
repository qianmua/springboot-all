package pers.jc.flow.engine.engine;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.jc.flow.engine.Context;
import pers.jc.flow.engine.FlowNode;
import pers.jc.flow.engine.service.BeanService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description : 执行器入口
 *
 * @author jinchao.hu
 * @date 2021/6/9  10:02
 */
@Service
public class FlowEngine<T> {

    public static ThreadPoolExecutor mainThreadPool = new ThreadPoolExecutor(
            5,
            10,
            60L,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(500),
            new ThreadPoolExecutor.AbortPolicy());


    public void execute(FlowNode flowNode, RunData<T> runData, Context context) throws Exception {
        final Map<String, List<String>> nodeGroup = groupByGroupName(flowNode);
        Map<String, FlowNode.NodeConf> nodeMap = flowNode.getNodeMap();

        for (String groupName : nodeGroup.keySet()) {
            boolean needThrowExp = false;
            String throwMsg = "";
            List<String> nodeNameList = nodeGroup.get(groupName);
            // 单个节点  串行
            if (nodeNameList.size() == 1) {
                String nodeName = nodeNameList.get(0);
                FlowNodeInterface<String> detailNode = BeanService.getBeanByType(Class.forName(nodeName));
                NodeExecuteTask<String> nodeParallelTask = new NodeExecuteTask<>(detailNode, runData, context);
                try {
                    Object result = nodeParallelTask.execute();
                    context.getResultMap().put(detailNode.resultKey(), result);
                } catch (Exception e) {
                    needThrowExp = true;
                    throwMsg = e.getMessage();
                }
            } else {
                // 多个节点 并行
                List<Future<String>> resultList = new ArrayList<>();
                List<String> executeNodeNameList = new ArrayList<>();
                List<NodeExecuteTask<String>> executeTaskList = new ArrayList<>();

                for (String nodeName : nodeNameList) {
                    FlowNodeInterface<String> detailNode = BeanService.getBeanByType(Class.forName(nodeName));
                    NodeExecuteTask<String> nodeParallelTask = new NodeExecuteTask<>(detailNode, runData, context);
                    executeTaskList.add(nodeParallelTask);
                    executeNodeNameList.add(nodeName);
                    resultList.add(mainThreadPool.submit(nodeParallelTask));
                }

                for (int i = 0; i < resultList.size(); i++) {
                    String nodeName = executeNodeNameList.get(i);
                    String nodeKey = groupName + "_" + nodeName;
                    FlowNodeInterface<String> detailNode = BeanService.getBeanByType(Class.forName(nodeName));
                    FlowNode.NodeConf nodeConf = nodeMap.get(nodeKey);
                    int timeout = nodeConf.getTimeout();
                    Future<String> future = resultList.get(i);
                    try {
                        String t = future.get(timeout, TimeUnit.MILLISECONDS);
                        context.getResultMap().put(detailNode.resultKey() , t);
                    }catch (Exception e){
                        needThrowExp = true;
                        throwMsg = e.getMessage();
                    }
                }

            }
            if (needThrowExp) {
                throw new RuntimeException("Run node [" + nodeNameList + "] error. Msg :" + throwMsg);
            }
        }
    }

    /**
     * 参数
     */
    public static class RunData<T> {

        private String params;

        private T data;

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    private Map<String, List<String>> groupByGroupName(FlowNode flowNode) {
        Map<String, List<String>> nodeGroup = new LinkedHashMap<>();
        for (String nodeKey : flowNode.getNodeList()) {
            String groupName = getGroupName(nodeKey);
            String nodeName = getNodeName(nodeKey);
            if (StringUtils.isBlank(groupName)) {
                List<String> nodeNameList = new ArrayList<>();
                nodeNameList.add(nodeName);
                nodeGroup.put(nodeName, nodeNameList);
            } else {
                List<String> nodeNameList = nodeGroup.get(groupName);
                if (CollectionUtils.isEmpty(nodeNameList)) {
                    nodeNameList = new ArrayList<>();
                }
                nodeNameList.add(nodeName);
                nodeGroup.put(nodeName, nodeNameList);
            }
        }
        return nodeGroup;
    }

    private String getGroupName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[0] : null;
    }

    private String getNodeName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[1] : null;
    }
}
