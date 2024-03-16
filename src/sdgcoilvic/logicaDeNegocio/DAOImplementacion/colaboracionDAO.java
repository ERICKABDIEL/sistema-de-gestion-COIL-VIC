package sdgcoilvic.logicaDeNegocio.DAOImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sdgcoilvic.accesoADatos.manejadorBaseDeDatos;
import sdgcoilvic.logicaDeNegocio.clases.colaboracion;
import sdgcoilvic.logicaDeNegocio.interfaces.IColaboracion;

public class colaboracionDAO implements IColaboracion {
    private final String AGREGAR_COLABORACION_COMANDO = "INSERT INTO colaboracion "
            + "(numeroEstudiantes, fechaInicio, fechaCierre, tipoColaboracion, experienciaEducativa, idColaboracion) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private final String OBTENER_COLABORACION_POR_ID_COMANDO = "SELECT * FROM colaboracion WHERE idColaboracion = ?";
    private final String OBTENER_TODAS_LAS_COLABORACIONES_COMANDO = "SELECT * FROM colaboracion";
    private final String ACTUALIZAR_COLABORACION_COMANDO = "UPDATE colaboracion SET numeroEstudiantes = ?, fechaInicio = ?, fechaCierre = ?, tipoColaboracion = ?, experienciaEducativa = ? WHERE idColaboracion = ?";
    private final String ELIMINAR_COLABORACION_COMANDO = "DELETE FROM colaboracion WHERE idColaboracion = ?";

    private final manejadorBaseDeDatos databaseConnection;

    public colaboracionDAO(manejadorBaseDeDatos databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public int registrarColaboracion(colaboracion colaboracion) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(AGREGAR_COLABORACION_COMANDO, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, colaboracion.getNumeroEstudiantes());
            statement.setString(2, colaboracion.getFechaInicio());
            statement.setString(3, colaboracion.getFechaCierre());
            statement.setString(4, colaboracion.getTipoColaboracion());
            statement.setString(5, colaboracion.getExperienciaEducativa());
            statement.setInt(6, colaboracion.getIdColaboracion());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Error al insertar la colaboración.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó el ID de la colaboración.");
                }
            }
        }
    }

    public colaboracion obtenerColaboracionPorId(int id) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_COLABORACION_POR_ID_COMANDO)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapColaboracion(resultSet);
                }
            }
        }
        return null;
    }

    public List<colaboracion> obtenerTodosLasColaboraciones() throws SQLException {
        List<colaboracion> colaboraciones = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_TODAS_LAS_COLABORACIONES_COMANDO);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                colaboracion colaboracionObjeto = mapColaboracion(resultSet);
                colaboraciones.add(colaboracionObjeto);
            }
        }
        return colaboraciones;
    }

    @Override
    public boolean actualizarColaboracion(colaboracion colaboracion) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTUALIZAR_COLABORACION_COMANDO)) {
            statement.setInt(1, colaboracion.getNumeroEstudiantes());
            statement.setString(2, colaboracion.getFechaInicio());
            statement.setString(3, colaboracion.getFechaCierre());
            statement.setString(4, colaboracion.getTipoColaboracion());
            statement.setString(5, colaboracion.getExperienciaEducativa());
            statement.setInt(6, colaboracion.getIdColaboracion());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean eliminarColaboracion(int id) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ELIMINAR_COLABORACION_COMANDO)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    private colaboracion mapColaboracion(ResultSet resultSet) throws SQLException {
        colaboracion colaboracionObjeto = new colaboracion(
                resultSet.getInt("numeroEstudiantes"),
                resultSet.getString("fechaInicio"),
                resultSet.getString("fechaCierre"),
                resultSet.getString("tipoColaboracion"),
                resultSet.getString("experienciaEducativa"),
                resultSet.getInt("idColaboracion")
        );
        return colaboracionObjeto;
    }

    @Override
    public List<colaboracion> obtenerTodosLosEstudiantes() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
