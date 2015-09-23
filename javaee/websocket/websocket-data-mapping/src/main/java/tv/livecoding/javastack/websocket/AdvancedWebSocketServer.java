package tv.livecoding.javastack.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Eugene Orel on 9/16/2015.
 */
@ServerEndpoint(value = "/websocket",
        decoders = {WebSocketDataDecoder.class},
        encoders = {WebSocketDataEncoder.class}
)
public class AdvancedWebSocketServer {

    @OnMessage
    public WebSocketData onMessage(WebSocketData data) {
        System.out.println(data);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg;
        switch (data.getPayload()) {
            case "Hey!":
                msg = "What's up!";
                break;
            case "Bye!":
                msg = "See you!";
                break;
            default:
                msg = "Got '" + data.getPayload() + "'";
        }
        return new WebSocketData(System.currentTimeMillis(), data.getType(), msg);
    }

}
