package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import upc.edu.pe.licoreria.type.Producto;

/**
 *
 * @author Miguel Cardoso
 */
public class ProductoRestServiceTest {

    public ProductoRestServiceTest() {
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
     * Test of listadoProductos method, of class ProductoRestService.
     */
    @Test
    public void testListadoProductos() {
        System.out.println("listadoProductos");
        try {
            URL url = new URL("http://localhost:8080/LicoreriaRest/productos");
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

                Type type = new TypeToken<List<Producto>>() {
                }.getType();
                List<Producto> inpList = json.fromJson(output, type);
                for (int i = 0; i < inpList.size(); i++) {
                    Producto x = inpList.get(i);
                    System.out.println(x.getNombre());
                }
            }

            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

}
