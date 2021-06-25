package co.edu.poli.persistencia.dao;

import co.edu.poli.persistencia.model.Ciudad;
import co.edu.poli.persistencia.model.Cliente;
import co.edu.poli.persistencia.model.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public static Cliente read(Integer cedula, Connection connection) throws SQLException {
        String sql = "SELECT Cedula, Nombre1, Nombre2, Apellido1, Apellido2, c.Codciudad, c.Nombre, p.Codpais, p.Nombre ";
        sql += "FROM Cliente ";
        sql += "JOIN Ciudad c on (c.Codciudad = Ciudad) ";
        sql += "JOIN Pais p on (p.codpais = c.Pais) ";
        sql += "WHERE Cedula=?";
        // String sql = "INSERT INTO Cliente (Cedula, Nombre1, Nombre2, Apellido1, Apellido2, Ciudad) VALUES(?, ?, ?, ?, ?, ?)";
//        String sql = "INSERT INTO Cliente (Cedula, Nombre1, Nombre2, Apellido1, Apellido2, Ciudad) VALUES(80258757, 'Darwison', '', 'Orjuela', 'Vallejo', 12345)";
        try (PreparedStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, cedula);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    String primerNombre = resultSet.getString(2);
                    String segundoNombre = resultSet.getString(3);
                    String primerApellido = resultSet.getString(4);
                    String segundoApellido = resultSet.getString(5);
                    Pais pais = new Pais(resultSet.getInt(8), resultSet.getString(9));
                    Ciudad ciudad = new Ciudad(resultSet.getInt(6), resultSet.getString(7), pais);

                    return new Cliente(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, ciudad);
                }

                throw new RuntimeException("Cliente no encontrado: " + cedula);
            }
        }
    }
}
