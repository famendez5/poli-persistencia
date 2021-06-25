package co.edu.poli.persistencia;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        iniciarCliente();

    }

    static String direccion = "localhost";
    static int puerto = 9000;
    static Socket cliente;
    static DataOutputStream salida;
    static DataInputStream entrada;
    static String mensajeSaliente;
    static String mensajeEntrante;

    public static void iniciarCliente() {

        try {
            cliente = new Socket(direccion, puerto);
            salida = new DataOutputStream(cliente.getOutputStream());
            entrada = new DataInputStream(cliente.getInputStream());

            do {

                tipotransaccion = Integer.parseInt(JOptionPane.showInputDialog(("\n BIENVENIDO AL BANCO XYZ, ESCOJA" + " UNA OPCION" + "\n Opcion 0: Cerrar transaccion" + "\n Opcion 1: Consignacion" + "\n Opcion 2: Retiro" + "\n Opcion 3: Consulta")));

                opciones(tipotransaccion);

                mensajeSaliente = transaccionIda();
                salida.writeUTF(mensajeSaliente);

                if (tipotransaccion == 1 || tipotransaccion == 2 || tipotransaccion == 3) {
                    mensajeEntrante = entrada.readUTF();
                }

                if (tipotransaccion == 1 || tipotransaccion == 2) {
                    decodificarTransaccion(mensajeEntrante);
                    JOptionPane.showMessageDialog(null, transacionVuelta(), "Resultado", JOptionPane.PLAIN_MESSAGE);
                } else if (tipotransaccion == 3) {
                    decodificarConsulta(mensajeEntrante);
                    JOptionPane.showMessageDialog(null, consultaVuelta(), "Resultado", JOptionPane.PLAIN_MESSAGE);
                }

            } while (tipotransaccion != 0);

            cliente.close();
        } catch (IOException sock) {
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor", null, JOptionPane.ERROR_MESSAGE);
            sock.printStackTrace();
        }

    }

    static int numCuenta;
    static int cedula;
    static String nombre;
    static int saldo;

    static String telefono;
    static String sexo;
    static String ciudad;
    static int ingreso;
    static int egreso;

    static String mensajeStatus;
    static int tipotransaccion;
    static int monto;

    public static void decodificarTransaccion(String mensajeRecibido) {
        int inicio = 0;
        int contador = 0;
        for (int i = 0; i < mensajeEntrante.length(); i++) {
            if (mensajeRecibido.charAt(i) == ',') {
                switch (contador) {
                    case 0:
                        numCuenta = Integer.parseInt(mensajeEntrante.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        break;
                    case 1:
                        cedula = Integer.parseInt(mensajeEntrante.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        break;
                    case 2:
                        nombre = mensajeEntrante.substring(inicio, i);
                        inicio = i + 1;
                        contador++;
                        break;
                    case 3:
                        saldo = Integer.parseInt(mensajeEntrante.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        mensajeStatus = mensajeEntrante.substring(inicio, mensajeRecibido.length());
                        break;
                }
            }
        }

    }

    public static void decodificarConsulta(String mensajeRecibido) {
        int inicio = 0;
        int contador = 0;
        for (int i = 0; i < mensajeEntrante.length(); i++) {
            if (mensajeRecibido.charAt(i) == ',') {
                switch (contador) {
                    case 0:
                        numCuenta = Integer.parseInt(mensajeEntrante.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        break;
                    case 1:
                        cedula = Integer.parseInt(mensajeEntrante.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        break;
                    case 2:
                        nombre = mensajeEntrante.substring(inicio, i);
                        inicio = i + 1;
                        contador++;
                        break;
                    case 3:
                        saldo = Integer.parseInt(mensajeEntrante.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        break;
                    case 4:
                        ciudad = mensajeEntrante.substring(inicio, i);
                        inicio = i + 1;
                        contador++;
                        break;
                    case 5:
                        telefono = mensajeEntrante.substring(inicio, i);
                        inicio = i + 1;
                        contador++;
                        break;
                    case 6:
                        egreso = Integer.parseInt(mensajeEntrante.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        break;
                    case 7:
                        ingreso = Integer.parseInt(mensajeEntrante.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        mensajeStatus = (mensajeEntrante.substring(inicio, mensajeRecibido.length()));
                        break;

                }
            }
        }

    }

    public static String transaccionIda() {

        String mensaje = tipotransaccion + "," + numCuenta + "," + cedula + "," + monto;
        return mensaje;
    }

    public static String transacionVuelta() {

        return "Cuenta: " + numCuenta + "\n Titular: " + nombre + "\n C.C. " + cedula + "\n saldo: " + saldo + "\n Estado de la accion: " + mensajeStatus;
    }

    public static String consultaVuelta() {

        return "Cuenta: " + numCuenta + "\n Titular: " + nombre + "\n C.C. " + cedula + "\n saldo: " + saldo + "\n Egresos totales: " + egreso + "\n Ingresos totales: " + ingreso + "\n Ciudad: " + ciudad + "\n Telefono: " + telefono + "\n Estado de la accion: " + mensajeStatus;
    }

    public static void opciones(int numero) {

        switch (numero) {
            case 0:
                JOptionPane.showMessageDialog(null, "Conexion cancelada", "BANCO XYZ", JOptionPane.CLOSED_OPTION);
                break;
            case 1:
                numCuenta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cuenta"));
                cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite se numero de cedula"));
                monto = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad que va a consignar: "));
                break;
            case 2:
                numCuenta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cuenta"));
                cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite se numero de cedula"));
                monto = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad que va a retirar: "));

                break;
            case 3:
                numCuenta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cuenta"));
                cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite se numero de cedula"));
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida", "BANCO XYZ", JOptionPane.ERROR_MESSAGE);
        }

    }

}
