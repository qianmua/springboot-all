package pers.qm.spi.chain.container;

import pers.qm.spi.chain.handler.impl.Handler;

import java.util.List;

/**
 * description : 装载过滤器容器，spi实现
 *
 * @author jinchao.hu
 * @date 2021/2/18  9:29
 */
public class DefaultReceiptHandlerContainer implements InvokeContainerLoad{

    private DefaultReceiptHandlerContainer(){}

    private static class Instance{
        private static final DefaultReceiptHandlerContainer container = new DefaultReceiptHandlerContainer();
    }

    public static DefaultReceiptHandlerContainer getInstance() {
        return Instance.container;
    }

    @Override
    public List<Handler> loadContainer(){
        return null;
    }
}
