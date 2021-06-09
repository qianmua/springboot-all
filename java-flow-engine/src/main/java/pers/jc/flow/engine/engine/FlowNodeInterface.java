package pers.jc.flow.engine.engine;

import pers.jc.flow.engine.Context;

/**
 * description : 执行Call方法 node解点
 *
 * @author jinchao.hu
 * @date 2021/6/9  11:25
 */
public interface FlowNodeInterface<T> {

    /**
     * node 执行方法
     */
    T invokeNode(FlowEngine.RunData runData , Context context);

    /**
     * node 执行完
     */
    void afterInvoke(FlowEngine.RunData runData , Context context);

    /**
     * 从 context中获取node结果 key.
     */
    String resultKey();
}
