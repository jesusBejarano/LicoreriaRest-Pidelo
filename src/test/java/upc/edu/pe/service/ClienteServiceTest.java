package upc.edu.pe.service;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import upc.edu.pe.licoreria.type.Cliente;
import upc.edu.pe.licoreria.type.Distrito;
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
    //@Test
    public void testAutenticar() throws Exception {
        System.out.println("autenticar");
        Cliente cliente = new Cliente();
        cliente.setUsuario("lcardoso");
        cliente.setContrasena("12345");
        Cliente expResult = null;
        Cliente result = instance.autenticar(cliente);
        if(result != null)
        System.out.println("Resultado : " + result.getUsuario());
        else
            System.out.println("Soy Vacio");
            assertTrue(result != null);
    }

    /**
     * Test of insertar method, of class ClienteService.
     */
    //@Test
    public void testInsertar() throws Exception {
        System.out.println("insertar");
        Cliente cliente = new Cliente();
        cliente.setNombre("mybatis");
        cliente.setApellidos("spring");
        cliente.setUsuario("ms");
        cliente.setContrasena("1230");
        cliente.setDistrito(new Distrito());
        cliente.getDistrito().setId_distrito(1);
        Integer result = instance.insertar(cliente);
        System.out.println("Resultado : " + result);
        assertTrue(result == 1);
    }

    /**
     * Test of actualizar method, of class ClienteService.
     */
    //@Test
    public void testActualizar() throws Exception {
        System.out.println("actualizar");
        Cliente cliente =  new Cliente();
        cliente.setId_cliente(11);
        cliente.setNombre("test");
        cliente.setApellidos("test");
        cliente.setUsuario("ms");
        cliente.setContrasena("1230");
        cliente.setDireccion("no existe");
        cliente.setCodigoGCM("APA91bGsluzfpQyh2GCxtOfWdfwzJlG0y-IrkAS1JeHbeSonqlBRrdKoYK7_zM11DIpMKVGeAs4WgyzNZpsJDcL-BYJKugT8rKjAR4Jl2J672bn4rUEYkZ2KDG6hfTyKC3iznc4WHiu1");
        cliente.setDistrito(new Distrito());
        cliente.getDistrito().setId_distrito(2);
        Integer result = instance.actualizar(cliente);
        System.out.println("Resultado : " + result);
        assertTrue(result == 1);
    }
    
}
