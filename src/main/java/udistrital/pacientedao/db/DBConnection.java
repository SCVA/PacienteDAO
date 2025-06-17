/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.pacientedao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Sebastian
 */
public abstract class DBConnection {
    protected String url;
    private Connection conn;
    protected final Properties props = new Properties();
    
    public Connection getConn() throws SQLException{
        conn = DriverManager.getConnection(url,props);
        return conn;
    }
}
