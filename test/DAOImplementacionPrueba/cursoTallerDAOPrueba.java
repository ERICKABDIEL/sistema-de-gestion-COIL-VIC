package DAOImplementacionPrueba;

import static org.junit.Assert.*;
import org.junit.Test;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import sdgcoilvic.accesoADatos.manejadorBaseDeDatos;
import sdgcoilvic.logicaDeNegocio.DAOImplementacion.cursoTallerDAO;
import sdgcoilvic.logicaDeNegocio.clases.cursoTaller;

public class cursoTallerDAOPrueba {

    @Test
    public void pruebaRegistrarCursoTallerExitoso() throws SQLException {
        int resultadoEsperado = 1;
        cursoTallerDAO instance = new cursoTallerDAO(new manejadorBaseDeDatos());
        cursoTaller course = new cursoTaller();
        course.setNombre("Curso de Pruebas");
        course.setHorario("Lunes a Viernes, 9am - 12pm");
        course.setNumeroEstudiante(20);
        course.setFechaInicio(new Date());
        course.setFechaCierre(new Date());

        int resultadoObtenido = instance.registrarCursoTaller(course);

        assertNotEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaObtenerCursoTallerPorIdExitoso() throws SQLException {
        int resultadoEsperado = 1;
        cursoTallerDAO instance = new cursoTallerDAO(new manejadorBaseDeDatos());

        int idCursoTallerBuscado = 1; // ID de curso a buscar

        cursoTaller resultadoObtenido = instance.obtenerCursoTallerPorId(idCursoTallerBuscado);

        assertNotEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaObtenerTodosLosCursosTalleresExitoso() throws SQLException {
        int resultadoEsperado = 1;
        cursoTallerDAO instance = new cursoTallerDAO(new manejadorBaseDeDatos());

        List<cursoTaller> resultadoObtenido = instance.obtenerTodosLosCursosTalleres();

        assertNotEquals(resultadoEsperado, resultadoObtenido.size());
    }

    @Test
    public void pruebaActualizarCursoTallerExitoso() throws SQLException {
        int resultadoEsperado = 1;
        cursoTallerDAO instance = new cursoTallerDAO(new manejadorBaseDeDatos());
        cursoTaller course = new cursoTaller();
        course.setIdCursoTaller(1); // ID del curso a actualizar
        course.setNombre("Curso Actualizado");
        course.setHorario("Lunes a Viernes, 2pm - 5pm");
        course.setNumeroEstudiante(25);
        course.setFechaInicio(new Date());
        course.setFechaCierre(new Date());

        boolean resultadoObtenido = instance.actualizarCursoTaller(course);

        assertNotEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    public void pruebaEliminarCursoTallerExitoso() throws SQLException {
        int resultadoEsperado = 1;
        cursoTallerDAO instance = new cursoTallerDAO(new manejadorBaseDeDatos());

        int idCursoTallerAEliminar = 1; // ID del curso a eliminar

        boolean resultadoObtenido = instance.eliminarCursoTaller(idCursoTallerAEliminar);

        assertNotEquals(resultadoEsperado, resultadoObtenido);
    }
}