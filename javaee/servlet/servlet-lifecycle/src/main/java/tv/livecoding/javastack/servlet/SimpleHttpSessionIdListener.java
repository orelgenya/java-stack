package tv.livecoding.javastack.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebListener
public class SimpleHttpSessionIdListener implements HttpSessionIdListener {
    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        System.out.println("HttpSessionIdListener.sessionIdChanged([Id: " +
                event.getSession().getId() + ", OldId: " + oldSessionId + "])");
    }
}
