package pres.qianmuna.redis;

import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/4
 * @time 15:01
 */
public class PingTest {

    public static void main(String[] args) throws JSONException {
        //link jedis 操作redis
        Jedis jedis = new Jedis("127.0.0.1" , 6379);
        jedis.auth(""); // 没有就不设置
        //jedis 指令跟 redis一样
        System.out.println(jedis.ping());
        System.out.println(jedis.keys("*"));
//        jedis.flushDB();

        // jedis 操作事务
        JSONObject object = new JSONObject();
        object.put("hello" , "world");
        object.put("name" , "world");
        String s = object.toJSONString();
        //开启事务
        Transaction multi = jedis.multi();

        try {
            multi.set("u1" , s);
            multi.set("u2" , s);
            multi.exec();
        } catch (Exception e) {
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("u1"));
            System.out.println(jedis.get("u2"));
            jedis.close();
        }

        //关闭连接
//        jedis.close();
    }

}
