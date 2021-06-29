package co.edu.poli.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    static Connection conexion = null;

    public static Connection getConexion() {
        String url = "jdbc:mysql://localhost:3306/transaccion";
        String Usuario = "root";
        String Contrasenia = "9YGgirLtHpy7";
        try {
            conexion = DriverManager.getConnection(url, Usuario, Contrasenia);
            System.out.println("Conexion establecida con la base de datos");
            conexion.setAutoCommit(false);
        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new RuntimeException("No ha sido posible conectarse con la base de datos");
        }

        return conexion;
    }
}
