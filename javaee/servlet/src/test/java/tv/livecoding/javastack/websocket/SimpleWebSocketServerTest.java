package tv.livecoding.javastack.websocket;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene Orel on 9/16/2015.
 */
@RunWith(Arquillian.class)
public class SimpleWebSocketServerTest {

    @ArquillianResource
    private URL base;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addClasses(SimpleWebSocketServer.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Test
    public void testWebSocket() throws URISyntaxException, IOException, DeploymentException {
        final Session session = ContainerProvider.getWebSocketContainer()
                .connectToServer(WebSocketClient.class, new URI(base + "websocket"));
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicInteger responcesCount = new AtomicInteger();

        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String msg) {
                System.out.println("Receiving " + msg);
                try {
                    switch (msg) {
                        case "What's up!":
                            responcesCount.incrementAndGet();
                            System.out.println("Sending Bye!");
                            session.getBasicRemote().sendText("Bye!");
                            break;
                        case "See you!":
                            responcesCount.incrementAndGet();
                        default:
                            session.close();
                            latch.countDown();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Sending Hey!");
        session.getBasicRemote().sendText("Hey!");

        try {
            latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException ignore) {
            System.out.println("Interrupted!");
        }
        assertEquals(2, responcesCount.get());
    }

    @ClientEndpoint
    public static class WebSocketClient {}
}
