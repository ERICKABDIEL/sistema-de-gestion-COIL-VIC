package sdgcoilvic.accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Juan Eduardo
 */

public class manejadorBaseDeDatos {
    private static manejadorBaseDeDatos instancia;
    private Connection conexion;
    private final String BASEDEDATOS_NOMBRE="jdbc:mysql://localhost/proyecto";
    private final String BASEDEDATOS_USUARIO="root";
    private final String BASEDEDATOS_CONTRASENYA="1234";
    
    public Connection getConnection() throws SQLException{
        conectar();
        return conexion;
    }
    
    private void conectar() throws SQLException{
        
        conexion=DriverManager.getConnection(BASEDEDATOS_NOMBRE,BASEDEDATOS_USUARIO,BASEDEDATOS_CONTRASENYA);
    }
    
    
    public void cerrarConexion(){
        if(conexion!=null){
            try {
                if(!conexion.isClosed()){
                    conexion.close();
                }
            } catch (SQLException exception) {                
                Logger.getLogger(manejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
    }
    public static synchronized manejadorBaseDeDatos getInstancia() {
        if (instancia == null) {
            instancia = new manejadorBaseDeDatos();
        }
        return instancia;
    }
    
}
