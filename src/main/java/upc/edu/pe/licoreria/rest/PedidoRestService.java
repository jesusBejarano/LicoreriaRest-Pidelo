package upc.edu.pe.licoreria.rest;

import com.google.gson.Gson;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import upc.edu.pe.licoreria.conexion.ConexionBD;
import upc.edu.pe.licoreria.type.Cliente;
import upc.edu.pe.licoreria.type.DetallePedido;
import upc.edu.pe.licoreria.type.Distrito;
import upc.edu.pe.licoreria.type.Pedido;
import upc.edu.pe.licoreria.type.Producto;
import upc.edu.pe.tools.PadreType;

/**
 *
 * @author lcardoso
 */
@Path("/pedidos")
public class PedidoRestService extends PadreType{
    ResultSet rs = null;
    CallableStatement cs =null;
    PreparedStatement ps = null;
    Connection conexion = null;
    String query = "";
    
    @GET
    @Path("/todos")
    @Produces("application/json")
    public String listadoPedidosPendientes() {
        List<Pedido> listPedido = new ArrayList<Pedido>();
        Pedido pedido;
        String json = "";
        Gson gson = new Gson();
        try {
            conexion = ConexionBD.conexion();
            cs  = conexion.prepareCall("{call sp_pedidos_all()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                pedido = new Pedido();
                pedido.setDistrito(new Distrito());
                pedido.setCliente(new Cliente());
                pedido.setId_pedido(Integer.parseInt(rs.getString("id_pedido")));
                pedido.setDireccion(rs.getString("direccion"));
                pedido.getDistrito().setId_distrito(Integer.parseInt(rs.getString("id_distrito")));
                pedido.getCliente().setId_cliente(rs.getInt("id_cliente"));
                pedido.getCliente().setUsuario(rs.getString("usuario"));
                pedido.getDistrito().setNombre(rs.getString("nombre"));
                pedido.setFecha(cambiarFormatoFecha(rs.getString("fecha")));
                pedido.setEstado(rs.getString("estado"));
                pedido.setMonto(rs.getDouble("monto"));
                pedido.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                listPedido.add(pedido);
            }
            rs.close();
            cs.close();
            conexion.close();
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
        Pedido pedido;
        String json = "";
        Gson gson = new Gson();
        try {
            conexion = ConexionBD.conexion();
            query = "SELECT p.Id_pedido, p.Direccion, p.Id_distrito, d.Nombre, p.Fecha, p.Estado, p.Monto, p.Cantidad FROM tb_pedido p INNER JOIN tb_distrito d ON p.Id_distrito = d.Id_distrito WHERE p.Id_cliente = ? ;";
            ps = conexion.prepareStatement(query);    
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pedido = new Pedido();
                pedido.setDistrito(new Distrito());
                pedido.setId_pedido(Integer.parseInt(rs.getString("id_pedido")));
                pedido.setDireccion(rs.getString("direccion"));
                pedido.getDistrito().setId_distrito(Integer.parseInt(rs.getString("id_distrito")));
                pedido.getDistrito().setNombre(rs.getString("nombre"));
                pedido.setFecha(cambiarFormatoFecha(rs.getString("fecha")));
                pedido.setEstado(rs.getString("estado"));
                pedido.setMonto(rs.getDouble("monto"));
                pedido.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                listPedido.add(pedido);
            }
            ps.close();
            conexion.close();
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
        DetallePedido detallePedido;
        String json = "";
        Gson gson = new Gson();
        try {
            conexion = ConexionBD.conexion();
            query = "SELECT dp.Item, dp.Id_producto, p.Nombre, dp.cantidad, dp.Precio, dp.Total FROM tb_detalle_pedido dp INNER JOIN tb_producto p ON dp.Id_producto = p.Id_producto WHERE dp.Id_pedido = ? ;";
            ps = conexion.prepareStatement(query);    
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                detallePedido = new DetallePedido();
                detallePedido.setProducto(new Producto());
                
                detallePedido.setItem(rs.getInt("item"));
                detallePedido.getProducto().setId_producto(rs.getInt("id_producto"));
                detallePedido.getProducto().setNombre(rs.getString("nombre"));
                detallePedido.getProducto().setPrecio(rs.getDouble("precio"));
                detallePedido.setCantidad(rs.getInt("cantidad"));
                detallePedido.setPrecio(rs.getDouble("precio"));
                detallePedido.setTotal(rs.getDouble("total"));
                
                listDetallePedido.add(detallePedido);
            }
            ps.close();
            conexion.close();
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
            conexion = ConexionBD.conexion();
            conexion.setAutoCommit(false);
            ps = conexion.prepareStatement("INSERT INTO tb_pedido(Cantidad,Direccion,Estado,Fecha,Id_cliente,Id_distrito,Monto)VALUES(?,?,?,now(),?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pedido.getCantidad());
            ps.setString(2, pedido.getDireccion());
            ps.setString(3, pedido.getEstado());
            ps.setInt(4, pedido.getCliente().getId_cliente());
            ps.setInt(5, pedido.getDistrito().getId_distrito());
            ps.setDouble(6, pedido.getMonto());
            pedido.setFecha(convertirDateToStringLong(new Date()));
            resultado = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId_pedido(rs.getInt(1));
            }
            System.out.println("Registros : " + resultado);
            if (resultado == 1) {
//                ps = conexion.prepareStatement("INSERT INTO tb_detalle_pedido(Id_pedido,Item,cantidad,Id_producto,Precio,Total)VALUES(?,?,?,?,?,?)");
                for (DetallePedido dp : pedido.getListDetallePedidos()) {
                    ps = conexion.prepareStatement("INSERT INTO tb_detalle_pedido(Id_pedido,Item,cantidad,Id_producto,Precio,Total)VALUES(?,?,?,?,?,?)");
                    ps.setInt(1, pedido.getId_pedido());
                    ps.setInt(2, dp.getItem());
                    ps.setInt(3, dp.getCantidad());
                    ps.setInt(4, dp.getProducto().getId_producto());
                    ps.setDouble(5, dp.getProducto().getPrecio());
                    ps.setDouble(6, dp.getTotal());
                    resultado = ps.executeUpdate();
                }

            }

            if (resultado == 1) {
                conexion.commit();
                enviarPedidoaCola(gson.toJson(pedido));
                result = "OK";
                response = Response.status(201).entity(result).build();
            } else {
                conexion.rollback();
                result = "ERROR";
                response = Response.status(404).entity(result).build();
            }
            ps.close();
            conexion.close();
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
            conexion = ConexionBD.conexion();
            cs = conexion.prepareCall("{call sp_pedido_actualizar(?)}");
            cs.setInt(1, pedido.getId_pedido());
            int actualizo = cs.executeUpdate();
            System.out.println("Actualizo Pedido : " + actualizo);
            cs.close();
            conexion.close();
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
