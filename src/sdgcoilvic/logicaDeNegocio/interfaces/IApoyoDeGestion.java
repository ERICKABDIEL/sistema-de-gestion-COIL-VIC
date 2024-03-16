package sdgcoilvic.logicaDeNegocio.interfaces;

import java.sql.SQLException;
import java.util.List;
import sdgcoilvic.logicaDeNegocio.clases.apoyoDeGestion;

/**
 *
 * @author abdie
 */
public interface IApoyoDeGestion {
   int registrarApoyoDeGestion(apoyoDeGestion apoyoDeGestion) throws SQLException;
    apoyoDeGestion obtenerEstudiantePorMatricula(String correo) throws SQLException;
    List<apoyoDeGestion> obtenerTodosLosEstudiantes() throws SQLException;
    boolean actualizarApoyoDeGestion(apoyoDeGestion apoyoDeGestion) throws SQLException;
    boolean eliminarApoyoDeGestion(String correo) throws SQLException; 
}