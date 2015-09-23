package tv.livecoding.javastack.servlet;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/15/2015.
 */
@WebServlet(value = "async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Resource(lookup = "java:comp/DefaultManagedExecutorService")
    ManagedExecutorService es;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext ac = req.startAsync();
        ac.addListener(new AsyncListener());
        es.execute(new AsyncService(ac));
    }

}
