package tv.livecoding.javastack.servlet;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
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
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene Orel on 9/21/2015.
 */
@RunWith(Arquillian.class)
public class GZIPFilterTest {

    @ArquillianResource
    private URL base;

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
        page = webClient.getPage(request);
        assertEquals("hello!", page.getWebResponse().getContentAsString());
    }
}
