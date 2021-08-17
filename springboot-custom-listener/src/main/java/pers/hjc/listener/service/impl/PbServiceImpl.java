package pers.hjc.listener.service.impl;

import org.springframework.stereotype.Service;
import pers.hjc.listener.config.NotifySender;
import pers.hjc.listener.service.PbService;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/16  10:22
 */
@Service
public class PbServiceImpl implements PbService {

    private final NotifySender notifySender;

    public PbServiceImpl(NotifySender notifySender) {
        this.notifySender = notifySender;
    }

    @Override
    public void send() {
        notifySender.sendMail("C");
    }
}
