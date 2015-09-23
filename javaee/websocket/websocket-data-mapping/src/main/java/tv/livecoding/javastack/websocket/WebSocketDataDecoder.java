package tv.livecoding.javastack.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
public class WebSocketDataDecoder implements Decoder.Text<WebSocketData> {

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
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
