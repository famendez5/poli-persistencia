package co.edu.poli.persistencia.model;

public class Cuenta {
    public Integer numero;
    public Double ingreso;
    public Double egreso;
    public Double saldo;
    public Cliente cliente;

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
