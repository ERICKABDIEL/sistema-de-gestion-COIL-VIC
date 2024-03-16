/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sdgcoilvic.logicaDeNegocio.interfaces;

import java.sql.SQLException;
import java.util.List;
import sdgcoilvic.logicaDeNegocio.clases.colaboracion;

/**
 *
 * @author abdie
 */
public interface IColaboracion {
    int registrarColaboracion(colaboracion colaboracion) throws SQLException;
    List<colaboracion> obtenerTodosLosEstudiantes() throws SQLException;
    boolean actualizarColaboracion(colaboracion colaboracion) throws SQLException;
}