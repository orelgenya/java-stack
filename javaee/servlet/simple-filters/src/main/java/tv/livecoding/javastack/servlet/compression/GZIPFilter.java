package tv.livecoding.javastack.servlet.compression;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/21/2015.
 */
@WebFilter("/gzip")
public class GZIPFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        String contentEncoding = httpReq.getHeader("content-encoding");
        String acceptEncoding = httpReq.getHeader("accept-encoding");

        if (contentEncoding != null && contentEncoding.toLowerCase().indexOf("gzip") != -1) {
            request = new GZIPRequestWrapper(httpReq);
        }

        if (acceptEncoding != null && acceptEncoding.toLowerCase().indexOf("gzip") != -1) {
            response = new GZIPResponseWrapper(httpResp);
        }

        chain.doFilter(request, response);

        if (response instanceof GZIPResponseWrapper) {
            ((GZIPResponseWrapper) response).finish();
        }
    }
}