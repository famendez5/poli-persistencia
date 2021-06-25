package co.edu.poli.persistencia.model;

public class Ciudad {
    public final Integer codigo;
    public final String nombre;
    public final Pais pais;

    public Ciudad(Integer codigo, String nombre, Pais pais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", pais=" + pais +
                '}';
    }
}
