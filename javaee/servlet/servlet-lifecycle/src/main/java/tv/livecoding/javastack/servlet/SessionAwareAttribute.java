package tv.livecoding.javastack.servlet;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
public class SessionAwareAttribute implements HttpSessionActivationListener {
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("HttpSessionActivationListener.sessionWillPassivate([Id: " + se.getSession().getId() + "])");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("HttpSessionActivationListener.sessionDidActivate([Id: " + se.getSession().getId() + "])");
    }
}
