package upc.edu.pe.licoreria.type;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Miguel Cardoso
 */
public class Sucursal implements Serializable {

    private int id_sucursal;
    private String nombre;

    private List<Distrito> listDistritos;

    private Distrito Distrito;

    public Sucursal() {
    }

    public int getId_sucursal() {
        return this.id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Distrito> getListDistritos() {
        return listDistritos;
    }

    public void setListDistritos(List<Distrito> listDistritos) {
        this.listDistritos = listDistritos;
    }

    public Distrito getDistrito() {
        return Distrito;
    }

    public void setDistrito(Distrito Distrito) {
        this.Distrito = Distrito;
    }

}
