package udistrital.pacientedao.modelo;

/**
 * Representa la tabla telefonof.
 */
public class Telefonof {
    private Long telefonof;    // numeric(12,0)
    private Integer idFarmacia; // numeric(3,0)

    public Telefonof(Long telefonof, Integer idFarmacia) {
        this.telefonof = telefonof;
        this.idFarmacia = idFarmacia;
    }

    public Long getTelefonof() {
        return telefonof;
    }

    public void setTelefonof(Long telefonof) {
        this.telefonof = telefonof;
    }

    public Integer getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(Integer idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    @Override
    public String toString() {
        return "Telefonof{" +
                "telefonof=" + telefonof +
                ", idFarmacia=" + idFarmacia +
                '}';
    }
}
