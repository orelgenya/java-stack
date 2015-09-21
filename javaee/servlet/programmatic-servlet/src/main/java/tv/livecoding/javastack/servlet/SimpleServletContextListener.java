package tv.livecoding.javastack.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 * Created by Eugene Orel on 9/15/2015.
 */
@WebListener
public class SimpleServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet context initialized: " + sce.getServletContext().getContextPath());
        ServletRegistration.Dynamic registration = sce.getServletContext().addServlet("dynamic", DynamicServlet.class);
        registration.addMapping("/dynamic");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet context destroyed: " + sce.getServletContext().getContextPath());
    }
}
