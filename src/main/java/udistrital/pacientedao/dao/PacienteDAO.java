/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.pacientedao.dao;

import udistrital.pacientedao.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import udistrital.pacientedao.modelo.Paciente;

/**
 *
 * @author Sebastian
 */
public class PacienteDAO implements GenericDAO {

    private DBConnection con;

    public PacienteDAO(DBConnection con) {
        this.con = con;
    }

    @Override
    public boolean crear(Object obj) throws SQLException {
        Paciente p = (Paciente) obj;
        final String sql
                = "INSERT INTO public.paciente "
                + "(cedula, fechanac, primern, segundon, primera, segundoa) "
                + "VALUES (?,?,?,?,?,?)";
        try (
                Connection c = con.getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql)) {
                    ps.setLong(1, p.getCedula());
                    ps.setObject(2, p.getFechaNac());
                    ps.setString(3, p.getPrimerNombre());
                    ps.setString(4, p.getSegundoNombre());
                    ps.setString(5, p.getPrimerApellido());
                    ps.setString(6, p.getSegundoApellido());
                    ps.executeUpdate();
                }
        return true;
    }

    @Override
    public Object buscarPorId(Object id) throws SQLException {
        int cedula = (Integer) id;
        final String sql = "SELECT * FROM public.paciente WHERE cedula = ?";

        try (Connection c = con.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, cedula);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public ArrayList listarTodos() throws SQLException {
        final String sql = "SELECT * FROM public.paciente ORDER BY cedula";

        try (Connection c = con.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ArrayList<Paciente> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
            return lista;
        }
    }

    @Override
    public boolean actualizar(Object obj) throws SQLException {
        Paciente p = (Paciente) obj;
        final String sql =
            "UPDATE public.paciente SET " +
            "fechanac = ?, primern = ?, segundon = ?, primera = ?, segundoa = ? " +
            "WHERE cedula = ?";

        try (Connection c = con.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setObject(1, p.getFechaNac());
            ps.setString(2, p.getPrimerNombre());
            ps.setString(3, p.getSegundoNombre());
            ps.setString(4, p.getPrimerApellido());
            ps.setString(5, p.getSegundoApellido());
            ps.setLong(6, p.getCedula());

            ps.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean eliminar(Object id) throws SQLException {
        int cedula = (Integer) id;
        final String sql = "DELETE FROM public.paciente WHERE cedula = ?";

        try (Connection c = con.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, cedula);
            ps.executeUpdate();
        }
        return true;
    }

    private Paciente mapear(ResultSet rs) throws SQLException {
        return new Paciente(
            rs.getLong("cedula"),
            rs.getObject("fechanac", OffsetDateTime.class),
            rs.getString("primern"),
            rs.getString("segundon"),
            rs.getString("primera"),
            rs.getString("segundoa")
        );
    }
}