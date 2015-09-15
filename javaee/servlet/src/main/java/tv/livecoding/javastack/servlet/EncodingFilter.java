package tv.livecoding.javastack.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/14/2015.
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
    public static final String UTF8 = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(UTF8);
        response.setCharacterEncoding(UTF8);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}