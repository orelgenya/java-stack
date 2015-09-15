package tv.livecoding.javastack.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Eugene Orel on 9/15/2015.
 */
@WebServlet(value = "async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    public static final String RESPONSE = "async";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext ac = req.startAsync();
        ac.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("AsyncService was completed");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {

            }

            @Override
            public void onError(AsyncEvent event) throws IOException {

            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                System.out.println("AsyncService onStart");
            }
        });
        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(new AsyncService(ac));
    }

    class AsyncService implements Runnable {
        AsyncContext ac;

        public AsyncService(AsyncContext ac) {
            this.ac = ac;
        }

        @Override
        public void run() {
            System.out.println("AsyncService was run");
            try {
                ac.getResponse().getWriter().print(RESPONSE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ac.complete();
        }
    }
}
