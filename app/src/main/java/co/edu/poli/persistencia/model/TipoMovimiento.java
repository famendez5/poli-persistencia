package co.edu.poli.persistencia.model;

public class TipoMovimiento {
    public final Integer id;
    public final String tipo;
    public final String descripcion;

    public TipoMovimiento(Integer id, String tipo, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public static final TipoMovimiento CONSIGNACION = new TipoMovimiento(1, "", "");
    public static final TipoMovimiento RETIRO = new TipoMovimiento(2, "", "");

    @Override
    public String toString() {
        return "TipoMovimiento{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
