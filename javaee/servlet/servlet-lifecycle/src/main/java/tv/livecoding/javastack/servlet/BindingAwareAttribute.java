package tv.livecoding.javastack.servlet;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
public class BindingAwareAttribute implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("HttpSessionBindingListener.valueBound([Id: " +
                event.getSession().getId() + ", " + event.getName() + ": " + event.getValue() + "])");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("HttpSessionBindingListener.valueUnbound([Id: " +
                event.getSession().getId() + ", " + event.getName() + ": " + event.getValue() + "])");
    }
}
