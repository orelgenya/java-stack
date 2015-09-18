package tv.livecoding.javastack.servlet;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebListener
public class SimpleServletRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequestAttributeListener.attributeAdded([" +
                srae.getName() + ": " + srae.getValue() + "])");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequestAttributeListener.attributeRemoved([" +
                srae.getName() + ": " + srae.getValue() + "])");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequestAttributeListener.attributeReplaced([" +
                srae.getName() + ": " + srae.getValue() + "])");
    }
}
