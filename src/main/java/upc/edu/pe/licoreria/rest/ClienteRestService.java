package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import upc.edu.pe.licoreria.type.Cliente;
import upc.edu.pe.licoreria.type.Distrito;
import upc.edu.pe.service.ClienteService;
import upc.edu.pe.spring.SisLocFactory;

/**
 *
 * @author Miguel Cardoso
 */
@Path("/usuarios")
public class ClienteRestService {

    CallableStatement cs = null;
    PreparedStatement ps = null;
    Connection conexion = null;
    String query = "";
    
    ClienteService clienteService = SisLocFactory.getInstance().getClienteService();

    @GET
    @Path("/info/{id}")
    @Produces("application/json")
    public String obtenerClienteById(@PathParam("id") String id) {
        Cliente cliente = new Cliente();
        cliente.setDistrito(new Distrito());
        String json = "";
        Gson gson = new Gson();
        try {
            cliente = clienteService.obtenerCliente(Integer.parseInt(id));
            json = gson.toJson(cliente);

        } catch (Exception e) {
            System.out.println("Message Obtener usuario por id : " + e.getMessage());
        }
        return json;
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    public Response login(String usuario) {
        String result = "";
        Gson gson = new Gson();
        Response response = null;
        Cliente cliente = (Cliente) gson.fromJson(usuario, Cliente.class);
        try {
            cliente = clienteService.autenticar(cliente);   
            if (cliente != null) {
                result = "OK";
                response = Response.status(200).entity(result).build();
            } else {
                result = "ERROR";
                response = Response.status(404).entity(result).build();
            }
        } catch (Exception e) {
            response = Response.status(404).entity(result).build();
            System.out.println("Message Login: " + e.getMessage());
        }

        return response;
    }

    @POST
    @Path("/insertar")
    @Consumes("application/json")
    public Response registarCliente(String usuario) {
        String result = "";
        Gson gson = new Gson();
        Response response = null;

        try {
            Cliente cliente = (Cliente) gson.fromJson(usuario, Cliente.class);
            int registro = clienteService.insertar(cliente);
            System.out.println("Registro Cliente : " + registro);
            if (registro == 1) {
                result = "OK";
                response = Response.status(201).entity(result).build();
            } else {
                result = "ERROR";
                response = Response.status(404).entity(result).build();
            }
        } catch (Exception e) {
            System.out.println("Message Insertar Cliente: " + e.getMessage());
        }
        return response;

    }

    @PUT
    @Path("/actualizar")
    @Consumes("application/json")
    public Response actualizarCliente(String usuario) {
        String result = "";
        Gson gson = new Gson();
        Response response = null;
        Cliente cliente = (Cliente) gson.fromJson(usuario, Cliente.class);
        try {
            int actualizo = clienteService.actualizar(cliente);
            System.out.println("Actualizo Cliente: " + actualizo);
            if (actualizo == 1) {
                result = "OK";
                response = Response.status(201).entity(result).build();
            } else {
                result = "ERROR";
                response = Response.status(404).entity(result).build();
            }
        } catch (Exception e) {
            System.out.println("Message Actualizar Cliente: " + e.getMessage());
        }

        return response;
    }
}