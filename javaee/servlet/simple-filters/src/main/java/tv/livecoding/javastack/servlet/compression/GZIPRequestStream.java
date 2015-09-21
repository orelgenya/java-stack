package tv.livecoding.javastack.servlet.compression;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Created by Eugene Orel on 9/21/2015.
 */
public class GZIPRequestStream extends ServletInputStream {

    private GZIPInputStream gis;
    private ReadListener listener;

    public GZIPRequestStream(HttpServletRequest request) throws IOException {
        this.gis = new GZIPInputStream(request.getInputStream());
    }

    @Override
    public boolean isFinished() {
        try {
            return gis.available() == 0;
        } catch (IOException ex) {
            listener.onError(ex);
            return false;
        }
    }

    @Override
    public boolean isReady() {
        try {
            return gis.available() == 1;
        } catch (IOException ex) {
            listener.onError(ex);
            return false;
        }
    }

    @Override
    public void setReadListener(ReadListener listener) {
        if (this.listener == null) {
            throw new IllegalStateException("ReadListener is already defined!");
        }
        this.listener = listener;
        try {
            this.listener.onDataAvailable();
        } catch (IOException ex) {
            this.listener.onError(ex);
        }
    }

    @Override
    public int read() throws IOException {
        int b = gis.read();
        if (b == -1 && listener != null) {
            listener.onAllDataRead();
        }
        return b;
    }
}
