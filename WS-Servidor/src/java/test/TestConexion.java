package test;

import java.sql.Connection;
import util.ConexionDB;

public class TestConexion {

    public static void main(String[] args) {
        ConexionDB cdb = new ConexionDB();
        try ( Connection cn = cdb.conexionDB()) {
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
