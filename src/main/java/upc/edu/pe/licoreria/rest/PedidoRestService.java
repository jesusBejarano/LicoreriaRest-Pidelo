package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import upc.edu.pe.licoreria.type.DetallePedido;
import upc.edu.pe.licoreria.type.Pedido;
import upc.edu.pe.service.PedidoService;
import upc.edu.pe.spring.SisLocFactory;
import upc.edu.pe.tools.PadreType;

/**
 *
 * @author lcardoso
 */
@Path("/pedidos")
public class PedidoRestService extends PadreType{
    //Servicio
    PedidoService pedidoService = SisLocFactory.getInstance().getPedidoService();
    
    @GET
    @Path("/todos")
    @Produces("application/json")
    public String listadoPedidosPendientes() {
        List<Pedido> listPedido = new ArrayList<Pedido>();
        Pedido pedido;
        String json = "";
        Gson gson = new Gson();
        try {
            listPedido = pedidoService.listar();
            json = gson.toJson(listPedido);

        } catch (Exception e) {
            System.out.println("Message listar pedidos todos: " + e.getMessage());
        }
        return json;
    }  
    
    @GET
    @Path("/todo/{id}")
    @Produces("application/json")
    public String listadoPedidos(@PathParam("id") String id) {
        List<Pedido> listPedido = new ArrayList<Pedido>();
        String json = "";
        Gson gson = new Gson();
        try {
            listPedido = pedidoService.obtenerPedido(Integer.parseInt(id));
            json = gson.toJson(listPedido);
        } catch (Exception e) {
            System.out.println("Message listar pedidos : " + e.getMessage());
        }
        return json;
    }     
    
    @GET
    @Path("/detalle/{id}")
    @Produces("application/json")
    public String obtenerDetallePedido(@PathParam("id") String id) {
        List<DetallePedido> listDetallePedido = new ArrayList<DetallePedido>();
        String json = "";
        Gson gson = new Gson();
        try {
            listDetallePedido = pedidoService.obtenerDetalle(Integer.parseInt(id));
            json = gson.toJson(listDetallePedido);

        } catch (Exception e) {
            System.out.println("Message listar pedidos detalle : " + e.getMessage());
        }
        return json;
    }     

    @POST
    @Path("/insertar")
    @Consumes("application/json")
    public Response registarPedido(String pedidos) {
        String result = "";
        Gson gson = new Gson();
        Response response = null;
        int resultado = 0;
        Pedido pedido = (Pedido) gson.fromJson(pedidos, Pedido.class);
        try {
            resultado = pedidoService.insertarTodo(pedido);
            System.out.println("Registros : " + resultado);         
            if (resultado == 1) {
                enviarPedidoaCola(gson.toJson(pedido));
                result = "OK";
                response = Response.status(201).entity(result).build();
            } else {
                result = "ERROR";
                response = Response.status(404).entity(result).build();
            }
        } catch (Exception e) {
            System.out.println("Message Insertar Pedido: " + e.getMessage());
        }
        return response;

    }   
    
    @PUT
    @Path("/actualizar")
    @Consumes("application/json")
    public Response actualizarPedido(String pedidos) {
        String result = "";
        Gson gson = new Gson();
        Response response = null;
        Pedido pedido = (Pedido) gson.fromJson(pedidos, Pedido.class);
        try {
            int actualizo = pedidoService.actualizar(pedido.getId_pedido());
            System.out.println("Actualizo Pedido : " + actualizo);
            if (actualizo == 1) {
                result = "OK";
                response = Response.status(201).entity(result).build();
            } else {
                result = "ERROR";
                response = Response.status(404).entity(result).build();
            }
        } catch (Exception e) {
            System.out.println("Message Actualizar Pedido: " + e.getMessage());
        }

        return response;
    }
    
}