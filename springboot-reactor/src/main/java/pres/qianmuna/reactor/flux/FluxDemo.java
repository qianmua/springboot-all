package pres.qianmuna.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/1  23:19
 * @description :
 */
public class FluxDemo {

    public static void main(String[] args) {
        print("run");
        //  发布
        Flux.just("A" , "B" , "C")
                // 线程池切换
                .publishOn(Schedulers.elastic())
                // 其他操作符
                .map( v1 -> v1+ "consu -. ")
                // 下游消费
//                .subscribe(FluxDemo::print);
                // 重载方法
                // 消费   异常处理    完成操作（回调）
                // 注意 并发 编程
                // 主线程 退出时 回调 可能不会 执行
                // 注意问题哦
//                .subscribe(FluxDemo::print , FluxDemo::print , () -> System.out.println("end"));
                // 背压操作
                .subscribe(FluxDemo::print ,    // onNext
                        FluxDemo::print ,   //  onError
                        () -> System.out.println("end"),    //onComp
                        subsp -> {  //onSubscribe
                            //消费3了
                            //传播背压 消费
                            // 请求 元素 数量
                            subsp.request(3);
                            // 取消上游 数据 传递
                            //subsp.cancel();
                        });


        // mono // 同理

    }

    private static void print(Object o){
        String name = Thread.currentThread().getName();
        System.out.println("thread[" + name + "]\t value's: " + o.toString());
    }
}
