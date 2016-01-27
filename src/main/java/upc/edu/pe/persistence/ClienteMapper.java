package upc.edu.pe.persistence;

import org.springframework.dao.DataAccessException;
import upc.edu.pe.licoreria.type.Cliente;

/**
 *
 * @author lcardoso
 */
public interface ClienteMapper {
    
    public Cliente obtener(Integer id) throws DataAccessException;
    public Cliente autenticar(Cliente cliente) throws DataAccessException;
    public Integer insertar(Cliente cliente) throws DataAccessException;
    
}
