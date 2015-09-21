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
 * Created by OrelGenya on 14.09.2015.
 */
@RunWith(Arquillian.class)
public class ProgrammaticRegistrationTest {


    @ArquillianResource
    private URL base;

    WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        SimpleServletContextListener.class,
                        DynamicServlet.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
    }

    @Test
    public void testDynamic() throws IOException {
        WebRequest request = new WebRequest(new URL(base + "dynamic"), HttpMethod.GET);
        Page page = webClient.getPage(request);
        assertEquals(DynamicServlet.RESPONSE, page.getWebResponse().getContentAsString());
    }
}
