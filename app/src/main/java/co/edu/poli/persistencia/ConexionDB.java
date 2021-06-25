package co.edu.poli.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionDB {
    static Connection conexion = null;

    public static Connection getConexion() {
        String url = "jdbc:mysql://localhost:3306/transaccion";
        String Usuario = "root";
        String Contrasenia = "";
        try {
            conexion = DriverManager.getConnection(url, Usuario, Contrasenia);
            System.out.println("Conexion establecida con la base de datos");
            conexion.setAutoCommit(false);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        return conexion;
    }

    static int numCuenta, saldo, ingreso, egreso, cliente, cedula;
    static String telefono, nombre1, nombre2, apellido1, apellido2, ciudad;

    public static void crearMovimiento(int numCuenta, int valor, int tipo) throws SQLException {

        final String MOVIMIENTO = "insert into ,movimiento (Numcuen,Fechamov,Valor_money,Tipo) values (?, CURRENT(),?,?)";
        PreparedStatement movimiento = null;

        try {

            movimiento = conexion.prepareStatement(MOVIMIENTO);
            movimiento.setInt(1, numCuenta);
            movimiento.setInt(2, valor);
            movimiento.setInt(3, tipo);
            movimiento.execute();

            conexion.commit();
        } catch (SQLException sql) {
            conexion.rollback();
            sql.printStackTrace();
        }

    }

    public static void actualizarCuenta(int numCuenta, int ingreso, int egreso, int saldo) throws SQLException {
        final String CUENTA = "update Cuenta set Ingreso_money = ?, Egreso_money = ?, Saldo_money = ? where Numcuenta = ?";
        PreparedStatement cuenta = null;

        try {

            cuenta = conexion.prepareStatement(CUENTA);
            cuenta.setInt(1, ingreso);
            cuenta.setInt(2, egreso);
            cuenta.setInt(3, saldo);
            cuenta.setInt(4, numCuenta);
            cuenta.execute();

            conexion.commit();
        } catch (SQLException sql) {
            conexion.rollback();
            sql.printStackTrace();
        }

    }

    public static void consultarCliente(int cedula) throws SQLException {

        try {

            PreparedStatement pst = conexion.prepareStatement("select Cedula, Nombre1, Nombre2, Apellido1, Apellido2, Ciudad.Nombre as Ciudad from Cliente, Ciudad where Ciudad = Codciudad and Cedula = ?");

            pst.setInt(1, cedula);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cedula = rs.getInt("Cedula");
                nombre1 = rs.getString("Nombre1");
                nombre2 = rs.getString("Nombre2");
                apellido1 = rs.getString("Apellido1");
                apellido2 = rs.getString("Apellido2");
                ciudad = rs.getString("Ciudad");
            }

        } catch (SQLException sql) {
            sql.printStackTrace();
        }

    }

    public static void consultarCuenta(int cuenta) throws SQLException {

        try {

            PreparedStatement pst = conexion.prepareStatement("select * from Cuenta where Numcuenta = ?");

            pst.setInt(1, cuenta);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                numCuenta = rs.getInt("Numcuenta");
                ingreso = rs.getInt("Ingreso_money");
                egreso = rs.getInt("Egreso_money");
                saldo = rs.getInt("Saldo_money");
                cliente = rs.getInt("Cliente");
            }

        } catch (SQLException sql) {
            sql.printStackTrace();
        }

    }

    public static void consultarTel(int cedula) throws SQLException {

        try {

            PreparedStatement pst = conexion.prepareStatement("select * from Telefono where Ced = ?");

            pst.setInt(1, cedula);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                telefono = rs.getString("Tel");
            }

        } catch (SQLException sql) {
            sql.printStackTrace();
        }

    }

    public static int getIngreso() {
        return ingreso;
    }

    public static int getEgreso() {
        return egreso;
    }

    public static int getSaldo() {
        return saldo;
    }

    public static String getTelefono() {
        return telefono;
    }

    public static String getNombre() {
        return apellido1 + " " + apellido2 + " " + nombre1 + " " + nombre2;
    }

    public static String getCiudad() {
        return ciudad;
    }

}
