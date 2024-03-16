package sdgcoilvic.logicaDeNegocio.clases;

public class colaboracion {
    private int numeroEstudiantes;
    private String fechaCierre;
    private String fechaInicio;
    private String tipoColaboracion;
    private String experienciaEducativa;
    private int idColaboracion;

    public colaboracion(int numeroEstudiantes, String fechaInicio, String fechaCierre, String tipoColaboracion, String experienciaEducativa, int idColaboracion) {
        this.numeroEstudiantes = numeroEstudiantes;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.tipoColaboracion = tipoColaboracion;
        this.experienciaEducativa = experienciaEducativa;
        this.idColaboracion = idColaboracion;
    }

    public int getNumeroEstudiantes() {
        return numeroEstudiantes;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getTipoColaboracion() {
        return tipoColaboracion;
    }

    public String getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setNumeroEstudiantes(int numeroEstudiantes) {
        this.numeroEstudiantes = numeroEstudiantes;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setTipoColaboracion(String tipoColaboracion) {
        this.tipoColaboracion = tipoColaboracion;
    }

    public void setExperienciaEducativa(String experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }
}
