package sdgcoilvic.logicaDeNegocio.clases;
/**
 *
 * @author abdie
 */


public class apoyoDeGestion extends usuario {
    private int idColaboracion;

    public apoyoDeGestion(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, String idioma, String claveInstitucional, int idColaboracion) {
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, idioma, claveInstitucional);
        this.idColaboracion = idColaboracion;
    }

    public String getCorreo() {
        return correo;
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }
}