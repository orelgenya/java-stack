package tv.livecoding.javastack.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Eugene Orel on 9/16/2015.
 */
@ServerEndpoint(value = "/websocket",
        decoders = {AdvancedWebSocketServer.WebSocketDataDecoder.class},
        encoders = {AdvancedWebSocketServer.WebSocketDataEncoder.class}
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
        return new WebSocketData(System.currentTimeMillis(), data.type, msg);
    }

    public static class WebSocketData {
        private long timestamp;
        private String type;
        private String payload;

        public WebSocketData(long timestamp, String type, String payload) {
            this.timestamp = timestamp;
            this.type = type;
            this.payload = payload;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        @Override
        public String toString() {
            return "WebSocketData{" +
                    "timestamp=" + timestamp +
                    ", type='" + type + '\'' +
                    ", payload='" + payload + '\'' +
                    '}';
        }
    }

    public static class WebSocketDataDecoder implements Decoder.Text<WebSocketData> {

        @Override
        public WebSocketData decode(String s) throws DecodeException {
            String[] parts = s.split(";");
            if (parts.length != 3) {
                throw new DecodeException(s, "The data should have 3 parts separated by ';'!");
            }
            try {
                return new WebSocketData(Long.valueOf(parts[0]), parts[1], parts[2]);
            } catch (NumberFormatException ex) {
                throw new DecodeException(s, "Failed to convert timestamp!", ex);
            }
        }

        @Override
        public boolean willDecode(String s) {
            return s.split(";").length == 3;
        }

        @Override
        public void init(EndpointConfig config) {}

        @Override
        public void destroy() {}
    }

    public static class WebSocketDataEncoder implements Encoder.Text<WebSocketData> {

        @Override
        public String encode(WebSocketData data) throws EncodeException {
            return data.getTimestamp() + ";" + data.getType() + ";" + data.getPayload();
        }

        @Override
        public void init(EndpointConfig config) {}

        @Override
        public void destroy() {}
    }
}
