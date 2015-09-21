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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by OrelGenya on 11.09.2015.
 */
@RunWith(Arquillian.class)
public class EncodingFilterTest {

    @ArquillianResource
    private URL base;

    WebClient webClient;
    String URL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        EncodingServlet.class,
                        EncodingFilter.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
        URL = base + "encoding";
    }

    @Test
    public void testGet() throws IOException {
        Page page = webClient.getPage(URL);
        assertEquals(EncodingServlet.INITIAL_PARAM_VALUE, page.getWebResponse().getContentAsString(EncodingFilter.UTF8));
    }

    @Test
    public void testPost() throws IOException {
        WebRequest request = new WebRequest(new URL(URL), HttpMethod.POST);
        Page page = webClient.getPage(request);
        assertEquals(EncodingServlet.POST_RESPONSE, page.getWebResponse().getContentAsString(EncodingFilter.UTF8));
    }
}
