package sdgcoilvic.logicaDeNegocio.clases;

public class coordinador extends usuario {
    private int idCoordinador;

    public coordinador(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, String idioma, String claveInstitucional, int idCoordinador) {
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, idioma, claveInstitucional);
        this.idCoordinador = idCoordinador;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }
}
