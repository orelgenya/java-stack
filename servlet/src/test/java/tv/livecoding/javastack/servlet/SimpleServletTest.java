package tv.livecoding.javastack.servlet;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.TextPage;
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
        WebArchive war = ShrinkWrap.create(WebArchive.class).addClass(SimpleServlet.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
    }

    @Test
    public void testGet() throws IOException {
        TextPage page = webClient.getPage(base + "SimpleServlet");
        assertEquals("my get", page.getContent());
    }

    @Test
    public void testPost() throws IOException {
        WebRequest request = new WebRequest(new URL(base + "SimpleServlet"), HttpMethod.POST);
        TextPage page = webClient.getPage(request);
        assertEquals("my post", page.getContent());
    }
}
