package pers.hjc.listener.notify;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import pers.hjc.listener.filter.Handle;

import java.util.List;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/16  10:06
 */
@Service
public class NotifyEventListener implements ApplicationListener<MailNotifyEvent> {

    private final List<Handle> handleList;

    public NotifyEventListener(List<Handle> handleList) {
        this.handleList = handleList;
    }

    @Override
    public void onApplicationEvent(MailNotifyEvent event) {
        // 监听到事件 然后 调用
        String mailNotify = event.sendMailNotify();
        for (Handle handle : handleList) {
            if (handle.handle(mailNotify))
                return;
        }
    }
}
