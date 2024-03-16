package sdgcoilvic.logicaDeNegocio.clases;

public class representanteInstitucional extends usuario {
    private int idRepresentanteInstitucional;

    public representanteInstitucional(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, String idioma, String claveInstitucional, int idRepresentanteInstitucional) {
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, idioma, claveInstitucional);
        this.idRepresentanteInstitucional = idRepresentanteInstitucional;
    }

    public int getIdRepresentanteInstitucional() {
        return idRepresentanteInstitucional;
    }

    public void setIdRepresentanteInstitucional(int idRepresentanteInstitucional) {
        this.idRepresentanteInstitucional = idRepresentanteInstitucional;
    }
}
