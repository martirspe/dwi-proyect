package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {    
    private final String URL = "jdbc:mysql://localhost:3306/dwi-proyect";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String USER = "root";
    private final String PASS = "";
    
    public Connection conexionDB() throws SQLException{
    Connection c = null;
        try {
            Class.forName(DRIVER).newInstance();
            c = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | 
                 IllegalAccessException | 
                 InstantiationException | 
                 SQLException e) {
            throw new SQLException(e.getMessage());
        }    
    return c;
    }
}



