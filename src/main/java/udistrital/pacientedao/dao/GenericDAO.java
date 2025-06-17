/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package udistrital.pacientedao.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public interface GenericDAO {
    boolean crear(Object obj) throws SQLException;
    Object buscarPorId(Object id) throws SQLException;
    ArrayList listarTodos() throws SQLException;
    boolean actualizar(Object obj) throws SQLException;
    boolean eliminar(Object id) throws SQLException;
}
