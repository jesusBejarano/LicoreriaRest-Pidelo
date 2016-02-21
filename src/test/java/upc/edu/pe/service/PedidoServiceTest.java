package upc.edu.pe.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import upc.edu.pe.licoreria.type.Cliente;
import upc.edu.pe.licoreria.type.DetallePedido;
import upc.edu.pe.licoreria.type.Distrito;
import upc.edu.pe.licoreria.type.Pedido;
import upc.edu.pe.licoreria.type.Producto;
import upc.edu.pe.spring.SisLocFactory;

/**
 *
 * @author Miguel Cardoso
 */
public class PedidoServiceTest {
    
    PedidoService instance;
    
    public PedidoServiceTest() {
        instance = SisLocFactory.getInstance().getPedidoService();
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
     * Test of listar method, of class PedidoService.
     */
    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        List<Pedido> result = instance.listar();
        System.out.println("Resultado : " + result.size());
        for(Pedido ped : result){
            System.out.println("Nombre : " + ped.getCliente().getNombre());
            System.out.println("Apellidos : " + ped.getCliente().getApellidos());
        }
        assertTrue(result != null);
    }

    /**
     * Test of obtenerPedido method, of class PedidoService.
     */
    //@Test
    public void testObtenerPedido() throws Exception {
        System.out.println("obtenerPedido");
        Integer id = 7;
        List<Pedido> result = instance.obtenerPedido(id);
        System.out.println("Resultado : " + result.size());
        assertTrue(result != null);
    }

    /**
     * Test of insertarPedido method, of class PedidoService.
     */
    //@Test
    public void testInsertarPedido() throws Exception {
        System.out.println("insertarPedido");
        Pedido pedido = new Pedido();
        pedido.setCliente(new Cliente());
        pedido.setDistrito(new Distrito());
        pedido.setCantidad(10);
        pedido.setDireccion("AV. LA MAR");
        pedido.setEstado("P");
        pedido.getCliente().setId_cliente(1);
        pedido.getDistrito().setId_distrito(2);
        pedido.setMonto(20.40);
        Integer expResult = 1;
        Integer result = instance.insertarPedido(pedido);
        System.out.println("Resultado : " + result);
        //assertEquals(expResult, result);
        assertTrue(result > 0);
    }

    /**
     * Test of actualizar method, of class PedidoService.
     */
    //@Test
    public void testActualizar() throws Exception {
        System.out.println("actualizar");
        Integer id = 17;
        Integer expResult = 1;
        Integer result = instance.actualizar(id);
        System.out.println("Resultado : " + result);
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenerDetalle method, of class PedidoService.
     */
    //@Test
    public void testObtenerDetalle() throws Exception {
        System.out.println("obtenerDetalle");
        Integer id = 17;
        List<DetallePedido> result = instance.obtenerDetalle(id);
        System.out.println("Resultado : " + result.size());
        assertTrue(result != null);
    }

    /**
     * Test of insertarDetalle method, of class PedidoService.
     */
    //@Test
    public void testInsertarDetalle() throws Exception {
        System.out.println("insertarDetalle");
        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(new Pedido());
        detalle.setProducto(new Producto());
        detalle.getPedido().setId_pedido(17);
        detalle.setItem(1);
        detalle.setCantidad(10);
        detalle.setTotal(200.40);
        detalle.getProducto().setId_producto(1);
        detalle.getProducto().setPrecio(20.90);
        Integer expResult = 1;
        Integer result = instance.insertarDetalle(detalle);
        System.out.println("Resultado : " + result);
        assertEquals(expResult, result);        
    }

    /**
     * Test of insertarTodo method, of class PedidoService.
     */
    //@Test
    public void testInsertarTodo() throws Exception {
        System.out.println("insertarTodo");
        Pedido pedido = new Pedido();
        pedido.setCliente(new Cliente());
        pedido.setDistrito(new Distrito());
        pedido.setCantidad(3);
        pedido.setDireccion("AV. Pruebas");
        pedido.setEstado("P");
        pedido.getCliente().setId_cliente(14);
        pedido.getDistrito().setId_distrito(1);
        pedido.setMonto(350.20);
        
        List<DetallePedido> listDetalle = new ArrayList<DetallePedido>();
        
        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(new Pedido());
        detalle.setProducto(new Producto());
      // detalle.getPedido().setId_pedido(17);
        detalle.setItem(1);
        detalle.setCantidad(2);
        detalle.setTotal(120.0);
        detalle.getProducto().setId_producto(4);
        detalle.getProducto().setPrecio(67.15);
        
        listDetalle.add(detalle);
        
        detalle = new DetallePedido();
        detalle.setPedido(new Pedido());
        detalle.setProducto(new Producto());
      // detalle.getPedido().setId_pedido(17);
        detalle.setItem(2);
        detalle.setCantidad(1);
        detalle.setTotal(60.0);
        detalle.getProducto().setId_producto(5);
        detalle.getProducto().setPrecio(47.90);
        
        listDetalle.add(detalle);
        
        pedido.setListDetallePedidos(listDetalle);
        
        Integer result = instance.insertarTodo(pedido);
        System.out.println("Resultado : " + result);
        //assertEquals(expResult, result);
        assertTrue(result > 0);
    }
    
}
