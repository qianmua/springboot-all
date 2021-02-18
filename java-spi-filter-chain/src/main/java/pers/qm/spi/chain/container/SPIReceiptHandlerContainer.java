package pers.qm.spi.chain.container;

import pers.qm.spi.chain.handler.impl.Handler;

import java.util.*;

/**
 * description : 装载过滤器容器，spi实现
 * 单例
 *
 * @author jinchao.hu
 * @date 2021/2/18  9:29
 */
public class SPIReceiptHandlerContainer implements InvokeContainerLoad{

    private SPIReceiptHandlerContainer(){}

    private static class Instance {
        private static final SPIReceiptHandlerContainer container = new SPIReceiptHandlerContainer();
    }

    public static SPIReceiptHandlerContainer getInstance() {
        return Instance.container;
    }

    @Override
    public List<Handler> loadContainer(){
        // spi 查找实现类
        ServiceLoader<Handler> handlers = ServiceLoader.load(Handler.class);

        ArrayList<Handler> handlerArrayList = new ArrayList<>();

        Objects.requireNonNull(handlers , "handler chains empty. ");

        for (Handler handler : handlers) {
            handlerArrayList.add(handler);
        }

        return handlerArrayList;
    }

}
