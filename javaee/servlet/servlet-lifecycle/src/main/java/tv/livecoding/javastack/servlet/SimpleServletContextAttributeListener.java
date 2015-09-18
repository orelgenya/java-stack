package tv.livecoding.javastack.servlet;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebListener
public class SimpleServletContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("ServletContextAttributeListener.attributeAdded([" +
                event.getName() + ": " + event.getValue() + "])");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("ServletContextAttributeListener.attributeRemoved([" +
                event.getName() + ": " + event.getValue() + "])");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("ServletContextAttributeListener.attributeReplaced([" +
                event.getName() + ": " + event.getValue() + "])");
    }
}
