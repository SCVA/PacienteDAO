package udistrital.pacientedao.dao;

import udistrital.pacientedao.db.DBConnection;
import udistrital.pacientedao.modelo.Telefonof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO para la entidad Telefonof.
 */
public class TelefonofDAO implements GenericDAO {

    private DBConnection con;

    public TelefonofDAO(DBConnection con) {
        this.con = con;
    }

    @Override
    public boolean crear(Object obj) throws SQLException {
        Telefonof t = (Telefonof) obj;
        final String sql = "INSERT INTO public.telefonof (telefonof, idfarmacia) VALUES (?,?)";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, t.getTelefonof());
            ps.setInt(2, t.getIdFarmacia());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public Object buscarPorId(Object id) throws SQLException {
        long telefono = (Long) id;
        final String sql = "SELECT * FROM public.telefonof WHERE telefonof = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, telefono);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList listarTodos() throws SQLException {
        final String sql = "SELECT * FROM public.telefonof ORDER BY telefonof";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ArrayList<Telefonof> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
            return lista;
        }
    }

    @Override
    public boolean actualizar(Object obj) throws SQLException {
        Telefonof t = (Telefonof) obj;
        final String sql = "UPDATE public.telefonof SET idfarmacia = ? WHERE telefonof = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, t.getIdFarmacia());
            ps.setLong(2, t.getTelefonof());
            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        long telefono = (Long) id;
        final String sql = "DELETE FROM public.telefonof WHERE telefonof = ?";
        try (Connection c = con.getConn();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, telefono);
            ps.executeUpdate();
        }
        return true;
    }

    private Telefonof mapear(ResultSet rs) throws SQLException {
        return new Telefonof(
                rs.getLong("telefonof"),
                rs.getInt("idfarmacia")
        );
    }
}
