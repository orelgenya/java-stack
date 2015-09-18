package tv.livecoding.javastack.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebListener
public class SimpleHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("HttpSessionListener.sessionCreated([Id: " + se.getSession().getId() + "])");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("HttpSessionListener.sessionDestroyed([Id: " + se.getSession().getId() + "])");
    }
}
