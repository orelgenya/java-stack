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
            this.listener.onWritePossible();
        } catch (IOException ex) {
            listener.onError(ex);
        }
    }

    @Override
    public void write(int b) throws IOException {
        gos.write(b);
        onWritePossible();
        System.out.println(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        gos.write(b);
        onWritePossible();
        System.out.println(new String(b));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        gos.write(b, off, len);
        onWritePossible();
        System.out.println(new String(b, off, len));
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
        gos.finish();
    }

    @Override
    public void print(String s) throws IOException {
        gos.write(s.getBytes());
        System.out.println(s);
    }

    @Override
    public void print(boolean b) throws IOException {
        super.print(b);
        System.out.println(b);
    }

    @Override
    public void print(char c) throws IOException {
        super.print(c);
        System.out.println(c);
    }

    @Override
    public void print(int i) throws IOException {
        super.print(i);
        System.out.println(i);
    }

    @Override
    public void print(long l) throws IOException {
        super.print(l);
        System.out.println(l);
    }

    @Override
    public void print(float f) throws IOException {
        super.print(f);
        System.out.println(f);
    }

    @Override
    public void print(double d) throws IOException {
        super.print(d);
        System.out.println(d);
    }

    @Override
    public void println() throws IOException {
        super.println();
        System.out.println("println");
    }

    @Override
    public void println(String s) throws IOException {
        super.println(s);
        System.out.println(s);
    }

    @Override
    public void println(boolean b) throws IOException {
        super.println(b);
        System.out.println(b);
    }

    @Override
    public void println(char c) throws IOException {
        super.println(c);
        System.out.println(c);
    }

    @Override
    public void println(int i) throws IOException {
        super.println(i);
        System.out.println(i);
    }

    @Override
    public void println(long l) throws IOException {
        super.println(l);
        System.out.println(l);
    }

    @Override
    public void println(float f) throws IOException {
        super.println(f);
        System.out.println(f);
    }

    @Override
    public void println(double d) throws IOException {
        super.println(d);
        System.out.println(d);
    }
}
