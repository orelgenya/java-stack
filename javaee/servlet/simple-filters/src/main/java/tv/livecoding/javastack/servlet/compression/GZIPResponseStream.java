package tv.livecoding.javastack.servlet.compression;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Eugene Orel on 9/21/2015.
 */
public class GZIPResponseStream extends ServletOutputStream {
    private WriteListener listener;
    private GZIPOutputStream gos;
    private HttpServletResponse response;

    public GZIPResponseStream(HttpServletResponse response) throws IOException {
        this.response = response;
        this.gos = new GZIPOutputStream(response.getOutputStream());
        response.addHeader("Content-Encoding", "gzip");
    }

    public void resetBuffer() {
        if (gos != null && !response.isCommitted()) {
            response.setHeader("Content-Encoding", null);
        }
        gos = null;
    }

    @Override
    public boolean isReady() {
        return gos != null;
    }

    @Override
    public void setWriteListener(WriteListener listener) {
        this.listener = listener;
        onWritePossible();
    }

    public void onWritePossible() {
        try {
            if (this.listener != null) {
                this.listener.onWritePossible();
            }
        } catch (IOException ex) {
            listener.onError(ex);
        }
    }

    @Override
    public void write(int b) throws IOException {
        gos.write(b);
        onWritePossible();
    }

    @Override
    public void write(byte[] b) throws IOException {
        gos.write(b);
        onWritePossible();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        gos.write(b, off, len);
        onWritePossible();
    }

    @Override
    public void flush() throws IOException {
        gos.flush();
    }

    @Override
    public void close() throws IOException {
        finish();
        gos.close();
    }

    public void finish() throws IOException {
        flush();
        gos.finish();
    }
}
