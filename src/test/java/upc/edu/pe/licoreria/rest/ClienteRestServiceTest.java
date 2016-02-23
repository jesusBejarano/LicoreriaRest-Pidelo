package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import upc.edu.pe.licoreria.type.Cliente;
import upc.edu.pe.licoreria.type.Distrito;

/**
 *
 * @author Miguel Cardoso
 */
public class ClienteRestServiceTest {
    
    public ClienteRestServiceTest() {
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
     * Test of registarCliente method, of class ClienteRestService.
     */
   //@Test
    public void testRegistarCliente() throws Exception {
        System.out.println("registarCliente");
        try {
            URL url = new URL("http://localhost:8080/LicoreriaRest/usuarios/insertar");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            
            Cliente cliente = new Cliente();
            cliente.setNombre("Prueba");
            cliente.setApellidos("Proyecto");
//            cliente.setDireccion("AV.LA PAZ");
//            cliente.setDistrito(new Distrito());
//            cliente.getDistrito().setId_distrito(2);
//            cliente.setTelefono("5663313");
            cliente.setUsuario("Admin");
            cliente.setContrasena("Admin");
            cliente.setCorreo("lcardosoore@gmail.com");
            
            Gson gs = new Gson();
            String js = gs.toJson(cliente);
            
     //      String js = "{\"apellidos\":\"gkg\",\"contrasena\":\"c\",\"correo\":\"ckg\",\"nombre\":\"hih\",\"usuario\":\"v\",\"id_cliente\":0}";
            
            
            
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
     * Test of obtenerClienteById method, of class ClienteRestService.
     */
    //@Test
    public void testObtenerClienteById() {
        System.out.println("obtenerClienteById");
        try {
            URL url = new URL("http://localhost:8080/LicoreriaRest/usuarios/info/100");
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
               Cliente cliente = json.fromJson(output, Cliente.class);
                System.out.println("Cliente : " + cliente.getUsuario());
                
            }

            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Message : " + e.getMessage());
        }

    }

    /**
     * Test of actualizarCliente method, of class ClienteRestService.
     */
    //@Test
    public void testActualizarCliente() {
        System.out.println("actualizarCliente");
        try {
            URL url = new URL("http://localhost:8080/LicoreriaRest-Pidelo/usuarios/actualizar");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            Cliente cliente = new Cliente();
            cliente.setId_cliente(10);
            cliente.setNombre("");
            cliente.setApellidos("");
            cliente.setDireccion("");
            cliente.setDistrito(new Distrito());
            cliente.getDistrito().setId_distrito(0);
            cliente.setTelefono("5663313");
            cliente.setUsuario("");
            cliente.setContrasena("");
            cliente.setCorreo("");

            Gson gs = new Gson();
            String js = gs.toJson(cliente);

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
     * Test of login method, of class ClienteRestService.
     */
    //@Test
    public void testLogin() {
        System.out.println("login");
        try{
            URL url = new URL("http://localhost:8080/LicoreriaRest/usuarios/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            Cliente cliente = new Cliente();
            cliente.setUsuario("Admin");
            cliente.setContrasena("Admin");

            Gson gs = new Gson();
            String js = gs.toJson(cliente);

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
