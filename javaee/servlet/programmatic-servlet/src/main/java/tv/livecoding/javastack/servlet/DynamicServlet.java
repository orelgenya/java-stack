package tv.livecoding.javastack.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by OrelGenya on 14.09.2015.
 */
public class DynamicServlet extends HttpServlet {
    public static final String RESPONSE = "Привет, I'm dynamic! ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(RESPONSE);
    }

    @Override
    public String getServletInfo() {
        return "My dynamic awesome servlet!";
    }
}