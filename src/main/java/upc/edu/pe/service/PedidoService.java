package upc.edu.pe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.licoreria.type.DetallePedido;
import upc.edu.pe.licoreria.type.Pedido;
import upc.edu.pe.persistence.DetallePedidoMapper;
import upc.edu.pe.persistence.PedidoMapper;

/**
 *
 * @author Miguel Cardoso
 */
@Service
public class PedidoService {
    @Autowired
    PedidoMapper pedidoMapper;
    @Autowired
    DetallePedidoMapper detallePedidoMapper;
    
    /*Pedido*/
    public List<Pedido> listar() throws Exception{
        return pedidoMapper.listar();
    }    
    public List<Pedido> obtenerPedido(Integer id) throws Exception{
        return pedidoMapper.obtener(id);
    }
    public Integer insertarPedido(Pedido pedido) throws Exception{
        return pedidoMapper.insertar(pedido);
    }    
    public Integer actualizar(Integer id) throws Exception{
        return pedidoMapper.actualizar(id);
    }
    /*Detalle*/
    public List<DetallePedido> obtenerDetalle(Integer id) throws Exception{
        return detallePedidoMapper.obtener(id);
    }    
    public Integer insertarDetalle(DetallePedido detalle) throws Exception{
        return detallePedidoMapper.insertar(detalle);
    }
    
    public Integer insertarTodo(Pedido pedido) throws Exception {
        Integer resultado = 0;
        resultado = pedidoMapper.insertar(pedido);

        if (resultado > 0) {
            Integer id = resultado;
            for (DetallePedido detalle : pedido.getListDetallePedidos()) {
                detalle.getPedido().setId_pedido(id);
                resultado = detallePedidoMapper.insertar(detalle);
                if (resultado == 0) {
                    throw new Exception("Error Insertar Detalle Pedido");
                }
            }

        } else {
            throw new Exception("Error Insertar Pedido");
        }
        return resultado;
    }
}
