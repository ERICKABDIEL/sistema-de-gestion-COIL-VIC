package sdgcoilvic.logicaDeNegocio.DAOImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sdgcoilvic.accesoADatos.manejadorBaseDeDatos;
import sdgcoilvic.logicaDeNegocio.clases.cursoTaller;
import sdgcoilvic.logicaDeNegocio.interfaces.ICursoTaller;

public class cursoTallerDAO implements ICursoTaller {
    private final String AGREGAR_CURSOTALLER_COMANDO = "INSERT INTO cursoTaller "
            + "(nombre, horario, numeroEstudiante, fechaInicio, fechaCierre) "
            + "VALUES (?, ?, ?, ?, ?)";

    private final String OBTENER_CURSOTALLER_POR_ID_COMANDO = "SELECT * FROM cursoTaller WHERE idCursoTaller = ?";
    private final String OBTENER_TODOS_LOS_CURSOTALLERES_COMANDO = "SELECT * FROM cursoTaller";
    private final String ACTUALIZAR_CURSOTALLER_COMANDO = "UPDATE cursoTaller SET nombre = ?, horario = ?, numeroEstudiante = ?, fechaInicio = ?, fechaCierre = ? WHERE idCursoTaller = ?";
    private final String ELIMINAR_CURSOTALLER_COMANDO = "DELETE FROM cursoTaller WHERE idCursoTaller = ?";

    private final manejadorBaseDeDatos databaseConnection;

    public cursoTallerDAO(manejadorBaseDeDatos databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public int registrarCursoTaller(cursoTaller course) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(AGREGAR_CURSOTALLER_COMANDO, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getNombre());
            statement.setString(2, course.getHorario());
            statement.setInt(3, course.getNumeroEstudiante());
            statement.setDate(4, new java.sql.Date(course.getFechaInicio().getTime()));
            statement.setDate(5, new java.sql.Date(course.getFechaCierre().getTime()));
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Error al insertar el curso/taller.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    course.setIdCursoTaller(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("No se generó el ID del curso/taller.");
                }
            }
            return course.getIdCursoTaller();
        }
    }

    @Override
    public cursoTaller obtenerCursoTallerPorId(int id) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_CURSOTALLER_POR_ID_COMANDO)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapCursoTaller(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<cursoTaller> obtenerTodosLosCursosTalleres() throws SQLException {
        List<cursoTaller> cursosTalleres = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(OBTENER_TODOS_LOS_CURSOTALLERES_COMANDO);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cursoTaller cursoTaller = mapCursoTaller(resultSet);
                cursosTalleres.add(cursoTaller);
            }
        }
        return cursosTalleres;
    }

    @Override
    public boolean actualizarCursoTaller(cursoTaller course) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTUALIZAR_CURSOTALLER_COMANDO)) {
            statement.setString(1, course.getNombre());
            statement.setString(2, course.getHorario());
            statement.setInt(3, course.getNumeroEstudiante());
            statement.setDate(4, new java.sql.Date(course.getFechaInicio().getTime()));
            statement.setDate(5, new java.sql.Date(course.getFechaCierre().getTime()));
            statement.setInt(6, course.getIdCursoTaller());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean eliminarCursoTaller(int id) throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(ELIMINAR_CURSOTALLER_COMANDO)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

   private cursoTaller mapCursoTaller(ResultSet resultSet) throws SQLException {
    cursoTaller curso = new cursoTaller();
    curso.setIdCursoTaller(resultSet.getInt("idCursoTaller"));
    curso.setNombre(resultSet.getString("nombre"));
    curso.setHorario(resultSet.getString("horario"));
    curso.setNumeroEstudiante(resultSet.getInt("numeroEstudiante"));
    curso.setFechaInicio(resultSet.getDate("fechaInicio"));
    curso.setFechaCierre(resultSet.getDate("fechaCierre"));
    return curso;
}

}
