package pers.jc.flow.engine.engine;

import pers.jc.flow.engine.Context;

import java.util.concurrent.Callable;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/9  11:24
 */
public class NodeExecuteTask<T> implements Callable<T> {

    private final FlowNodeInterface<T> flowNodeInterface;
    private final FlowEngine.RunData runData;
    private final Context context;

    public NodeExecuteTask(FlowNodeInterface<T> flowNodeInterface, FlowEngine.RunData runData, Context context) {
        this.flowNodeInterface = flowNodeInterface;
        this.runData = runData;
        this.context = context;
    }

    @Override
    public T call() throws Exception {
        return execute();
    }

    public T execute() {
        try {
            T invokeNode = flowNodeInterface.invokeNode(runData, context);
            flowNodeInterface.afterInvoke(runData , context);
            return invokeNode;
        }catch (Throwable t){
            throw t;
        }
    }
}
