package tv.livecoding.javastack.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by OrelGenya on 14.09.2015.
 */

@WebListener
public class SimpleListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request created: " + sre.getServletRequest());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request destroyed: " + sre.getServletRequest());
    }
}
