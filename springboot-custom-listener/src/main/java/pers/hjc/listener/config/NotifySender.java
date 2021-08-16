package pers.hjc.listener.config;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import pers.hjc.listener.notify.MailNotifyEvent;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/16  10:08
 */
@Service
public class NotifySender {

    private final ApplicationContext applicationContext;

    public NotifySender(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void sendMail(String msg) {
        MailNotifyEvent notifyEvent = new MailNotifyEvent(applicationContext, msg);
        applicationContext.publishEvent(notifyEvent);
    }
}
