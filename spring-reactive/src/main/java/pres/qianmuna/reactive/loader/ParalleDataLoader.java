package pres.qianmuna.reactive.loader;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/30  22:36
 * @description :
 */
public class ParalleDataLoader {

    /**
     * 并行计算
     *
     * // 方法之间 依赖
     * 问题？
     *
     * future 之间 依赖？    java8 弥补？
     *
     *
     */
    protected void doLoad(){
        // 线程池
        ExecutorService service = Executors.newFixedThreadPool(3);
        CompletionService service1 = new ExecutorCompletionService<>(service);
        // 计算
        // 耗时 1s 2s 3s
        //  max 3s
        service1.submit(null , null);
        service1.submit(null , null);
        service1.submit(null , null);

        // 等待 完成
        int count = 0;
        while (count < 3){
            if ( service1.poll() != null){
                count ++;
            }
        }

        service.shutdown();
    }

    public static void main(String[] args) {
        // 并行
        // 串行 到 并行

        new ParalleDataLoader().doLoad();
    }
}
