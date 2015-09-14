package tv.livecoding.javastack.servlet;

import com.gargoylesoftware.htmlunit.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by OrelGenya on 11.09.2015.
 */
@RunWith(Arquillian.class)
public class SimpleServletTest {

    @ArquillianResource
    private URL base;

    WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addClasses(SimpleServlet.class, EncodingFilter.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
    }

    @Test
    public void testGet() throws IOException {
        Page page = webClient.getPage(base + "s");
        assertEquals(SimpleServlet.INITIAL_PARAM_VALUE, page.getWebResponse().getContentAsString(EncodingFilter.UTF8));
    }

    @Test
    public void testUnavailableGet() throws IOException {
        // set service unavailable
        try {
            WebRequest request = new WebRequest(new URL(base + "s?action=pause"), HttpMethod.POST);
            webClient.getPage(request);
            fail("Unavailable http status expected!");
        } catch (FailingHttpStatusCodeException ex) {
            assertEquals("Unavailable http status expected!", 503, ex.getStatusCode());
        }

        // service should be unavailable still
        try {
            webClient.getPage(base + "s");
            fail("Unavailable http status expected!");
        } catch (FailingHttpStatusCodeException ex) {
            assertEquals("Unavailable http status expected!", 503, ex.getStatusCode());
        }

        // wait for availability
        try {
            Thread.sleep(SimpleServlet.PAUSE_SECONDS * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // normal response expected
        Page page = webClient.getPage(base + "s");
        assertEquals(SimpleServlet.INITIAL_PARAM_VALUE, page.getWebResponse().getContentAsString(EncodingFilter.UTF8));
    }

    @Test
    public void testPost() throws IOException {
        WebRequest request = new WebRequest(new URL(base + "s"), HttpMethod.POST);
        Page page = webClient.getPage(request);
        assertEquals(SimpleServlet.POST_RESPONSE, page.getWebResponse().getContentAsString(EncodingFilter.UTF8));
    }
}
