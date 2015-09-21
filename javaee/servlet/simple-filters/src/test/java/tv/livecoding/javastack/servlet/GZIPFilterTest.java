package tv.livecoding.javastack.servlet;

import com.gargoylesoftware.htmlunit.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import tv.livecoding.javastack.servlet.compression.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene Orel on 9/21/2015.
 */
@RunWith(Arquillian.class)
public class GZIPFilterTest {

    @ArquillianResource
    private java.net.URL base;

    WebClient webClient;
    String URL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        GZIPServlet.class,
                        GZIPFilter.class,
                        GZIPRequestStream.class,
                        GZIPRequestWrapper.class,
                        GZIPResponseStream.class,
                        GZIPResponseWrapper.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
        URL = base + "gzip";
    }

    @Test
    public void testGet() throws IOException {
        WebRequest request = new WebRequest(new URL(URL), HttpMethod.GET);
        Page page = webClient.getPage(request);
        assertEquals("hello!", page.getWebResponse().getContentAsString());

        request = new WebRequest(new URL(URL), HttpMethod.GET);
        request.setAdditionalHeader("Accept-Encoding", "gzip");
        WebResponse response = webClient.getWebConnection().getResponse(request);


        assertEquals("hello!json", readGZIP(response.getContentAsStream()));
    }

    private String readGZIP (InputStream is) throws IOException {
        byte[] bb = new byte[256];
        int len;
        StringBuilder sb = new StringBuilder();
        try (GZIPInputStream gis = new GZIPInputStream(is)) {
            while ((len = gis.read(bb)) > 0) {
                sb.append(new String(bb, 0, len));
            }
        } catch (IOException ex) {
            System.out.println(sb.toString());
            throw ex;
        }
        return sb.toString();
    }
}
