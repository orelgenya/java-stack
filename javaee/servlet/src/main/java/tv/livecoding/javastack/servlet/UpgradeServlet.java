package tv.livecoding.javastack.servlet;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/15/2015.
 */
@WebServlet("/up")
public class UpgradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.upgrade(UpgradeHandler.class);
        resp.getWriter().print("Upgrade complete!\r\n\r\n");
    }

    public static class UpgradeHandler implements HttpUpgradeHandler {

        @Override
        public void init(WebConnection wc) {
            System.out.println("UpgradeHandler inited!");
            StringBuilder sb = new StringBuilder();
            final byte[] bb = new byte[256];
            int len;

            try {
                ServletInputStream sis = wc.getInputStream();
                ServletOutputStream sos = wc.getOutputStream();
                boolean end = false;
                while (!end) {
                    while (!sb.toString().endsWith("\r\n\r\n") && (len = sis.read(bb)) != -1) {
                        sb.append(new String(bb, 0, len));
                    }
                    String cmd = sb.toString();
                    if (!cmd.endsWith("\r\n\r\n")) {
                        continue;
                    }
                    cmd = cmd.substring(0, cmd.length() - 4);
                    System.out.println("Command read: " + cmd);

                    switch (cmd) {
                        case "hey":
                            sos.write("What's up!\r\n\r\n".getBytes());
                            break;
                        case "bye":
                            sos.write("See you!\r\n\r\n".getBytes());
                            end = true;
                            break;
                        default:
                            sos.write(cmd.getBytes());
                            break;
                    }
                    sb.setLength(0);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    wc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void destroy() {
            System.out.println("UpgradeHandler destroyed!");
        }
    }

    public static class UpgradeListener implements ReadListener {
        final ServletInputStream sis;
        final ServletOutputStream sos;

        final StringBuilder sb = new StringBuilder();
        final byte[] bb = new byte[256];

        public UpgradeListener(ServletInputStream sis, ServletOutputStream sos) {
            this.sis = sis;
            this.sos = sos;
        }

        @Override
        public void onDataAvailable() throws IOException {
            int len;
            while (sis.isReady() && (len = sis.read(bb)) != -1) {
                sb.append(new String(bb, 0, len));
            }
            String cmd = sb.toString();
            System.out.println("Command read: " + cmd);
            if (!cmd.endsWith("\r\n\r\n")) {
                return;
            }
            cmd = cmd.substring(0, cmd.length() - 4);
            System.out.println("Command read2: " + cmd);

            switch (cmd) {
                case "hey":
                    sos.write("What's up!\r\n\r\n".getBytes());
                    break;
                case "bye":
                    sos.write("See you!\r\n\r\n".getBytes());
                    break;
                default:
                    sos.write(cmd.getBytes());
                    break;
            }
        }

        @Override
        public void onAllDataRead() throws IOException {
            System.out.println("onAllDataRead");
        }

        @Override
        public void onError(Throwable t) {
            System.out.println("onError");
        }
    }
}
