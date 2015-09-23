package tv.livecoding.javastack.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
@WebServlet(value = "/base", asyncSupported = true)
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "'action' parameter is expected!");
            return;
        }
        resp.getWriter().print("Hello from ");
        if ("include".equals(action)) {
            req.getServletContext().getRequestDispatcher("/include").include(req, resp);
        } else if ("forward".equals(action)) {
            req.getServletContext().getRequestDispatcher("/forward").forward(req, resp);
        } else if ("static".equals(action)) {
            req.getServletContext().getRequestDispatcher("/text.txt").include(req, resp);
        } else if ("async".equals(action)) {
            AsyncContext ctx = req.startAsync();
            ctx.dispatch("/include");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown 'action' parameter value!");
            return;
        }
        resp.getWriter().print("!");
    }
}
