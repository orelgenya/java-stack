package tv.livecoding.javastack.servlet;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by OrelGenya on 11.09.2015.
 */
@RunWith(Arquillian.class)
public class SimpleServletTest {

    @ArquillianResource
    private URL base;
    private String servletUrl;

    WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(SimpleServlet.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
        servletUrl = base + "simple";
    }

    @Test
    public void testGet() throws IOException {
        Page page = webClient.getPage(servletUrl);
        assertEquals(SimpleServlet.INITIAL_PARAM_VALUE + 1, page.getWebResponse().getContentAsString());
    }

    @Test
    public void testPostAndUnavailableGet() throws IOException {
        // set service unavailable
        try {
            WebRequest request = new WebRequest(new URL(servletUrl), HttpMethod.POST);
            webClient.getPage(request);
            fail("Unavailable http status expected!");
        } catch (FailingHttpStatusCodeException ex) {
            assertEquals("Unavailable http status expected!", 503, ex.getStatusCode());
        }

        // service should be unavailable still
        try {
            webClient.getPage(servletUrl);
            fail("Unavailable http status expected!");
        } catch (FailingHttpStatusCodeException ex) {
            assertEquals("Unavailable http status expected!", 503, ex.getStatusCode());
        }

        // wait for availability
        try {
            Thread.sleep(SimpleServlet.PAUSE_SECONDS * 1000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        // normal response expected
        Page page = webClient.getPage(servletUrl);
        assertEquals(SimpleServlet.INITIAL_PARAM_VALUE + 1, page.getWebResponse().getContentAsString());
    }
}
