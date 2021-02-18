package pers.qm.spi.chain.chain.impl;

import pers.qm.spi.chain.handler.impl.Context;
import pers.qm.spi.chain.handler.impl.Handler;
import pers.qm.spi.chain.container.SPIReceiptHandlerContainer;

import java.util.List;

/**
 * description : 处理器链默认实现  ,,
 * netty pipeline 实现需要手动指定， 使用经典servlet 实现
 *
 * @author jinchao.hu
 * @date 2021/2/18  9:24
 */
public class DefaultChainImpl implements FilterChain {

    private int index = 0;

    private static final List<Handler> FILTER_LIST;

    static {
        FILTER_LIST = SPIReceiptHandlerContainer.getInstance().loadContainer();
    }

    @Override
    public void handler(Context ctx) {
        if (FILTER_LIST != null && FILTER_LIST.size() > 0 && index < FILTER_LIST.size()) {
            Handler handler = FILTER_LIST.get(index++);
            handler.handle(ctx , this);
        }
    }

}
