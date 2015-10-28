package tv.livecoding.javastack.cdi;

import javax.enterprise.inject.Produces;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Eugene Orel on 10/7/2015.
 */
public class BeansProducer {

    @Produces
    public Iterable<EchoBean> beansAll() {
        return Arrays.asList(new EchoBean(), new EchoBean());
    }

    @Produces @Cool
    public Iterable<EchoBean> beansCool() {
        return Arrays.asList(new EchoBean());
    }
}

@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
@interface Cool {}
