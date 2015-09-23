package tv.livecoding.javastack.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
public class DynamicFilter implements Filter {
    public static final String UTF8 = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(UTF8);
        response.setCharacterEncoding(UTF8);

        chain.doFilter(request, response);
    }
}
