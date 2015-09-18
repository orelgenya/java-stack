package tv.livecoding.javastack.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebServlet("/simple")
public class SimpleServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("requestAttribute", "simple");
        req.setAttribute("requestAttribute", "more complex");
        req.removeAttribute("requestAttribute");

        req.getSession().setAttribute("sessionAwareAttribute", new SessionAwareAttribute());
        req.getSession().setAttribute("bindingAwareAttribute", new BindingAwareAttribute());
        req.getSession().invalidate();

        resp.getWriter().print("simple response");
    }
}
