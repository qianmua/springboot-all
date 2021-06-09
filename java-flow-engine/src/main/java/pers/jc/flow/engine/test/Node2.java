package pers.jc.flow.engine.test;

import org.springframework.stereotype.Service;
import pers.jc.flow.engine.Context;
import pers.jc.flow.engine.engine.FlowEngine;
import pers.jc.flow.engine.engine.FlowNodeInterface;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/9  12:40
 */
@Service
public class Node2 implements FlowNodeInterface<String> {

    @Override
    public String invokeNode(FlowEngine.RunData runData, Context context) {
        System.out.println("running invoke  [" + runData.getParams());
        return runData.getParams();
    }

    @Override
    public void afterInvoke(FlowEngine.RunData runData, Context context) {
        System.out.println("running after  [" + runData.getParams());
    }

    @Override
    public String resultKey() {
        return "node2";
    }
}
