package sdgcoilvic.logicaDeNegocio.clases;

import java.util.Date;

public class cursoTaller {
    private int idCursoTaller;
    private String nombre;
    private String horario;
    private int numeroEstudiante;
    private Date fechaInicio;
    private Date fechaCierre;

    public cursoTaller(int idCursoTaller, String nombre, String horario, int numeroEstudiante, Date fechaInicio, Date fechaCierre) {
        this.idCursoTaller = idCursoTaller;
        this.nombre = nombre;
        this.horario = horario;
        this.numeroEstudiante = numeroEstudiante;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
    }

    public cursoTaller() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdCursoTaller() {
        return idCursoTaller;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public int getNumeroEstudiante() {
        return numeroEstudiante;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setIdCursoTaller(int idCursoTaller) {
        this.idCursoTaller = idCursoTaller;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setNumeroEstudiante(int numeroEstudiante) {
        this.numeroEstudiante = numeroEstudiante;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
}
