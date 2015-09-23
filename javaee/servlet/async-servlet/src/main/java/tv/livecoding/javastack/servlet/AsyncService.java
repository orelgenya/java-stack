package tv.livecoding.javastack.servlet;

import javax.servlet.AsyncContext;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
public class AsyncService implements Runnable {
    public static final String RESPONSE = "async";
    private final AsyncContext ac;

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
