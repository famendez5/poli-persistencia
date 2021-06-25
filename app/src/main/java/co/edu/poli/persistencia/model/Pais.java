package co.edu.poli.persistencia.model;

public class Pais {
    public final Integer codigo;
    public final String nombre;

    public Pais(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
