package manejadorBaseDeDatos;

import java.sql.Connection;
import java.sql.SQLException;
import sdgcoilvic.accesoADatos.manejadorBaseDeDatos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Juan Eduardo
 */
public class manejadorBadeDeDatosPrueba {
    
    public manejadorBadeDeDatosPrueba() {
    }
    
   @Test
    public void probarConexiónDeRedExitosa() throws SQLException{
        manejadorBaseDeDatos instancia = new manejadorBaseDeDatos();       
        Connection resultadoEsperado = instancia.getConnection();
        assertNotNull(resultadoEsperado);
    }
    
}
