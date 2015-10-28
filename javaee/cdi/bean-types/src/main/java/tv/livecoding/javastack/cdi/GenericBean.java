package tv.livecoding.javastack.cdi;

/**
 * Created by Eugene Orel on 10/7/2015.
 */
@Generic
public class GenericBean<T> implements Fireable {
    String gen(T msg) {
        return fire(msg.toString());
    }

    @Override
    public String fire(String msg) {
        return msg;
    }
}