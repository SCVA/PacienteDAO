package udistrital.pacientedao.modelo;

/**
 * Representa la tabla farmacia.
 */
public class Farmacia {
    private Integer idFarmacia; // numeric(3,0)
    private String nombre;      // nombref
    private String calle;
    private String carrera;
    private String numero;

    public Farmacia(Integer idFarmacia, String nombre, String calle, String carrera, String numero) {
        this.idFarmacia = idFarmacia;
        this.nombre = nombre;
        this.calle = calle;
        this.carrera = carrera;
        this.numero = numero;
    }

    public Integer getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(Integer idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "idFarmacia=" + idFarmacia +
                ", nombre='" + nombre + '\'' +
                ", calle='" + calle + '\'' +
                ", carrera='" + carrera + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
