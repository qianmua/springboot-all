package pers.qm.spi.chain.handler.impl;

import pers.qm.spi.chain.chain.impl.FilterChain;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/18  9:21
 */
public class H2 implements Handler {
    @Override
    public void handle(Context ctx, FilterChain chain) {
        System.out.println(" this is h2" + ctx.getCode() + ctx.getMsg());

        chain.handler(ctx);
    }
}
