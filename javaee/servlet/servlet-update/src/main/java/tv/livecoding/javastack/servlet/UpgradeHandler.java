package tv.livecoding.javastack.servlet;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.WebConnection;
import java.io.IOException;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
public class UpgradeHandler implements HttpUpgradeHandler {

    @Override
    public void init(WebConnection wc) {
        System.out.println("UpgradeHandler initialized!");
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
