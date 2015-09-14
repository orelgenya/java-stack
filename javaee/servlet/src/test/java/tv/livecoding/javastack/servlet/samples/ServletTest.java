package tv.livecoding.javastack.servlet.samples;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import static org.junit.Assert.*;

/**
 * @author arungupta
 */
@RunWith(Arquillian.class)
public class ServletTest {

    @ArquillianResource
    private URL base;

    WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).
            addClass(ParentServlet.class).
            addClass(ChildServlet.class);
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
    }

    @Test
    public void testChildServlet() throws IOException, SAXException {
        try {
            webClient.getPage(base + "/ChildServlet");
        } catch (FailingHttpStatusCodeException e) {
            assertNotNull(e);
            assertEquals(404, e.getStatusCode());
            return;
        }
        fail("/ChildServlet could be accessed with programmatic registration");
        webClient.getPage(base + "/ParentServlet");
        webClient.getPage(base + "/ChildServlet");
    }
}
