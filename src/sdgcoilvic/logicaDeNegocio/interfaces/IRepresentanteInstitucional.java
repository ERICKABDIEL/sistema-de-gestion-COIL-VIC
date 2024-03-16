package sdgcoilvic.logicaDeNegocio.interfaces;

import java.sql.SQLException;
import java.util.List;
import sdgcoilvic.logicaDeNegocio.clases.representanteInstitucional;

public interface IRepresentanteInstitucional {
    int registrarRepresentanteInstitucional(representanteInstitucional representanteInstitucional) throws SQLException;
    representanteInstitucional obtenerRepresentanteInstitucional(String correo) throws SQLException;
    List<representanteInstitucional> obtenerRepresentanteInstitucional() throws SQLException;
    boolean actualizarRepresentanteInstitucional(representanteInstitucional representanteInstitucional) throws SQLException;
    boolean eliminarRepresentanteInstitucional(String correo) throws SQLException;
}
