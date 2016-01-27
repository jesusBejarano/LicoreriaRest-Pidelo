package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import upc.edu.pe.licoreria.conexion.ConexionBD;
import upc.edu.pe.licoreria.type.Familia;
import upc.edu.pe.licoreria.type.Producto;

/**
 *
 * @author Miguel Cardoso
 */
@Path("/productos")
public class ProductoRestService {
    Statement st = null;
    Connection conexion = null;
    String query = "";
    
    @GET
    @Produces("application/json")
    public String listadoProductos() {
        List<Producto> listProductos = new ArrayList<Producto>();
        Producto producto;
        String json = "";
        Gson gson = new Gson();
        try {
            conexion = ConexionBD.conexion();
            st = conexion.createStatement();
            query = "SELECT p.Id_producto,p.Nombre,p.descripcion,p.Id_familia,p.Precio,p.Id_familia, f.Descripcion as clase FROM tb_producto p inner join tb_familia f on p.Id_familia = f.Id_familia ;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                producto = new Producto();
                producto.setNombre(rs.getString("nombre"));
                producto.setId_producto(Integer.parseInt(rs.getString("id_producto")));
                producto.setPrecio(Double.parseDouble(rs.getString("precio")));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setFamilia(new Familia());
                producto.getFamilia().setId_familia(Integer.parseInt(rs.getString("id_familia")));
                producto.getFamilia().setDescripcion(rs.getString("clase"));                
                listProductos.add(producto);
            }
            st.close();
            conexion.close();
            json = gson.toJson(listProductos);

        } catch (Exception e) {
            System.out.println("Messge Listar Productos : " + e.getMessage());
        }
        return json;
    }    
    
}
