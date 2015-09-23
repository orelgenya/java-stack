package tv.livecoding.javastack.websocket;

/**
 * Created by Eugene Orel on 9/23/2015.
 */
public class WebSocketData {
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
