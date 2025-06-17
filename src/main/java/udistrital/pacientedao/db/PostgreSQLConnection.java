/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.pacientedao.db;

import java.sql.*;

/**
 *
 * @author Sebastian
 */
public class PostgreSQLConnection extends DBConnection{
    private PostgreSQLConnection instancia;
    private static final String HOST = "localhost";
    private static final String PORT = "5434";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String DATABASE = "Hospital";

    private PostgreSQLConnection() {
        url = "jdbc:postgresql://";
        props.setProperty("host", HOST);
        props.setProperty("port", PORT);
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        props.setProperty("database", DATABASE);
    }

    @Override
    public Connection getConnection () throws SQLException{
        if(instancia==null)
            instancia = new PostgreSQLConnection();
        return instancia.getConn();
    }
}
