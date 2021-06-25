package co.edu.poli.persistencia.model;

public class Cliente {
    private final Integer cedula;
    private final String primerNombre;
    private final String segundoNombre;
    private final String primerApellido;
    private final String segundoApellido;
    private final Ciudad ciudad;

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
