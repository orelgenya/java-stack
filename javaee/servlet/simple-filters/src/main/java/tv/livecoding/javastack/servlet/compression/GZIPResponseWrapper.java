package tv.livecoding.javastack.servlet.compression;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Eugene Orel on 9/21/2015.
 */
public class GZIPResponseWrapper extends HttpServletResponseWrapper {
    private HttpServletResponse response;
    private PrintWriter writer;
    private GZIPResponseStream grs;
    private boolean compress = true;

    public GZIPResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    @Override
    public void setContentLength(int len) {
        // ignore
    }

    @Override
    public void addHeader(String name, String value) {
        if (!"content-length".equalsIgnoreCase(name)) {
            super.addHeader(name, value);
        }
    }

    @Override
    public void setStatus(int sc) {
        if (sc < 200 || sc >= 300) {
            compress = false;
        }
        super.setStatus(sc);
    }

    @Override
    public void setIntHeader(String name, int value) {
        if (!"content-length".equalsIgnoreCase(name)) {
            super.setIntHeader(name, value);
        }
    }

    @Override
    public void setHeader(String name, String value) {
        if (!"content-length".equalsIgnoreCase(name)) {
            super.setHeader(name, value);
        }
    }

    @Override
    public void addIntHeader(String name, int value) {
        if (!"content-length".equalsIgnoreCase(name)) {
            super.addIntHeader(name, value);
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        if (writer != null) {
            writer.flush();
        }
        if (grs != null) {
            grs.finish();
        } else {
            getResponse().flushBuffer();
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (grs != null) {
            grs.resetBuffer();
        }
        writer = null;
        grs = null;
        compress = true;
    }

    @Override
    public void resetBuffer() {
        super.resetBuffer();
        if (grs != null) {
            grs.resetBuffer();
        }
        writer = null;
        grs = null;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        resetBuffer();
        super.sendError(sc, msg);
    }

    @Override
    public void sendError(int sc) throws IOException {
        resetBuffer();
        super.sendError(sc);
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        resetBuffer();
        super.sendRedirect(location);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new PrintWriter(getOutputStream());
        }
        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (!response.isCommitted() && compress) {
            if (grs == null) {
                grs = new GZIPResponseStream(response);
            }
            return grs;
        } else {
            return response.getOutputStream();
        }
    }
}
