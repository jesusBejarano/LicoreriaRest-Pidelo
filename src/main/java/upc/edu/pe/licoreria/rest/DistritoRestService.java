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
import upc.edu.pe.licoreria.type.Distrito;

/**
 *
 * @author Miguel Cardoso
 */
@Path("/distritos")
public class DistritoRestService {

    Statement st = null;
    Connection conexion = null;
    String query="";

    @GET
    @Produces("application/json")
    public String listadoDistrito() {
        List<Distrito> listDistritos = new ArrayList<Distrito>();
        Distrito distrito;
        String json="";
        Gson gson = new Gson();
        try {
            conexion = ConexionBD.conexion();
            st = conexion.createStatement();
            query = "SELECT * FROM tb_distrito;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                distrito = new Distrito();
                distrito.setNombre(rs.getString("nombre"));
                distrito.setId_distrito(Integer.parseInt(rs.getString("id_distrito")));
                listDistritos.add(distrito);
            }
            st.close();
            conexion.close();
            json = gson.toJson(listDistritos);
            
            

        } catch (Exception e) {
        }
        return json;
    }
}
