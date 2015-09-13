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
        TextPage page = webClient.getPage(base + "s");
        assertEquals("cheking", page.getContent());
    }

    @Test
    public void testPost() throws IOException {
        WebRequest request = new WebRequest(new URL(base + "s"), HttpMethod.POST);
        Page page = webClient.getPage(request);
        assertEquals("мой post", page.getWebResponse().getContentAsString("UTF-8"));
    }
}
