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
import static org.junit.Assert.fail;

/**
 * Created by Eugene Orel on 9/16/2015.
 */
@RunWith(Arquillian.class)
public class AdvancedWebSocketServerTest {

    @ArquillianResource
    private URL base;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClasses(
                        AdvancedWebSocketServer.class,
                        WebSocketData.class,
                        WebSocketDataDecoder.class,
                        WebSocketDataEncoder.class);
        System.out.println(war.toString(true));
        return war;
    }

    @Test
    public void testWebSocket() throws URISyntaxException, IOException, DeploymentException, EncodeException {
        final Session session = ContainerProvider.getWebSocketContainer()
                .connectToServer(WebSocketClient.class, new URI(base + "websocket"));
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicInteger responcesCount = new AtomicInteger();

        session.addMessageHandler(new MessageHandler.Whole<WebSocketData>() {
            @Override
            public void onMessage(WebSocketData data) {
                System.out.println("Receiving " + data);
                try {
                    switch (data.getPayload()) {
                        case "What's up!":
                            responcesCount.incrementAndGet();
                            System.out.println("Sending What did you get?!");
                            data = new WebSocketData(System.currentTimeMillis(), "chat", "What did you get?");
                            session.getBasicRemote().sendObject(data);
                            break;
                        case "See you!":
                            responcesCount.incrementAndGet();
                            session.close();
                            latch.countDown();
                            break;
                        default:
                            System.out.println("Sending Bye!");
                            data = new WebSocketData(System.currentTimeMillis(), "farewell", "Bye!");
                            session.getBasicRemote().sendObject(data);
                            responcesCount.incrementAndGet();

                    }
                } catch (IOException | EncodeException ex) {
                    ex.printStackTrace();
                    fail(ex.getMessage());
                }
            }
        });

        System.out.println("Sending Hey!");
        WebSocketData data = new WebSocketData(System.currentTimeMillis(), "greeting", "Hey!");
        session.getBasicRemote().sendObject(data);

        try {
            latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException ignore) {
            System.out.println("Interrupted!");
        }
        assertEquals(3, responcesCount.get());
    }

    @ClientEndpoint(
            decoders = {WebSocketDataDecoder.class},
            encoders = {WebSocketDataEncoder.class}
    )
    public static class WebSocketClient {}
}
