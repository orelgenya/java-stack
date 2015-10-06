package tv.livecoding.javastack.cdi;

import javax.inject.Named;

/**
 * Created by Eugene Orel on 10/6/2015.
 */
@Named("echo")
public class Echo {
    public String echo(String msg) {
        return msg;
    }
}
