package co.edu.poli.persistencia.dao;

import co.edu.poli.persistencia.model.Movimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MovimientoDAO {
    public static void create(Movimiento movimiento, Connection connection) throws SQLException {
        final String sql = "INSERT INTO Movimiento (Numcuen, Fechamov, Valor_money, Tipo) values (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movimiento.cuenta);
            stmt.setTimestamp(2, Timestamp.valueOf(movimiento.fecha));
            stmt.setDouble(3, movimiento.valor);
            stmt.setInt(4, movimiento.tipo.id);
            stmt.execute();
        }
    }
}
