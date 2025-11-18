package udistrital.pacientedao.modelo;

/**
 * Representa la tabla medicina.
 */
public class Medicina {
    private Integer idMedicina;       // numeric(5,0)
    private String nombreGenerico;    // nombregenerico

    public Medicina(Integer idMedicina, String nombreGenerico) {
        this.idMedicina = idMedicina;
        this.nombreGenerico = nombreGenerico;
    }

    public Integer getIdMedicina() {
        return idMedicina;
    }

    public void setIdMedicina(Integer idMedicina) {
        this.idMedicina = idMedicina;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    @Override
    public String toString() {
        return "Medicina{" +
                "idMedicina=" + idMedicina +
                ", nombreGenerico='" + nombreGenerico + '\'' +
                '}';
    }
}
