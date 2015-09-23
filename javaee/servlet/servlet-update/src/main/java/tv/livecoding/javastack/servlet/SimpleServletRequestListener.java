package tv.livecoding.javastack.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by OrelGenya on 14.09.2015.
 */

@WebListener
public class SimpleServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request destroyed: " + ((HttpServletRequest) sre.getServletRequest()).getServletPath());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request created: " + ((HttpServletRequest) sre.getServletRequest()).getServletPath());
    }
}
