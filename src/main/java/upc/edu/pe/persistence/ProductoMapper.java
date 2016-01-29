package upc.edu.pe.persistence;

import java.util.List;
import org.springframework.dao.DataAccessException;
import upc.edu.pe.licoreria.type.Producto;

/**
 *
 * @author lcardoso
 */
public interface ProductoMapper {
    
    public List<Producto> listar() throws DataAccessException;
    
}