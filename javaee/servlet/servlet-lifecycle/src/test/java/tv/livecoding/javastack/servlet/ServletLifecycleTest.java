package tv.livecoding.javastack.servlet;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Eugene Orel on 9/18/2015.
 */
@RunWith(Arquillian.class)
public class ServletLifecycleTest {
    @ArquillianResource
    private URL base;

    private WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addClasses(
                SimpleServlet.class,
                SimpleServletContextListener.class,
                SimpleServletContextAttributeListener.class,
                SimpleServletRequestListener.class,
                SimpleServletRequestAttributeListener.class,
                SimpleHttpSessionListener.class,
                SimpleHttpSessionIdListener.class,
                SessionAwareAttribute.class,
                SimpleHttpSessionAttributeListener.class,
                BindingAwareAttribute.class
        );
        System.out.println(war);
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
    }

    @Test
    public void testLifecycle() throws IOException {
        Page page = webClient.getPage(base + "simple");
        Assert.assertEquals("simple response", page.getWebResponse().getContentAsString());
    }
}
