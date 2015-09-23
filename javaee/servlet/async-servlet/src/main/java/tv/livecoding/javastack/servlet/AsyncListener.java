package tv.livecoding.javastack.servlet;

import javax.servlet.AsyncEvent;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
public class AsyncListener implements javax.servlet.AsyncListener {
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("AsyncService was completed");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("AsyncService onTimeout");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("AsyncService onError");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("AsyncService onStart");
    }
}
