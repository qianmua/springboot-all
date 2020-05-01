package pres.qianmuna;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/1
 * @time 21:45
 */
@SpringBootTest
public class SMSTestApplication {


    @Test
    void TestInfo(){
        // id 密码 连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);


        // 构建请求
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        //官方版本
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        /*自定义参数*/

        //签名
        request.putQueryParameter("SignName", "谦牧呐");
        //发送
        request.putQueryParameter("PhoneNumbers", "18111998111");
        //模板
        request.putQueryParameter("TemplateCode", "SMS_********");
        //短信验证码
        HashMap<String, Object> map = new HashMap<>();
        map.put("code" , 1234);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));


        try {
            CommonResponse response = client.getCommonResponse(request);
            // 响应
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
