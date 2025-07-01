package udistrital.pacientedao.dao;

import udistrital.pacientedao.db.DBConnection;
import udistrital.pacientedao.modelo.Farmacia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO para la entidad Farmacia.
 */
public class FarmaciaDAO implements GenericDAO {

    private DBConnection con;

    public FarmaciaDAO(DBConnection con) {
        this.con = con;
    }

    @Override
    public boolean crear(Object obj) throws SQLException {
        Farmacia f = (Farmacia) obj;
        final String sql = "INSERT INTO public.farmacia " +
                "(idfarmacia, nombref, calle, carrera, numero) VALUES (?,?,?,?,?)";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, f.getIdFarmacia());
            ps.setString(2, f.getNombre());
            ps.setString(3, f.getCalle());
            ps.setString(4, f.getCarrera());
            ps.setString(5, f.getNumero());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public Object buscarPorId(Object id) throws SQLException {
        int idFarmacia = (Integer) id;
        final String sql = "SELECT * FROM public.farmacia WHERE idfarmacia = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idFarmacia);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList listarTodos() throws SQLException {
        final String sql = "SELECT * FROM public.farmacia ORDER BY idfarmacia";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ArrayList<Farmacia> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
            return lista;
        }
    }

    @Override
    public boolean actualizar(Object obj) throws SQLException {
        Farmacia f = (Farmacia) obj;
        final String sql = "UPDATE public.farmacia SET " +
                "nombref = ?, calle = ?, carrera = ?, numero = ? " +
                "WHERE idfarmacia = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, f.getNombre());
            ps.setString(2, f.getCalle());
            ps.setString(3, f.getCarrera());
            ps.setString(4, f.getNumero());
            ps.setInt(5, f.getIdFarmacia());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        int idFarmacia = (Integer) id;
        final String sql = "DELETE FROM public.farmacia WHERE idfarmacia = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idFarmacia);
            ps.executeUpdate();
        }
        return true;
    }

    private Farmacia mapear(ResultSet rs) throws SQLException {
        return new Farmacia(
                rs.getInt("idfarmacia"),
                rs.getString("nombref"),
                rs.getString("calle"),
                rs.getString("carrera"),
                rs.getString("numero")
        );
    }
}
