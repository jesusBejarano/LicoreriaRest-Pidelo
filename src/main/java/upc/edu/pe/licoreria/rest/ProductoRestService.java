package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import upc.edu.pe.licoreria.type.Producto;
import upc.edu.pe.service.ProductoService;
import upc.edu.pe.spring.SisLocFactory;

/**
 *
 * @author Miguel Cardoso
 */
@Path("/productos")
public class ProductoRestService {
     //Servicio
    ProductoService productoService = SisLocFactory.getInstance().getProductoService();
    
    @GET
    @Produces("application/json")
    public String listadoProductos() {
        List<Producto> listProductos = new ArrayList<Producto>();
        String json = "";
        Gson gson = new Gson();
        try {
            listProductos = productoService.listar();
            json = gson.toJson(listProductos);
        } catch (Exception e) {
            System.out.println("Messge Listar Productos : " + e.getMessage());
        }
        return json;
    }    
    
}
