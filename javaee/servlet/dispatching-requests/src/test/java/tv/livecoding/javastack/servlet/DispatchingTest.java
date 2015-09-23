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

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
@RunWith(Arquillian.class)
public class DispatchingTest {
    @ArquillianResource
    private URL base;

    String URL;
    WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        BaseServlet.class,
                        IncludeServlet.class,
                        ForwardServlet.class)
                .addAsWebResource("text.txt");
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
        URL = base + "base";
    }

    @Test
    public void testDynamic() throws IOException {
        WebRequest request = new WebRequest(new URL(URL + "?action=include"), HttpMethod.GET);
        Page page = webClient.getPage(request);
        assertEquals("Hello from included!", page.getWebResponse().getContentAsString());

        request = new WebRequest(new URL(URL + "?action=forward"), HttpMethod.GET);
        page = webClient.getPage(request);
        assertEquals("forwarded", page.getWebResponse().getContentAsString());

        request = new WebRequest(new URL(URL + "?action=static"), HttpMethod.GET);
        page = webClient.getPage(request);
        assertEquals("Hello from text!", page.getWebResponse().getContentAsString());

        request = new WebRequest(new URL(URL + "?action=async"), HttpMethod.GET);
        page = webClient.getPage(request);
        assertEquals("Hello from !included", page.getWebResponse().getContentAsString());
    }
}
