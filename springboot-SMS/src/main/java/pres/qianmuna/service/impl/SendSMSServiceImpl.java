package pres.qianmuna.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;
import pres.qianmuna.service.SendSMSService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/1
 * @time 22:06
 */
@Service
public class SendSMSServiceImpl implements SendSMSService {

    @Override
    public boolean send(String phone, String templateCode, Map<String, Object> code) {
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
        request.putQueryParameter("PhoneNumbers", phone);
        //模板
        request.putQueryParameter("TemplateCode", templateCode);
        //短信验证码
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));


        try {
            CommonResponse response = client.getCommonResponse(request);
            // 响应
            System.out.println(response.getData());
            response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}
