package tv.livecoding.javastack.cdi;

import javax.ejb.Stateful;
import javax.inject.Named;

/**
 * Created by Eugene Orel on 10/6/2015.
 */
@Stateful
@Named("stateful")
public class StatefulBean extends Echo implements Fireable {
    @Override
    public String fire(String msg) {
        return echo(msg);
    }
}
