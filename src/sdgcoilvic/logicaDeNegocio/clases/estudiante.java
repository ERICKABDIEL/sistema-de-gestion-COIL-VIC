
package sdgcoilvic.logicaDeNegocio.clases;

   public class estudiante extends usuario {
    private String matricula;
    private int idColaboracion;

    public estudiante(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, String idioma, String claveInstitucional, String matricula, int idColaboracion) {
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, idioma, claveInstitucional);
        this.matricula = matricula;
        this.idColaboracion = idColaboracion;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }
}


