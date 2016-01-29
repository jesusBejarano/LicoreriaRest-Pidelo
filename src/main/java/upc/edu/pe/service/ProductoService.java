package upc.edu.pe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.licoreria.type.Producto;
import upc.edu.pe.persistence.ProductoMapper;

/**
 *
 * @author Miguel Cardoso
 */
@Service
public class ProductoService {
    @Autowired
    private ProductoMapper productoMapper;
    
    public List<Producto> listar() throws Exception{
        return productoMapper.listar();
    }
}
