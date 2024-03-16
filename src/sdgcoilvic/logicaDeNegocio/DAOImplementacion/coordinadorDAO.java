package sdgcoilvic.logicaDeNegocio.DAOImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sdgcoilvic.accesoADatos.manejadorBaseDeDatos;
import sdgcoilvic.logicaDeNegocio.clases.coordinador;
import sdgcoilvic.logicaDeNegocio.interfaces.ICoordinador;

public class coordinadorDAO implements ICoordinador {
    private final String AGREGAR_COORDINADOR_COMANDO = "INSERT INTO coordinador "
            + "(idUsuario, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, idioma, claveInstitucional, idCoordinador) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String OBTENER_COORDINADOR_POR_CORREO_COMANDO = "SELECT * FROM coordinador WHERE correo = ?";
    private final String OBTENER_TODOS_LOS_COORDINADORES_COMANDO = "SELECT * FROM coordinador";
    private final String ACTUALIZAR_COORDINADOR_COMANDO = "UPDATE coordinador SET idUsuario = ?, nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, idioma = ?, claveInstitucional = ? WHERE correo = ?";
    private final String ELIMINAR_COORDINADOR_COMANDO = "DELETE FROM coordinador WHERE correo = ?";

    private final manejadorBaseDeDatos databaseConnection;

    public coordinadorDAO(manejadorBaseDeDatos databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public int registrarCoordinador(coordinador coordinador) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(AGREGAR_COORDINADOR_COMANDO, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, coordinador.getIdUsuario());
            statement.setString(2, coordinador.getNombre());
            statement.setString(3, coordinador.getApellidoPaterno());
            statement.setString(4, coordinador.getApellidoMaterno());
            statement.setString(5, coordinador.getTelefono());
            statement.setString(6, coordinador.getCorreo());
            statement.setString(7, coordinador.getIdioma());
            statement.setString(8, coordinador.getClaveInstitucional());
            statement.setInt(9, coordinador.getIdCoordinador());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Error al insertar el coordinador.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó el ID del coordinador.");
                }
            }
        }
    }

    @Override
    public coordinador obtenerCoordinador(String correo) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_COORDINADOR_POR_CORREO_COMANDO)) {
            statement.setString(1, correo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapCoordinador(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<coordinador> obtenerCoordinador() throws SQLException {
        List<coordinador> coordinadores = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_TODOS_LOS_COORDINADORES_COMANDO);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                coordinador coordinadorObjeto = mapCoordinador(resultSet);
                coordinadores.add(coordinadorObjeto);
            }
        }
        return coordinadores;
    }

    @Override
    public boolean actualizarCoordinador(coordinador coordinador) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTUALIZAR_COORDINADOR_COMANDO)) {
            statement.setInt(1, coordinador.getIdUsuario());
            statement.setString(2, coordinador.getNombre());
            statement.setString(3, coordinador.getApellidoPaterno());
            statement.setString(4, coordinador.getApellidoMaterno());
            statement.setString(5, coordinador.getTelefono());
            statement.setString(6, coordinador.getIdioma());
            statement.setString(7, coordinador.getClaveInstitucional());
            statement.setString(8, coordinador.getCorreo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean eliminarCoordinador(String correo) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ELIMINAR_COORDINADOR_COMANDO)) {
            statement.setString(1, correo);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    private coordinador mapCoordinador(ResultSet resultSet) throws SQLException {
        coordinador coordinadorObjeto = new coordinador(
                resultSet.getInt("idUsuario"),
                resultSet.getString("nombre"),
                resultSet.getString("apellidoPaterno"),
                resultSet.getString("apellidoMaterno"),
                resultSet.getString("telefono"),
                resultSet.getString("correo"),
                resultSet.getString("idioma"),
                resultSet.getString("claveInstitucional"),
                resultSet.getInt("idCoordinador")
        );
        return coordinadorObjeto;
    }
}
