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

/**
 * Created by OrelGenya on 11.09.2015.
 */
@WebServlet(value = {"/SimpleServlet", "/s"},
        initParams = {
                @WebInitParam(name = SimpleServlet.INITIAL_PARAM_TYPE, value = SimpleServlet.INITIAL_PARAM_VALUE)
        })
public class SimpleServlet extends HttpServlet {
    public static final String INITIAL_PARAM_VALUE = "initial параметр";
    public static final String INITIAL_PARAM_TYPE = "type";
    public static final String POST_RESPONSE = "мой post";
    public static final String COOKIE_NAME = "cookie";
    public static final String COOKIE_VALUE = "css-secure";
    public static final int PAUSE_SECONDS = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(getInitParameter(INITIAL_PARAM_TYPE));

        // setting cookie
        Cookie cookie = new Cookie(COOKIE_NAME, COOKIE_VALUE);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("pause".equals(action)) {
            throw new UnavailableException("Service currently unavailable!", PAUSE_SECONDS);
        }
        resp.getWriter().print(POST_RESPONSE);
    }
}
