package udistrital.pacientedao.dao;

import udistrital.pacientedao.db.DBConnection;
import udistrital.pacientedao.modelo.Medicina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO para la entidad Medicina.
 */
public class MedicinaDAO implements GenericDAO {

    private DBConnection con;

    public MedicinaDAO(DBConnection con) {
        this.con = con;
    }

    @Override
    public boolean crear(Object obj) throws SQLException {
        Medicina m = (Medicina) obj;
        final String sql = "INSERT INTO public.medicina " +
                "(idmedicina, nombregenerico) VALUES (?, ?)";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, m.getIdMedicina());
            ps.setString(2, m.getNombreGenerico());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public Object buscarPorId(Object id) throws SQLException {
        int idMedicina = (Integer) id;
        final String sql = "SELECT * FROM public.medicina WHERE idmedicina = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idMedicina);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList listarTodos() throws SQLException {
        final String sql = "SELECT * FROM public.medicina ORDER BY idmedicina";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ArrayList<Medicina> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
            return lista;
        }
    }

    @Override
    public boolean actualizar(Object obj) throws SQLException {
        Medicina m = (Medicina) obj;
        final String sql = "UPDATE public.medicina SET " +
                "nombregenerico = ? WHERE idmedicina = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getNombreGenerico());
            ps.setInt(2, m.getIdMedicina());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        int idMedicina = (Integer) id;
        final String sql = "DELETE FROM public.medicina WHERE idmedicina = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idMedicina);
            ps.executeUpdate();
        }
        return true;
    }

    private Medicina mapear(ResultSet rs) throws SQLException {
        return new Medicina(
                rs.getInt("idmedicina"),
                rs.getString("nombregenerico")
        );
    }
}
