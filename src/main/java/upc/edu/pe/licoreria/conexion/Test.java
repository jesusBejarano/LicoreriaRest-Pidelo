package upc.edu.pe.licoreria.conexion;

import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Cardoso
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conexion;
        conexion = ConexionBD.conexion();
        if (conexion != null) {
            JOptionPane.showMessageDialog(null, "Conexión Realizada Correctamente");
        }
    }
    
}
