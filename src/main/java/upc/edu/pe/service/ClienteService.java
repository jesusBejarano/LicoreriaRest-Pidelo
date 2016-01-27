package upc.edu.pe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.licoreria.type.Cliente;
import upc.edu.pe.persistence.ClienteMapper;

/**
 *
 * @author lcardoso
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteMapper clienteMapper;
    
    public Cliente obtenerCliente(Integer id) throws Exception{
        return clienteMapper.obtener(id);
    }
    
    public Cliente autenticar(Cliente cliente) throws Exception{
        return clienteMapper.autenticar(cliente);
    }
    
}
