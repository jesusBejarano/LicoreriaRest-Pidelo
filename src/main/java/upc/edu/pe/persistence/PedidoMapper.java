package upc.edu.pe.persistence;

import java.util.List;
import org.springframework.dao.DataAccessException;
import upc.edu.pe.licoreria.type.Pedido;

/**
 *
 * @author lcardoso
 */
public interface PedidoMapper {
    
    public Integer insertar(Pedido pedido) throws DataAccessException;
    public Integer actualizar(Integer id) throws DataAccessException;
    public List<Pedido> listar() throws DataAccessException;
    public List<Pedido> obtener(Integer id) throws DataAccessException;
    
}