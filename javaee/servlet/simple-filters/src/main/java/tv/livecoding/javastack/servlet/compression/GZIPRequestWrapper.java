package tv.livecoding.javastack.servlet.compression;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Eugene Orel on 9/21/2015.
 */
public class GZIPRequestWrapper extends HttpServletRequestWrapper {
    private ServletInputStream sis;
    private BufferedReader reader;

    public GZIPRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.sis = new GZIPRequestStream(request);
        this.reader = new BufferedReader(new InputStreamReader(this.sis));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return sis;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return reader;
    }
}
