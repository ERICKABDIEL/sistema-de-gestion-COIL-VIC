package sdgcoilvic.logicaDeNegocio.interfaces;

import java.sql.SQLException;
import java.util.List;
import sdgcoilvic.logicaDeNegocio.clases.estudiante;

public interface IEstudiantes {
    int registrarEstudiante(estudiante estudiante) throws SQLException;
    estudiante obtenerEstudiantePorMatricula(String matricula) throws SQLException;
    List<estudiante> obtenerTodosLosEstudiantes() throws SQLException;
    boolean actualizarEstudiante(estudiante estudiante) throws SQLException;
    boolean eliminarEstudiante(String matricula) throws SQLException;
}
