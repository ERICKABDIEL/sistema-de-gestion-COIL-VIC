package sdgcoilvic.logicaDeNegocio.DAOImplementacion;
/**
 *
 * @author abdie
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sdgcoilvic.accesoADatos.manejadorBaseDeDatos;
import sdgcoilvic.logicaDeNegocio.clases.representanteInstitucional;
import sdgcoilvic.logicaDeNegocio.interfaces.IRepresentanteInstitucional;

public class RepresentanteInstitucionalDAO implements IRepresentanteInstitucional {
    private final String AGREGAR_REPRESENTANTE_COMANDO = "INSERT INTO representante_institucional "
            + "(idUsuario, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, idioma, claveInstitucional, idRepresentanteInstitucional) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String OBTENER_REPRESENTANTE_POR_CORREO_COMANDO = "SELECT * FROM representante_institucional WHERE correo = ?";
    private final String OBTENER_TODOS_LOS_REPRESENTANTES_COMANDO = "SELECT * FROM representante_institucional";
    private final String ACTUALIZAR_REPRESENTANTE_COMANDO = "UPDATE representante_institucional SET idUsuario = ?, nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, idioma = ?, claveInstitucional = ? WHERE correo = ?";
    private final String ELIMINAR_REPRESENTANTE_COMANDO = "DELETE FROM representante_institucional WHERE correo = ?";

    private final manejadorBaseDeDatos databaseConnection;

    public RepresentanteInstitucionalDAO(manejadorBaseDeDatos databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public int registrarRepresentanteInstitucional(representanteInstitucional representanteInstitucional) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(AGREGAR_REPRESENTANTE_COMANDO, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, representanteInstitucional.getIdUsuario());
            statement.setString(2, representanteInstitucional.getNombre());
            statement.setString(3, representanteInstitucional.getApellidoPaterno());
            statement.setString(4, representanteInstitucional.getApellidoMaterno());
            statement.setString(5, representanteInstitucional.getTelefono());
            statement.setString(6, representanteInstitucional.getCorreo());
            statement.setString(7, representanteInstitucional.getIdioma());
            statement.setString(8, representanteInstitucional.getClaveInstitucional());
            statement.setInt(9, representanteInstitucional.getIdRepresentanteInstitucional());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Error al insertar el representante institucional.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó el ID del representante institucional.");
                }
            }
        }
    }

    @Override
    public representanteInstitucional obtenerRepresentanteInstitucional(String correo) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_REPRESENTANTE_POR_CORREO_COMANDO)) {
            statement.setString(1, correo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRepresentante(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<representanteInstitucional> obtenerRepresentanteInstitucional() throws SQLException {
        List<representanteInstitucional> representantes = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_TODOS_LOS_REPRESENTANTES_COMANDO);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                representanteInstitucional representanteObjeto = mapRepresentante(resultSet);
                representantes.add(representanteObjeto);
            }
        }
        return representantes;
    }

    @Override
    public boolean actualizarRepresentanteInstitucional(representanteInstitucional representanteInstitucional) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTUALIZAR_REPRESENTANTE_COMANDO)) {
            statement.setInt(1, representanteInstitucional.getIdUsuario());
            statement.setString(2, representanteInstitucional.getNombre());
            statement.setString(3, representanteInstitucional.getApellidoPaterno());
            statement.setString(4, representanteInstitucional.getApellidoMaterno());
            statement.setString(5, representanteInstitucional.getTelefono());
            statement.setString(6, representanteInstitucional.getIdioma());
            statement.setString(7, representanteInstitucional.getClaveInstitucional());
            statement.setString(8, representanteInstitucional.getCorreo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean eliminarRepresentanteInstitucional(String correo) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ELIMINAR_REPRESENTANTE_COMANDO)) {
            statement.setString(1, correo);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    private representanteInstitucional mapRepresentante(ResultSet resultSet) throws SQLException {
        representanteInstitucional representanteObjeto = new representanteInstitucional(
                resultSet.getInt("idUsuario"),
                resultSet.getString("nombre"),
                resultSet.getString("apellidoPaterno"),
                resultSet.getString("apellidoMaterno"),
                resultSet.getString("telefono"),
                resultSet.getString("correo"),
                resultSet.getString("idioma"),
                resultSet.getString("claveInstitucional"),
                resultSet.getInt("idRepresentanteInstitucional")
        );
        return representanteObjeto;
    }
}
