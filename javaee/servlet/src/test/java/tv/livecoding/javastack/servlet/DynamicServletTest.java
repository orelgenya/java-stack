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
import tv.livecoding.javastack.servlet.samples.*;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by OrelGenya on 14.09.2015.
 */
@RunWith(Arquillian.class)
public class DynamicServletTest {


    @ArquillianResource
    private URL base;

    WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        tv.livecoding.javastack.servlet.samples.ParentServlet.class,
//                        EncodingFilter.class,
                        ChildServlet.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
    }

    @Test
    public void testDynamic() throws IOException {
        WebRequest request = new WebRequest(new URL(base + "parent"), HttpMethod.GET);
        Page page = webClient.getPage(request);
        assertEquals(page.getWebResponse().getStatusCode(), 200);
//        assertEquals(SimpleServlet.POST_RESPONSE, page.getWebResponse().getContentAsString(EncodingFilter.UTF8));

        request = new WebRequest(new URL(base + "ChildServlet"), HttpMethod.GET);
        page = webClient.getPage(request);
        assertEquals("I'm dynamic!", page.getWebResponse().getContentAsString(EncodingFilter.UTF8));
//        try {
//            webClient.getPage(base + "/ChildServlet");
//        } catch (FailingHttpStatusCodeException e) {
//            assertNotNull(e);
//            assertEquals(404, e.getStatusCode());
//            return;
//        }
//        fail("/ChildServlet could be accessed with programmatic registration");
//        webClient.getPage(base + "/ParentServlet");
//        webClient.getPage(base + "/ChildServlet");
    }
}
