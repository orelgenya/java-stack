package tv.livecoding.javastack.cdi;

import javax.inject.Named;

/**
 * Created by Eugene Orel on 10/6/2015.
 */
@Named("simple")
public class SimpleBean extends Echo implements Fireable{

    @Override
    public String fire(String msg) {
        return echo(msg);
    }
}

