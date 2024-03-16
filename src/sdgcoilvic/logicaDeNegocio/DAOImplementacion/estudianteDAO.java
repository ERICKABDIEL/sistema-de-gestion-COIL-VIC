package sdgcoilvic.logicaDeNegocio.DAOImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sdgcoilvic.accesoADatos.manejadorBaseDeDatos;
import sdgcoilvic.logicaDeNegocio.clases.estudiante;
import sdgcoilvic.logicaDeNegocio.interfaces.IEstudiantes;

public class estudianteDAO implements IEstudiantes {
    private final String AGREGAR_ESTUDIANTE_COMANDO = "INSERT INTO estudiante "
            + "(matricula, Usuario_idUsuario, Colaboracion_idColaboracion) "
            + "VALUES (?, ?, ?)";

    private final String OBTENER_ESTUDIANTE_POR_ID_COMANDO = "SELECT * FROM estudiante WHERE idEstudiante = ?";
    private final String OBTENER_TODOS_LOS_ESTUDIANTES_COMANDO = "SELECT * FROM estudiante";
    private final String ACTUALIZAR_ESTUDIANTE_COMANDO = "UPDATE estudiante SET matricula = ?, Usuario_idUsuario = ?, Colaboracion_idColaboracion = ? WHERE idEstudiante = ?";
    private final String ELIMINAR_ESTUDIANTE_COMANDO = "DELETE FROM estudiante WHERE idEstudiante = ?";

    private final manejadorBaseDeDatos databaseConnection;

    public estudianteDAO(manejadorBaseDeDatos databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public int registrarEstudiante(estudiante estudiante) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(AGREGAR_ESTUDIANTE_COMANDO, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, estudiante.getMatricula());
            statement.setInt(2, estudiante.getIdUsuario());
            statement.setInt(3, estudiante.getIdColaboracion());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Error al insertar el estudiante.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó el ID del estudiante.");
                }
            }
        }
    }

    public estudiante obtenerEstudiantePorMatricula(int id) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_ESTUDIANTE_POR_ID_COMANDO)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapEstudiante(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<estudiante> obtenerTodosLosEstudiantes() throws SQLException {
        List<estudiante> estudiantes = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_TODOS_LOS_ESTUDIANTES_COMANDO);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                estudiante estudianteObjeto = mapEstudiante(resultSet);
                estudiantes.add(estudianteObjeto);
            }
        }
        return estudiantes;
    }

    @Override
    public boolean actualizarEstudiante(estudiante estudiante) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTUALIZAR_ESTUDIANTE_COMANDO)) {
            statement.setString(1, estudiante.getMatricula());
            statement.setInt(2, estudiante.getIdUsuario());
            statement.setInt(3, estudiante.getIdColaboracion());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean eliminarEstudiante(int id) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ELIMINAR_ESTUDIANTE_COMANDO)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    private estudiante mapEstudiante(ResultSet resultSet) throws SQLException {
        estudiante estudianteObjeto = new estudiante(
                resultSet.getInt("Usuario_idUsuario"),
                resultSet.getString("nombre"),
                resultSet.getString("apellidoPaterno"),
                resultSet.getString("apellidoMaterno"),
                resultSet.getString("telefono"),
                resultSet.getString("correo"),
                resultSet.getString("idioma"),
                resultSet.getString("claveInstitucional"),
                resultSet.getString("matricula"),
                resultSet.getInt("Colaboracion_idColaboracion")
        );
        return estudianteObjeto;
    }

    @Override
    public estudiante obtenerEstudiantePorMatricula(String matricula) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarEstudiante(String matricula) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
