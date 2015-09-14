package tv.livecoding.javastack.servlet;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.util.KeyDataPair;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene Orel on 9/14/2015.
 */
@RunWith(Arquillian.class)
public class FileUploadServletTest {

    @ArquillianResource
    private URL base;

    WebClient webClient;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addClasses(FileUploadServlet.class, EncodingFilter.class);
        System.out.println(war);
        return war;
    }

    @Before
    public void setup() {
        webClient = new WebClient();
    }

    @Test
    public void test() throws IOException {
        WebRequest request = new WebRequest(new URL(base + "fileUpload"), HttpMethod.POST);
        request.setEncodingType(FormEncodingType.MULTIPART);

        List<NameValuePair> params = new LinkedList<>();
        KeyDataPair filePair = new KeyDataPair("arquillian.xml",
                new File(getClass().getClassLoader().getResource("arquillian.xml").getFile()), "text/xml", EncodingFilter.UTF8);
        params.add(filePair);

        request.setRequestParameters(params);

        Page page = webClient.getPage(request);
        String[] parts = page.getWebResponse().getContentAsString(EncodingFilter.UTF8).split(";");
        assertEquals("One part expected", 1, parts.length);

        parts = parts[0].split(":");
        assertEquals("Response format should be 'part_name:part_size'", 2, parts.length);
        assertEquals("Part name expected!", "arquillian.xml", parts[0]);
        assertEquals("Part size expected!", 636, Integer.parseInt(parts[1]));
    }
}
