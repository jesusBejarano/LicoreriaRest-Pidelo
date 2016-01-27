package upc.edu.pe.service;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import upc.edu.pe.licoreria.type.Cliente;
import upc.edu.pe.spring.SisLocFactory;

/**
 *
 * @author lcardoso
 */
public class ClienteServiceTest {
    
    ClienteService instance;
    
    public ClienteServiceTest() {
      instance   = SisLocFactory.getInstance().getClienteService();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of obtenerCliente method, of class ClienteService.
     */
    //@Test
    public void testObtenerCliente() throws Exception {
        System.out.println("obtenerCliente");
            Integer id = 4;
            Cliente expResult = null;
            Cliente result = instance.obtenerCliente(id);
            System.out.println("Resultado : " + result.getUsuario());
            assertTrue(result != null);
    }

    /**
     * Test of autenticar method, of class ClienteService.
     */
    @Test
    public void testAutenticar() throws Exception {
        System.out.println("autenticar");
        Cliente cliente = new Cliente();
        cliente.setUsuario("rsalazar");
        cliente.setContrasena("123");
        Cliente expResult = null;
        Cliente result = instance.autenticar(cliente);
        if(result != null)
        System.out.println("Resultado : " + result.getUsuario());
        else
            System.out.println("Soy Vacio");
            assertTrue(result != null);
    }
    
}
