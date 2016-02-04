package upc.edu.pe.persistence;

import java.util.List;
import org.springframework.dao.DataAccessException;
import upc.edu.pe.licoreria.type.DetallePedido;

/**
 *
 * @author Miguel Cardoso
 */
public interface DetallePedidoMapper {
    
    public Integer insertar(DetallePedido pedido) throws DataAccessException;
    public List<DetallePedido> obtener(Integer id) throws DataAccessException;
    
}