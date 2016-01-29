package upc.edu.pe.service;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import upc.edu.pe.licoreria.type.Distrito;
import upc.edu.pe.spring.SisLocFactory;

/**
 *
 * @author Miguel Cardoso
 */
public class DistritoServiceTest {
    
    DistritoService instance;
    
    public DistritoServiceTest() {        
        instance = SisLocFactory.getInstance().getDistritoService();
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
     * Test of listar method, of class DistritoService.
     */
    @Test
    public void testListar() throws Exception {
        System.out.println("listar");
        List<Distrito> result = instance.listar();
        System.out.println("Resultado : " + result.size());
        assertTrue(result != null);
    }
    
}