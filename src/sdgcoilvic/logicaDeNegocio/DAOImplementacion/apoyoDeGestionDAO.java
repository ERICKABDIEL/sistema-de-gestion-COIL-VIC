package sdgcoilvic.logicaDeNegocio.DAOImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sdgcoilvic.accesoADatos.manejadorBaseDeDatos;
import sdgcoilvic.logicaDeNegocio.clases.apoyoDeGestion;
import sdgcoilvic.logicaDeNegocio.interfaces.IApoyoDeGestion;

public class apoyoDeGestionDAO implements IApoyoDeGestion {
    private final String AGREGAR_APOYO_DE_GESTION_COMANDO = "INSERT INTO apoyo_de_gestion "
            + "(idUsuario, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, idioma, claveInstitucional, idColaboracion) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String OBTENER_APOYO_DE_GESTION_POR_CORREO_COMANDO = "SELECT * FROM apoyo_de_gestion WHERE correo = ?";
    private final String OBTENER_TODOS_LOS_APOYOS_DE_GESTION_COMANDO = "SELECT * FROM apoyo_de_gestion";
    private final String ACTUALIZAR_APOYO_DE_GESTION_COMANDO = "UPDATE apoyo_de_gestion SET idUsuario = ?, nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, idioma = ?, claveInstitucional = ? WHERE correo = ?";
    private final String ELIMINAR_APOYO_DE_GESTION_COMANDO = "DELETE FROM apoyo_de_gestion WHERE correo = ?";

    private final manejadorBaseDeDatos databaseConnection;

    public apoyoDeGestionDAO(manejadorBaseDeDatos databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public int registrarApoyoDeGestion(apoyoDeGestion apoyoDeGestion) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(AGREGAR_APOYO_DE_GESTION_COMANDO, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, apoyoDeGestion.getIdUsuario());
            statement.setString(2, apoyoDeGestion.getNombre());
            statement.setString(3, apoyoDeGestion.getApellidoPaterno());
            statement.setString(4, apoyoDeGestion.getApellidoMaterno());
            statement.setString(5, apoyoDeGestion.getTelefono());
            statement.setString(6, apoyoDeGestion.getCorreo());
            statement.setString(7, apoyoDeGestion.getIdioma());
            statement.setString(8, apoyoDeGestion.getClaveInstitucional());
            statement.setInt(9, apoyoDeGestion.getIdColaboracion());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Error al insertar el apoyo de gestión.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó el ID del apoyo de gestión.");
                }
            }
        }
    }

    @Override
    public apoyoDeGestion obtenerEstudiantePorMatricula(String correo) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_APOYO_DE_GESTION_POR_CORREO_COMANDO)) {
            statement.setString(1, correo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapApoyoDeGestion(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<apoyoDeGestion> obtenerTodosLosEstudiantes() throws SQLException {
        List<apoyoDeGestion> apoyosDeGestion = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_TODOS_LOS_APOYOS_DE_GESTION_COMANDO);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                apoyoDeGestion apoyoDeGestionObjeto = mapApoyoDeGestion(resultSet);
                apoyosDeGestion.add(apoyoDeGestionObjeto);
            }
        }
        return apoyosDeGestion;
    }

    @Override
    public boolean actualizarApoyoDeGestion(apoyoDeGestion apoyoDeGestion) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTUALIZAR_APOYO_DE_GESTION_COMANDO)) {
            statement.setInt(1, apoyoDeGestion.getIdUsuario());
            statement.setString(2, apoyoDeGestion.getNombre());
            statement.setString(3, apoyoDeGestion.getApellidoPaterno());
            statement.setString(4, apoyoDeGestion.getApellidoMaterno());
            statement.setString(5, apoyoDeGestion.getTelefono());
            statement.setString(6, apoyoDeGestion.getIdioma());
            statement.setString(7, apoyoDeGestion.getClaveInstitucional());
            statement.setString(8, apoyoDeGestion.getCorreo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean eliminarApoyoDeGestion(String correo) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ELIMINAR_APOYO_DE_GESTION_COMANDO)) {
            statement.setString(1, correo);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    private apoyoDeGestion mapApoyoDeGestion(ResultSet resultSet) throws SQLException {
        apoyoDeGestion apoyoDeGestionObjeto = new apoyoDeGestion(
                resultSet.getInt("idUsuario"),
                resultSet.getString("nombre"),
                resultSet.getString("apellidoPaterno"),
                resultSet.getString("apellidoMaterno"),
                resultSet.getString("telefono"),
                resultSet.getString("correo"),
                resultSet.getString("idioma"),
                resultSet.getString("claveInstitucional"),
                resultSet.getInt("idColaboracion")
        );
        return apoyoDeGestionObjeto;
    }
}
