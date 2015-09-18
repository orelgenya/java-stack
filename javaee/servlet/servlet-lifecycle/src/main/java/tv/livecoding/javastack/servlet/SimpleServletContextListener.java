package tv.livecoding.javastack.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebListener
public class SimpleServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextListener.contextInitialized([Url: " +
                sce.getServletContext().getContextPath() + "])");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContextListener.contextDestroyed([Url: " +
                sce.getServletContext().getContextPath() + "])");
    }
}
