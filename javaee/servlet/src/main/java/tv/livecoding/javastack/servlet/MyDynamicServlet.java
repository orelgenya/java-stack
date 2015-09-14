package tv.livecoding.javastack.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by OrelGenya on 14.09.2015.
 */
public class MyDynamicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("I'm dynamic!");
    }

    @Override
    public String getServletInfo() {
        return "My dynamic failure servlet!";
    }
}