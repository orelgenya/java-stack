package tv.livecoding.javastack.servlet;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Eugene Orel on 9/15/2015.
 */
@RunWith(Arquillian.class)
public class UpgradeServletTest {

    @ArquillianResource
    private URL base;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        UpgradeServlet.class,
                        SimpleServletRequestListener2.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Test
    @Ignore
    public void testUpgrade() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(base + "up").openConnection();
        connection.connect();
    }
}
