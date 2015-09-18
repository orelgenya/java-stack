package tv.livecoding.javastack.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebListener
public class SimpleHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("HttpSessionAttributeListener.attributeAdded([Id: " +
                event.getSession().getId() + ", " + event.getName() + ": " + event.getValue() + "])");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("HttpSessionAttributeListener.attributeRemoved([Id: " +
                event.getSession().getId() + ", " + event.getName() + ": " + event.getValue() + "])");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("HttpSessionAttributeListener.attributeReplaced([Id: " +
                event.getSession().getId() + ", " + event.getName() + ": " + event.getValue() + "])");
    }
}
