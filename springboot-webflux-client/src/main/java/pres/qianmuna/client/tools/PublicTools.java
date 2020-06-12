package pres.qianmuna.client.tools;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/12  12:46
 * @description :
 */
public class PublicTools {

    /**
     * 带下标的 遍历
     * @param consumer
     * @param <T>
     * @return
     */
    public static <T> Consumer<T> consumerWithIndex(BiConsumer<T, Integer> consumer) {
        class Obj {
            int i;
        }
        Obj obj = new Obj();
        return t -> {
            int index = obj.i++;
            consumer.accept(t, index);
        };
    }

}
