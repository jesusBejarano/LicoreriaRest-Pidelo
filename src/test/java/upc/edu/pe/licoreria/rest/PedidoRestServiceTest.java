package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
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

/**
 *
 * @author Miguel Cardoso
 */
public class PedidoRestServiceTest {

    public PedidoRestServiceTest() {
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
     * Test of listadoPedidosPendientes method, of class PedidoRestService.
     */
    @Test
    public void testListadoPedidosPendientes() {
        System.out.println("listadoPedidosPendientes");
        try {
            URL url = new URL("http://localhost:8080/LicoreriaRest/pedidos/todos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            Gson json = new Gson();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                Type type = new TypeToken<List<Pedido>>() {
                }.getType();
                List<Pedido> inpList = json.fromJson(output, type);
                for (int i = 0; i < inpList.size(); i++) {
                    Pedido x = inpList.get(i);
                    System.out.println(x.getCliente().getUsuario());
                }
            }

            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

    /**
     * Test of listadoPedidos method, of class PedidoRestService.
     */
    //@Test
    public void testListadoPedidos() {
        System.out.println("listadoPedidos");
        String id = "";
        PedidoRestService instance = new PedidoRestService();
        String expResult = "";
        String result = instance.listadoPedidos(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerDetallePedido method, of class PedidoRestService.
     */
    //@Test
    public void testObtenerDetallePedido() {
        System.out.println("obtenerDetallePedido");
        String id = "";
        PedidoRestService instance = new PedidoRestService();
        String expResult = "";
        String result = instance.obtenerDetallePedido(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registarPedido method, of class PedidoRestService.
     */
    //@Test
    public void testRegistarPedido() {
        System.out.println("registarPedido");
        try {
            URL url = new URL("http://localhost:8080/LicoreriaRest/pedidos/insertar");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            Pedido pedido = new Pedido();
            //pedido.setId_pedido(6);
            pedido.setCantidad(3);
            pedido.setMonto(411.70);
            pedido.setEstado("P");
            Cliente cliente = new Cliente();
            cliente.setId_cliente(7);
            cliente.setCorreo("lcardosoore@gmail.com");
            pedido.setCliente(cliente);

            Distrito distrito = new Distrito();
            distrito.setId_distrito(6);
            distrito.setNombre("SAN ISIDRO");
            pedido.setDistrito(distrito);
            pedido.setDireccion("Av. Las Camelias 702");

            List<DetallePedido> listdp = new ArrayList<DetallePedido>();
            Producto producto = new Producto();
            DetallePedido dp = new DetallePedido();
            Pedido pedido2 = new Pedido();
            pedido2.setId_pedido(6);

            dp.setPedido(pedido2);
            dp.setItem(1);
            dp.setCantidad(2);
            producto.setId_producto(2);
            producto.setPrecio(41.80);
            dp.setProducto(producto);
            dp.setTotal(producto.getPrecio() * dp.getCantidad());

            listdp.add(dp);

            dp = new DetallePedido();
            producto = new Producto();

            dp.setPedido(pedido2);
            dp.setItem(2);
            dp.setCantidad(1);
            producto.setId_producto(6);
            producto.setPrecio(369.90);
            dp.setProducto(producto);
            dp.setTotal(producto.getPrecio() * dp.getCantidad());

            listdp.add(dp);

            pedido.setListDetallePedidos(listdp);

            Gson gs = new Gson();
            String js = gs.toJson(pedido);

            System.out.println("Json : " + js);

            OutputStream os = conn.getOutputStream();
            os.write(js.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                System.out.println(output);
            }

            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

    /**
     * Test of actualizarPedido method, of class PedidoRestService.
     */
    //@Test
    public void testActualizarPedido() {
        System.out.println("actualizarPedido");
        try {
            URL url = new URL("http://localhost:8080/LicoreriaRest/pedidos/actualizar");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            Pedido pedido = new Pedido();
            pedido.setId_pedido(2);

            Gson gs = new Gson();
            String js = gs.toJson(pedido);

            OutputStream os = conn.getOutputStream();
            os.write(js.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                System.out.println(output);
            }

            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

}
