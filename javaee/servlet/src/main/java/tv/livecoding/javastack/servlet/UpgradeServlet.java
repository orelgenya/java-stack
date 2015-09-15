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
@WebServlet("up")
public class UpgradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.upgrade(UpgradeHandler.class);
        resp.getWriter().print("Upgrade complete!");
    }

    class UpgradeHandler implements HttpUpgradeHandler {

        @Override
        public void init(WebConnection wc) {
            System.out.println("UpgradeHandler inited!");
            try (ServletInputStream sis = wc.getInputStream();
                    ServletOutputStream sos = wc.getOutputStream();) {
                 sis.setReadListener(new UpgradeListener(sis, sos));
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void destroy() {
            System.out.println("UpgradeHandler destroyed!");
        }
    }

    class UpgradeListener implements ReadListener {
        final ServletInputStream sis;
        final ServletOutputStream sos;
        final byte[] bb = new byte[256];

        public UpgradeListener(ServletInputStream sis, ServletOutputStream sos) {
            this.sis = sis;
            this.sos = sos;
        }

        @Override
        public void onDataAvailable() throws IOException {
            int len = 0;
            while (sis.isReady()) {
                len = sis.read(bb, len, bb.length - len);
//                if (len > 0) {
//
//                }
            }
            String cmd = new String(bb, "UTF-8");
            System.out.println("Command read: " + cmd);
//            if (cmd.equals("end")) {
//            }
            sos.write(bb);
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
