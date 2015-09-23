package tv.livecoding.javastack.websocket;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Eugene Orel on 9/16/2015.
 */
@ServerEndpoint("/websocket")
public class SimpleWebSocketServer {

    @OnMessage
    public String handleMessage(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (msg) {
            case "Hey!":
                return "What's up!";
            case "Bye!":
                return "See you!";
            default:
                return "Got '" + msg + "'";
        }
    }
}
