package pers.qm.spi.chain.chain.impl;

import pers.qm.spi.chain.handler.impl.Context;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/18  9:20
 */
public interface FilterChain {
    void handler(Context ctx);

    default void fireNext(Context ctx){ }
}
