package pres.qianmuna.reactive.loader;

import java.util.concurrent.CompletableFuture;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/30  23:10
 * @description :
 */
public class ChainDataLoader {

    protected void doLoad(){
        //
        CompletableFuture
                .runAsync(null)
                .thenRun(null)
                .thenRun(null)
                .whenComplete( (v1 , v2) -> System.out.println("over")).join();
        // join
        // 等待完成
    }

    public static void main(String[] args) {
        // 串行
        // 一定程度 绕过了
        // 链式 方式
        // 解决 future 数据 返回 问题
        new ChainDataLoader().doLoad();
    }
}
