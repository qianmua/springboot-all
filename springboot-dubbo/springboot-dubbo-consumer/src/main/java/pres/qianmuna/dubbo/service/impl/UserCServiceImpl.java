package pres.qianmuna.dubbo.service.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.cluster.Constants;
import org.springframework.stereotype.Component;
import pres.qianmuna.dubbo.service.UserCService;
import pres.qianmuna.dubbo.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 19:55
 */
@Component
public class UserCServiceImpl implements UserCService {

    /**
     * Reference
     * 服务之间检查
     * check
     * true 检测 如果没有 检测到服务 会启动失败 // 要是循环依赖呢？
     * false 关闭检测  在服务上线时会自动链接
     *
     * loadbalance
     * 负载均衡机制
     *
     * 默认  轮询
     *
     */
    //注册中心引用服务
    @Reference( check = false , loadbalance = Constants.LOADBALANCE_KEY)
    private UserService userService;


    @Override
    public List<Map<String, Object>> queryAll() {
        return userService.queryAll();
//        return null;
    }

}
