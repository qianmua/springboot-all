package pres.qianmuna.async.service.impl;

import org.springframework.stereotype.Service;
import pres.qianmuna.async.service.AService;
import pres.qianmuna.async.service.BService;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/7/16  20:23
 */
@Service
public class ABServiceImpl implements AService, BService {

    @Override
    public void method(String params) {
        System.err.println("this params val -> " + params);
    }
}
