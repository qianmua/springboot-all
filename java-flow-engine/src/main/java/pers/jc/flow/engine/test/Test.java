package pers.jc.flow.engine.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.jc.flow.engine.Context;
import pers.jc.flow.engine.FlowNode;
import pers.jc.flow.engine.engine.FlowEngine;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/9  13:07
 */
@Component
public class Test {

    @Autowired
    private FlowEngine flowEngine;

    @PostConstruct
    public void m1() throws Exception {
        FlowNode testFlow = Flow.getTestFlow();
        FlowEngine.RunData<String> runData = new FlowEngine.RunData<>();
        runData.setData("world");
        runData.setParams("hello");
        Context context = new Context();
        flowEngine.execute(testFlow , runData , context);
        Map<String, Object> resultMap = context.getResultMap();
        System.out.println(resultMap);
    }


    public static class Flow{
        private static FlowNode testFlow = new FlowNode();

        static {
            testFlow.add(Node1.class , new FlowNode.NodeConf());
            testFlow.add(Node2.class , new FlowNode.NodeConf());
            testFlow.add("3" ,Node1.class , new FlowNode.NodeConf());
            testFlow.add("3", Node1.class , new FlowNode.NodeConf());
        }

        public static FlowNode getTestFlow() {
            return testFlow;
        }
    }
}
