package co.edu.poli.persistencia.model;

public class Telefono {
    public final Integer cliente;
    public final String numero;

    public Telefono(Integer cliente, String numero) {
        this.cliente = cliente;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "cliente=" + cliente +
                ", numero='" + numero + '\'' +
                '}';
    }
}
