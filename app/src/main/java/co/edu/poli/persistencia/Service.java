package co.edu.poli.persistencia;

import co.edu.poli.persistencia.dao.CuentaDAO;
import co.edu.poli.persistencia.dao.MovimientoDAO;
import co.edu.poli.persistencia.model.Cuenta;
import co.edu.poli.persistencia.model.Movimiento;
import co.edu.poli.persistencia.model.TipoMovimiento;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Service {
    public static Cuenta consultar(Integer nroCuenta) {
        try (Connection connection = ConexionDB.getConexion()) {
            return CuentaDAO.read(nroCuenta, connection);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException("Ha ocurrido un error inesperado");
        }
    }

    public static Cuenta consignar(Integer numeroCuenta, double monto) {
        try (Connection connection = ConexionDB.getConexion()) {
            Cuenta cuenta = CuentaDAO.read(numeroCuenta, connection);
            cuenta.saldo += monto;
            CuentaDAO.update(cuenta, connection);

            Movimiento movimiento = new Movimiento(numeroCuenta, LocalDateTime.now(), monto, TipoMovimiento.CONSIGNACION);
            MovimientoDAO.create(movimiento, connection);

            connection.commit();

            return cuenta;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException("Ha ocurrido un error inesperado");
        }
    }

    public static Cuenta retirar(Integer numeroCuenta, double monto) {
        try (Connection connection = ConexionDB.getConexion()) {
            Cuenta cuenta = CuentaDAO.read(numeroCuenta, connection);
            if (monto > cuenta.saldo) {
                throw new RuntimeException("Saldo insuficiente");
            }

            cuenta.saldo -= monto;
            CuentaDAO.update(cuenta, connection);

            Movimiento movimiento = new Movimiento(numeroCuenta, LocalDateTime.now(), monto, TipoMovimiento.RETIRO);
            MovimientoDAO.create(movimiento, connection);

            connection.commit();

            return cuenta;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException("Ha ocurrido un error inesperado");
        }
    }

    public static void transferir(Integer nroCuentaOrigen, Integer nroCuentaDestino, double monto) {
        try (Connection connection = ConexionDB.getConexion()) {
            Cuenta cuentaOrigen = CuentaDAO.read(nroCuentaOrigen, connection);

            if (monto > cuentaOrigen.saldo) {
                throw new RuntimeException("Saldo insuficiente");
            }

            Cuenta cuentaDestino = CuentaDAO.read(nroCuentaDestino, connection);

            cuentaOrigen.saldo -= monto;
            cuentaDestino.saldo += monto;

            CuentaDAO.update(cuentaOrigen, connection);
            CuentaDAO.update(cuentaDestino, connection);

            MovimientoDAO.create(new Movimiento(nroCuentaOrigen, LocalDateTime.now(), monto, TipoMovimiento.RETIRO), connection);
            MovimientoDAO.create(new Movimiento(nroCuentaDestino, LocalDateTime.now(), monto, TipoMovimiento.CONSIGNACION), connection);

            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException("Ha ocurrido un error inesperado");
        }
    }
}
