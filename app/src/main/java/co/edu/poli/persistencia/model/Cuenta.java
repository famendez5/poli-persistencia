package co.edu.poli.persistencia.model;

public class Cuenta {
    public final Integer numero;
    public final Double ingreso;
    public final Double egreso;
    public final Double saldo;
    public final Cliente cliente;

    public Cuenta(Integer numero, Double ingreso, Double egreso, Double saldo, Cliente cliente) {
        this.numero = numero;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numero=" + numero +
                ", ingreso=" + ingreso +
                ", egreso=" + egreso +
                ", saldo=" + saldo +
                ", cliente=" + cliente +
                '}';
    }
}
