package tv.livecoding.javastack.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/15/2015.
 */
@WebServlet("/up")
public class UpgradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.upgrade(UpgradeHandler.class);
        resp.getWriter().print("Upgrade complete!\r\n\r\n");
    }
}
