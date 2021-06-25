package co.edu.poli.persistencia;

import co.edu.poli.persistencia.model.Cuenta;

import javax.swing.*;

public class Client {
    private static final String mensajeBienvenida = "\n" +
            " BIENVENIDO AL BANCO XYZ, ESCOJA UNA OPCION\n" +
            " Opcion 0: Cerrar transaccion\n" +
            " Opcion 1: Consignacion\n" +
            " Opcion 2: Retiro\n" +
            " Opcion 3: Consulta\n" +
            " Opcion 4: Transferencia";

    public static void main(String[] args) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog((mensajeBienvenida));
                // Botón cancelar/cerrar
                if (input == null) {
                    break;
                }

                int opcion = Integer.parseInt(input);

                if (opcion == 0) {
                    JOptionPane.showMessageDialog(null, "Conexion cancelada", "BANCO XYZ", JOptionPane.PLAIN_MESSAGE);
                    break;
                }

                String resultado = ejecutarOpcion(opcion);

                if (resultado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Opcion no valida", "BANCO XYZ", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Resultado", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(null, "Opcion no valida", "BANCO XYZ", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String ejecutarOpcion(int opcion) {
        if (opcion <= 0 || opcion > 3) {
            return "";
        }

        int nroCuenta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cuenta"));

        try {
            if (opcion == 1 || opcion == 2) {
                double monto = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad que va a consignar: "));
                Cuenta cuenta;
                if (opcion == 1) {
                    cuenta = Service.consignar(nroCuenta, monto);
                    // mensajeStatus = "Consignacion Realizada";
                } else {
                    cuenta = Service.retirar(nroCuenta, monto);
                    // mensajeStatus = "Retiro Realizado";
                    // mensajeStatus = "Fondos insuficientes";
                }

                return String.format("Cuenta: %d\n Titular: %s\n C.C. %d\n saldo: %s", cuenta.numero, cuenta.cliente.primerNombre, cuenta.cliente.cedula, cuenta.saldo);
            }

            Cuenta cuenta = Service.consultar(nroCuenta);
            return String.format("Cuenta: %d\n Titular: %s\n C.C. %d\n saldo: %s\n Egresos totales: %s\n Ingresos totales: %s\n Ciudad: %s\n Telefono: ", cuenta.numero, cuenta.cliente.primerNombre, cuenta.cliente.cedula, cuenta.saldo, cuenta.egreso, cuenta.ingreso, cuenta.cliente.ciudad.nombre);
            // mensajeStatus = "Consulta realizada";
        } catch (RuntimeException re) {
            return re.getMessage();
        }
    }
}
