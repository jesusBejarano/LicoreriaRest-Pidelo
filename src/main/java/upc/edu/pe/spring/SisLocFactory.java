package upc.edu.pe.spring;

import org.springframework.beans.factory.BeanFactory;
import upc.edu.pe.service.ClienteService;

/**
 *
 * @author lcardoso
 */
public class SisLocFactory {
    
    public static String[] dataContext;
    private static SisLocFactory singleton = null;
    private BeanFactory factory = null;
    
    public SisLocFactory() {
        factory = SpringConfiguracion.configurar(dataContext);
    }
    
    public static SisLocFactory getInstance() {
        if (singleton == null) {
            singleton = new SisLocFactory();
        }
        return singleton;
    }

    public BeanFactory getFactory() {
        return factory;
    }
    
    /* Servicios */
    public ClienteService getClienteService(){
        return factory.getBean("clienteService", ClienteService.class);
    }
}
