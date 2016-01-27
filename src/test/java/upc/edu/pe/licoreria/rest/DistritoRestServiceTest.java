/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import upc.edu.pe.licoreria.type.Distrito;

/**
 *
 * @author Miguel Cardoso
 */
public class DistritoRestServiceTest {

    public DistritoRestServiceTest() {
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
     * Test of listadoDistrito method, of class DistritoRestService.
     */
    @Test
    public void testListadoDistrito() {
        System.out.println("listadoDistrito");
        try {
            URL url = new URL("http://localhost:8080/LicoreriaRest/distritos");
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
            String d;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                
             d =  "\"distrito\":" + output;
            System.out.println(output);
                        Gson json = new Gson();        
            
            
            
            Type type = new TypeToken<List<Distrito>>(){}.getType();
            List<Distrito> inpList = json.fromJson(output, type);
            for (int i = 0; i < inpList.size(); i++) {
                Distrito x = inpList.get(i);
                System.out.println(x.getNombre());
            }
            
            System.out.println("Out : " + d);
            }

            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Message : " + e.getMessage());
        }
    }

}
