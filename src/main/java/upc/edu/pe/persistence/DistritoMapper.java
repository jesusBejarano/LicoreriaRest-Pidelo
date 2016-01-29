package upc.edu.pe.persistence;

import java.util.List;
import org.springframework.dao.DataAccessException;
import upc.edu.pe.licoreria.type.Distrito;

/**
 *
 * @author lcardoso
 */
public interface DistritoMapper {
    
    public List<Distrito> listar() throws DataAccessException;
    
}