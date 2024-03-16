package sdgcoilvic.logicaDeNegocio.interfaces;

import java.sql.SQLException;
import java.util.List;
import sdgcoilvic.logicaDeNegocio.clases.coordinador;

public interface ICoordinador {
    int registrarCoordinador(coordinador coordinador) throws SQLException;
    coordinador obtenerCoordinador(String correo) throws SQLException;
    List<coordinador> obtenerCoordinador() throws SQLException;
    boolean actualizarCoordinador(coordinador coordinador) throws SQLException;
    boolean eliminarCoordinador(String correo) throws SQLException;
}

