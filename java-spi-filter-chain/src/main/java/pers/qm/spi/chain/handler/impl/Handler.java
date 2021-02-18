package pers.qm.spi.chain.handler.impl;

import pers.qm.spi.chain.chain.impl.FilterChain;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/18  9:20
 */
public interface Handler {
    void handle(Context ctx , FilterChain chain);
}
