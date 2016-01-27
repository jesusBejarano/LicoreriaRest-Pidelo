package upc.edu.pe.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lcardoso
 */
public class SpringConfiguracion {

    public static BeanFactory configurar(String[] xml) {
        if (xml == null) {
            xml = new String[]{"spring-context.xml"};
        }
        ApplicationContext context = new ClassPathXmlApplicationContext(xml);

        BeanFactory factory = (BeanFactory) context;
        return factory;
    }
}