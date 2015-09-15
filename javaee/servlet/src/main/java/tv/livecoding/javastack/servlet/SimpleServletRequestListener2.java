package tv.livecoding.javastack.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;

/**
 * Created by OrelGenya on 14.09.2015.
 */

@WebListener
public class SimpleServletRequestListener2 implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        System.out.println("Request destroyed: " + request.getServletPath());
//        for (Enumeration<String> headers = request.getHeaderNames(); headers.hasMoreElements();) {
//            String header = headers.nextElement();
//            System.out.println(header + ": " + request.getHeader(header));
//        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request created: " + ((HttpServletRequest) sre.getServletRequest()).getServletPath());
    }
}
