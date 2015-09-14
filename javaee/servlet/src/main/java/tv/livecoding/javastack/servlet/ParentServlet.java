package tv.livecoding.javastack.servlet;

import tv.livecoding.javastack.servlet.samples.ChildServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by OrelGenya on 14.09.2015.
 */
@WebServlet("/p")
public class ParentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().addServlet("MyDynamicServlet", ChildServlet.class);
        resp.getWriter().print("ok!");
    }
}
