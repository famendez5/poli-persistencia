package co.edu.poli.persistencia.model;

public class Cliente {
    public final Integer cedula;
    public final String primerNombre;
    public final String segundoNombre;
    public final String primerApellido;
    public final String segundoApellido;
    public final Ciudad ciudad;

    public Cliente(Integer cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Ciudad ciudad) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cedula=" + cedula +
                ", primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", ciudad=" + ciudad +
                '}';
    }
}
