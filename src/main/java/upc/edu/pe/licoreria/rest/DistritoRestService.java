package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import upc.edu.pe.licoreria.type.Distrito;
import upc.edu.pe.service.DistritoService;
import upc.edu.pe.spring.SisLocFactory;

/**
 *
 * @author Miguel Cardoso
 */
@Path("/distritos")
public class DistritoRestService {
    //Servicio
    DistritoService distritoService = SisLocFactory.getInstance().getDistritoService();

    @GET
    @Produces("application/json")
    public String listadoDistrito() {
        List<Distrito> listDistritos = new ArrayList<Distrito>();
        String json="";
        Gson gson = new Gson();
        try {
            listDistritos = distritoService.listar();
            json = gson.toJson(listDistritos);                      
        } catch (Exception e) {
        }
        return json;
    }
}
