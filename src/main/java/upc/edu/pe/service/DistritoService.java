package upc.edu.pe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.licoreria.type.Distrito;
import upc.edu.pe.persistence.DistritoMapper;

/**
 *
 * @author Miguel Cardoso
 */
@Service
public class DistritoService {
    @Autowired
    private DistritoMapper distritoMapper;
    
    public List<Distrito> listar() throws Exception {
        return distritoMapper.listar();
    }

}