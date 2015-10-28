package tv.livecoding.javastack.cdi;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Eugene Orel on 10/7/2015.
 */
@RunWith(Arquillian.class)
public class ProducersTest {

    @Deployment
    public static Archive<?> deploy() {
        Archive arch = ShrinkWrap.create(JavaArchive.class)
                .addClasses(
                        BeansProducer.class,
                        EchoBean.class,
                        Cool.class
                ).addAsManifestResource("beans.xml");
        System.out.println(arch.toString(true));
        return arch;
    }

    @Inject
    Iterable<EchoBean> beans;

    @Inject @Cool
    Iterable<EchoBean> cool;

    @Test
    public void test() {
        System.out.println(beans);
        Assert.assertEquals(2, ((Collection<EchoBean>) beans).size());

        System.out.println(cool);
        Assert.assertEquals(1, ((Collection<EchoBean>) cool).size());
    }
}
