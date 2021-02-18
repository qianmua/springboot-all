package pers.qm.spi.chain.container;

import pers.qm.spi.chain.handler.impl.Handler;

import java.util.List;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/18  9:33
 */
public interface InvokeContainerLoad {
    List<Handler> loadContainer();
}
