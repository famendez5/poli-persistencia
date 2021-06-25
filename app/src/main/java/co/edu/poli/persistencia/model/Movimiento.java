package co.edu.poli.persistencia.model;

import java.time.LocalDateTime;

public class Movimiento {
    public final Integer cuenta;
    public final LocalDateTime fecha;
    public final Double valor;
    public final TipoMovimiento tipo;

    public Movimiento(Integer cuenta, LocalDateTime fecha, Double valor, TipoMovimiento tipo) {
        this.cuenta = cuenta;
        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "cuenta=" + cuenta +
                ", fecha=" + fecha +
                ", valor=" + valor +
                ", tipo=" + tipo +
                '}';
    }
}
