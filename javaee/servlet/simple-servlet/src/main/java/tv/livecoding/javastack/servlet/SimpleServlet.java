package tv.livecoding.javastack.servlet;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by OrelGenya on 11.09.2015.
 */
@WebServlet(value = {"/simple"},
        initParams = {
                @WebInitParam(name = SimpleServlet.INITIAL_PARAM_TYPE, value = SimpleServlet.INITIAL_PARAM_VALUE)
        })
public class SimpleServlet extends HttpServlet {
    public static final String INITIAL_PARAM_VALUE = "servlets_count = ";
    public static final String INITIAL_PARAM_TYPE = "type";
    private static final AtomicInteger servletsCount = new AtomicInteger();
    public static final int PAUSE_SECONDS = 3;

    @Override
    public void init() throws ServletException {
        servletsCount.incrementAndGet();
    }

    @Override
    public void destroy() {
        servletsCount.decrementAndGet();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(getInitParameter(INITIAL_PARAM_TYPE) + servletsCount);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new UnavailableException("Service currently unavailable!", PAUSE_SECONDS);
    }
}
