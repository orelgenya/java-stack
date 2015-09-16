package tv.livecoding.javastack.servlet;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eugene Orel on 9/15/2015.
 */
@RunWith(Arquillian.class)
public class UpgradeServletTest {

    @ArquillianResource
    private URL base;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        UpgradeServlet.class,
                        SimpleServletRequestListener2.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Test
//    @Ignore
    public void testUpgrade() throws IOException {
//        System.out.println(base.getHost());
//        System.out.println(base.getPath());
//        System.out.println(base);
        Socket socket = new Socket(base.getHost(), base.getPort());
        try (InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream()) {
            writeCommand(os, "GET " + base.getPath() + "up HTTP/1.1\r\nConnection: upgrade\r\n\r\n");

            String response = readResponse(is);
            assertTrue(response.startsWith("HTTP/1.1 101 Switching Protocols\r\n"));

            writeCommand(os, "hey\r\n\r\n");
            assertEquals("What's up!\r\n\r\n", readResponse(is));

            writeCommand(os, "bye\r\n\r\n");
            assertEquals("See you!\r\n\r\n", readResponse(is));
        }
    }

    private void writeCommand(OutputStream os, String cmd) throws IOException {
        os.write(cmd.getBytes());
        os.flush();
    }

    private String readResponse(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] bb = new byte[256];
        int len;
        while(!sb.toString().endsWith("\r\n\r\n") && (len = is.read(bb)) != -1) { //!sb.toString().endsWith("\r\n\r\n") &&
            sb.append(new String(bb, 0, len));
        }
        return sb.toString();
    }
}
