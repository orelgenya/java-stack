package tv.livecoding.javastack.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

/**
 * Created by Eugene Orel on 9/15/2015.
 */
@WebListener
public class SimpleServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet context initialized: " + sce.getServletContext().getContextPath());
        ServletRegistration.Dynamic sreg = sce.getServletContext().addServlet("servlet", DynamicServlet.class);
        sreg.addMapping("/dynamic");

        FilterRegistration.Dynamic freg = sce.getServletContext().addFilter("filter", DynamicFilter.class);
        freg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/dynamic");

        sce.getServletContext().addListener(DynamicListener.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet context destroyed: " + sce.getServletContext().getContextPath());
    }
}
