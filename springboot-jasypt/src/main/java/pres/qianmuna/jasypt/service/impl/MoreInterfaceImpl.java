package pres.qianmuna.jasypt.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pres.qianmuna.jasypt.service.MoreAInterface;
import pres.qianmuna.jasypt.service.MoreBInterface;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/5/25  10:24
 */
@Service
public class MoreInterfaceImpl implements MoreAInterface, MoreBInterface {

    private final Logger logger = LoggerFactory.getLogger(MoreInterfaceImpl.class);

    @Override
    public void methodAInA() {
        logger.info("{}" , "A");
    }

    @Override
    public void methodAInB() {
        logger.info("{}" , "B");
    }
}
