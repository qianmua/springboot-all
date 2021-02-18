package pers.qm.spi.chain;

import org.junit.Test;
import pers.qm.spi.chain.chain.impl.DefaultChainImpl;
import pers.qm.spi.chain.chain.impl.FilterChain;
import pers.qm.spi.chain.handler.impl.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/18  10:13
 */
public class MainTest {

    @Test
    public void testM1(){
        List<Context> contexts = Builder.gen();
        for (Context context : contexts) {
            FilterChain chain = new DefaultChainImpl();
            chain.handler(context);
        }
    }

    static class Builder{
        public static List<Context> gen(){
            return new ArrayList<Context>(){{
                add(new Context("101" , "Hello world"));
                add(new Context("102" , "Java"));
                add(new Context("103" , "C/C++"));
            }};
        }
    }

}
