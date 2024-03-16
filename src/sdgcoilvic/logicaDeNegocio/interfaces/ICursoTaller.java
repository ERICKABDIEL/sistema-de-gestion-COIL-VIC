package sdgcoilvic.logicaDeNegocio.interfaces;

import java.sql.SQLException;
import java.util.List;
import sdgcoilvic.logicaDeNegocio.clases.cursoTaller;

public interface ICursoTaller {
    int registrarCursoTaller(cursoTaller course) throws SQLException;
    cursoTaller obtenerCursoTallerPorId(int id) throws SQLException;
    List<cursoTaller> obtenerTodosLosCursosTalleres() throws SQLException;
    boolean actualizarCursoTaller(cursoTaller course) throws SQLException;
    boolean eliminarCursoTaller(int id) throws SQLException;
}
