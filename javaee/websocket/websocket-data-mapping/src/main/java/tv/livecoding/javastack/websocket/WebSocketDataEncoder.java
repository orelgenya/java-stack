package tv.livecoding.javastack.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
public class WebSocketDataEncoder implements Encoder.Text<WebSocketData> {

    @Override
    public String encode(WebSocketData data) throws EncodeException {
        return data.getTimestamp() + ";" + data.getType() + ";" + data.getPayload();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
