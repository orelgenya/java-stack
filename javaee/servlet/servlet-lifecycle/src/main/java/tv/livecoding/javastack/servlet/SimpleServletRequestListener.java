package tv.livecoding.javastack.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebListener
public class SimpleServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest sr = (HttpServletRequest) sre.getServletRequest();
        System.out.println("ServletRequestListener.requestDestroyed([" +
                sr.getMethod() + ": " + sr.getServletPath() + "])");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest sr = (HttpServletRequest) sre.getServletRequest();
        System.out.println("ServletRequestListener.requestInitialized([" +
                sr.getMethod() + ": " + sr.getServletPath() + "])");
    }
}
