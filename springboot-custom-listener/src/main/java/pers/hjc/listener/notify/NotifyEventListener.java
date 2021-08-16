package pers.hjc.listener.notify;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/16  10:06
 */
@Service
public class NotifyEventListener implements ApplicationListener<MailNotifyEvent> {

    @Override
    public void onApplicationEvent(MailNotifyEvent event) {
        // 监听到事件 然后 调用
        String mailNotify = event.sendMailNotify();

        System.err.println(mailNotify);
    }
}
