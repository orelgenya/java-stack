package tv.livecoding.javastack.cdi;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Eugene Orel on 10/6/2015.
 */
@RunWith(Arquillian.class)
public class BeanTypesTest {

    @Deployment
    public static Archive<?> deploy() {
        Archive arch = ShrinkWrap.create(JavaArchive.class)
                .addClasses(
                        SimpleBean.class,
                        StatefulBean.class,
                        Echo.class,
                        Fireable.class,
                        GenericBean.class,
                        Generic.class,
                        TypedBean.class,
                        SpecializingBean.class
                ).addAsManifestResource("beans.xml");
        System.out.println(arch.toString(true));
        return arch;
    }

    @Inject
    SimpleBean bean;

    @Inject @Named("echo")
    Echo echo;

    @Inject @Named("simple")
    Echo simpleEcho;

    @Inject @Named("simple")
    Fireable simpleFireable;

    @Inject @Named("stateful")
    Fireable statefulFireable;

//    impossible inject session bean by class!
//
//    @Inject
//    StatefulBean statefulBean;
//
//    @Inject @Named("stateful")
//    Echo statefulEcho;

    @Inject @Generic
    Fireable genericFireable;

    @Inject @Generic
    GenericBean<Integer> genericBean;

//    impossible inject typed bean with unspecified type
//    @Inject @Named("typed")
//    Fireable typedFireable;

    @Inject
    TypedBean typedBean;

    @Test
    public void test() {
        System.out.println(bean);
        System.out.println(echo);
        System.out.println(genericBean);
        System.out.println(typedBean);

        System.out.println(simpleEcho);
        System.out.println(simpleFireable);
        System.out.println(statefulFireable);
        System.out.println(genericFireable);

        assertEquals("hi", bean.fire("hi"));
        assertEquals("hi", echo.echo("hi"));
        assertEquals("123", genericBean.gen(123));
        assertEquals("hi", typedBean.fire("hi"));

        assertEquals("hi", simpleEcho.echo("hi"));
        assertEquals("hi", simpleFireable.fire("hi"));
        assertEquals("hi", statefulFireable.fire("hi"));
        assertEquals("hi", genericFireable.fire("hi"));

        assertNotEquals(bean, echo);
        assertNotEquals(echo, simpleEcho);
        assertNotEquals(simpleFireable, statefulFireable);
        assertNotEquals(simpleFireable, genericFireable);
    }
}
