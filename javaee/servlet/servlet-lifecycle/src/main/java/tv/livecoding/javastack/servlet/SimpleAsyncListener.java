package tv.livecoding.javastack.servlet;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@WebListener
public class SimpleAsyncListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        HttpServletRequest request = (HttpServletRequest) event.getSuppliedRequest();
        System.out.println("AsyncListener.onComplete([" +
                request.getMethod() + ": " + request.getServletPath() + "])");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        HttpServletRequest request = (HttpServletRequest) event.getSuppliedRequest();
        System.out.println("AsyncListener.onTimeout([" +
                request.getMethod() + ": " + request.getServletPath() + ", Timeout: " +
                event.getAsyncContext().getTimeout() + "])");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        HttpServletRequest request = (HttpServletRequest) event.getSuppliedRequest();
        System.out.println("AsyncListener.onError([" +
                request.getMethod() + ": " + request.getServletPath() + ", Message: " +
                event.getThrowable().getMessage() + "])");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        HttpServletRequest request = (HttpServletRequest) event.getSuppliedRequest();
        System.out.println("AsyncListener.onStartAsync([" +
                request.getMethod() + ": " + request.getServletPath() + "])");
    }
}
