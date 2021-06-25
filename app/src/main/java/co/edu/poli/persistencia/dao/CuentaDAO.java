package co.edu.poli.persistencia.dao;

import co.edu.poli.persistencia.model.Cliente;
import co.edu.poli.persistencia.model.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAO {
    public static void create(Cuenta cuenta, Connection connection) throws SQLException {
    }

    public static Cuenta read(Integer numero, Connection connection) throws SQLException {
        String sql = "SELECT Numcuenta, Ingreso_money, Egreso_money, Saldo_money, Cliente FROM Cuenta WHERE Numcuenta=?";
        try (PreparedStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, numero);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Cliente cliente = ClienteDAO.read(resultSet.getInt(5), connection);
                    return new Cuenta(numero, resultSet.getDouble(2), resultSet.getDouble(3), resultSet.getDouble(4), cliente);
                }

                throw new RuntimeException("Cuenta no encontrada: " + numero);
            }
        }
    }

    public static void update(Cuenta cuenta, Connection connection) throws SQLException {
    }
}
