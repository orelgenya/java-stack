package tv.livecoding.javastack.cdi;

import javax.enterprise.inject.Specializes;
import javax.enterprise.inject.Typed;
import javax.inject.Named;

/**
 * Created by Eugene Orel on 10/7/2015.
 */
@Typed(TypedBean.class)
@Named("typed")
public class TypedBean extends Echo implements Fireable {
    @Override
    public String fire(String msg) {
        return echo(msg);
    }
}
