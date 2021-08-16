package pers.hjc.listener.notify;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/16  10:03
 */
public class MailNotifyEvent extends ApplicationContextEvent {

    private String params;

    /**
     * Create a new ContextStartedEvent.
     *
     * @param source the {@code ApplicationContext} that the event is raised for
     *               (must not be {@code null})
     */
    public MailNotifyEvent(ApplicationContext source , String params) {
        super(source);
        this.params = params;
    }

    public String sendMailNotify(){
        return this.params;
    }

}
