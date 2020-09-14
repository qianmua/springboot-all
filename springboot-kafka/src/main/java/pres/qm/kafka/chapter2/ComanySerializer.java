package pres.qm.kafka.chapter2;

import org.apache.kafka.common.serialization.Serializer;
import pres.qm.kafka.msg.MsgPo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/13  22:27
 * @description :
 */
public class ComanySerializer implements Serializer<MsgPo> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, MsgPo msgPo) {
        if (msgPo == null)
            return null;
        byte[]  name , address;
        try {
            if (msgPo.getCode() != null){
                name = msgPo.getCode().getBytes(StandardCharsets.UTF_8);
            }else {
                name = new byte[0];
            }
            if (msgPo.getMessage() != null)
                address = msgPo.getMessage().getBytes(StandardCharsets.UTF_8);
            else
                address = new byte[0];

            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);

            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(address.length);
            buffer.put(address);
//            return buffer.array();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];
    }
}
