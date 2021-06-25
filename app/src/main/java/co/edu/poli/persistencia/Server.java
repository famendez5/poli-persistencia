package co.edu.poli.persistencia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    public static void main(String[] args) throws IOException {
        iniciarServidor();

    }

    static int puerto = 9000;
    static ServerSocket servidor;
    static Socket cliente;
    static String mensajeEntrante;
    static String mensajeSaliente;
    static DataInputStream entrada;
    static DataOutputStream salida;

    static int numCuenta;
    static int cedula;
    static String nombre;
    static int saldo;

    static String telefono;
    static String ciudad;
    static int ingreso;
    static int egreso;

    static int tipotransaccion;
    static String mensajeStatus;
    static int monto;

    public static void iniciarServidor() throws IOException {

        try {

            servidor = new ServerSocket(puerto);
            cliente = new Socket();
            System.out.println("Esperando conexion...");
            cliente = servidor.accept();
            System.out.println("Conexion establecida con un cliente...");
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            while (true) {

                mensajeEntrante = entrada.readUTF();
                decodificarMensaje(mensajeEntrante);

                if (tipotransaccion == 1 || tipotransaccion == 2) {
                    ConexionDB.getConexion();
                    tipoTransaccion();
                    mensajeSaliente = mensajeSalidaTransaccion();
                    salida.writeUTF(mensajeSaliente);
                } else if (tipotransaccion == 3) {
                    ConexionDB.getConexion();
                    tipoTransaccion();
                    mensajeSaliente = mensajeSalidaConsulta();
                    salida.writeUTF(mensajeSaliente);
                }

                if (tipotransaccion == 0) {
                    break;
                }
            }

        } catch (Exception sock) {
            sock.printStackTrace();
        }

        salida.close();
        entrada.close();
        cliente.close();

    }

    public static void decodificarMensaje(String mensajeRecibido) {
        int inicio = 0;
        int contador = 0;
        for (int i = 0; i < mensajeRecibido.length(); i++) {
            if (mensajeRecibido.charAt(i) == ',') {
                switch (contador) {
                    case 0:
                        tipotransaccion = Integer.parseInt(mensajeRecibido.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        break;
                    case 1:
                        numCuenta = Integer.parseInt(mensajeRecibido.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        break;
                    case 2:
                        cedula = Integer.parseInt(mensajeRecibido.substring(inicio, i));
                        inicio = i + 1;
                        contador++;
                        monto = Integer.parseInt(mensajeRecibido.substring(inicio, mensajeRecibido.length()));
                        break;
                }
            }
        }

    }

    public static void tipoTransaccion() throws SQLException {

        switch (tipotransaccion) {
            case 0:
                break;
            case 1:
                consignacion();
                break;
            case 2:
                retiro();
                break;
            case 3:
                consulta();
                break;
        }
    }

    public static void consignacion() throws SQLException {
        ConexionDB.consultarCliente(cedula);
        nombre = ConexionDB.getNombre();
        ConexionDB.crearMovimiento(numCuenta, monto, tipotransaccion);
        ConexionDB.consultarCuenta(numCuenta);
        ingreso = ConexionDB.getIngreso();
        saldo = ConexionDB.getSaldo();
        egreso = ConexionDB.getEgreso();
        saldo += monto;
        ingreso += monto;
        egreso += 0;
        ConexionDB.actualizarCuenta(numCuenta, ingreso, egreso, saldo);
        mensajeStatus = "Consignacion Realizada";
    }

    public static void retiro() throws SQLException {
        ConexionDB.consultarCuenta(numCuenta);
        saldo = ConexionDB.getSaldo();
        ConexionDB.consultarCliente(cedula);
        nombre = ConexionDB.getNombre();
        if (saldo >= monto) {
            ConexionDB.crearMovimiento(numCuenta, monto, tipotransaccion);
            ingreso = ConexionDB.getIngreso();
            egreso = ConexionDB.getEgreso();
            saldo -= monto;
            ingreso += 0;
            egreso += monto;
            ConexionDB.actualizarCuenta(numCuenta, ingreso, egreso, saldo);
            mensajeStatus = "Retiro Realizado";
        } else {
            mensajeStatus = "Fondos insuficientes";
        }

    }

    public static void consulta() throws SQLException {
        ConexionDB.consultarCliente(cedula);
        ConexionDB.consultarCuenta(numCuenta);
        ConexionDB.consultarTel(cedula);
        ConexionDB.crearMovimiento(numCuenta, 0, tipotransaccion);
        nombre = ConexionDB.getNombre();
        ciudad = ConexionDB.getCiudad();
        telefono = ConexionDB.getTelefono();
        ingreso = ConexionDB.getIngreso();
        saldo = ConexionDB.getSaldo();
        egreso = ConexionDB.getEgreso();

        mensajeStatus = "Consulta realizada";
    }

    public static String mensajeSalidaTransaccion() {

        String mensaje = numCuenta + "," + cedula + "," + nombre + "," + saldo + "," + mensajeStatus + "\n ¡Gracias por escojer el Banco XYZ!" + ",";
        return mensaje;
    }

    public static String mensajeSalidaConsulta() {
        String mensaje = numCuenta + "," + cedula + "," + nombre + "," + saldo + "," + ciudad + "," + telefono + "," + egreso + "," + ingreso + "," + mensajeStatus + "\n ¡Gracias por escojer el Banco XYZ!";
        return mensaje;
    }

}
