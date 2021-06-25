create database transaccion;

create table transaccion.Pais
(
    Codpais numeric(5)  not null,
    Nombre  varchar(25) not null,

    constraint PKpais primary key (Codpais)
);

create table transaccion.Ciudad
(
    Codciudad numeric(5)  not null,
    Nombre    varchar(25) not null,
    Pais      numeric(5)  not null,

    constraint PKciudad primary key (Codciudad),
    constraint paisFK foreign key (Pais) references Pais (Codpais)
);

create table transaccion.Cliente
(
    Cedula    numeric(10) not null,
    Nombre1   varchar(15) not null,
    Nombre2   varchar(15),
    Apellido1 varchar(15) not null,
    Apellido2 varchar(15),
    Ciudad    numeric(5)  not null,

    constraint PKCedula primary key (Cedula),
    constraint ciudadFK foreign key (Ciudad) references Ciudad (Codciudad)
);

create table transaccion.Telefono
(
    Ced numeric(10) not null,
    Tel varchar(10) not null,

    constraint Llave primary key (Ced, Tel),
    constraint cedFK foreign key (Ced) references Cliente (Cedula)
);

create table transaccion.Cuenta
(
    Numcuenta     numeric(10) not null,
    Ingreso_money numeric     not null,
    Egreso_money  numeric     not null,
    Saldo_money   numeric     not null,
    Cliente       numeric(10) not null,

    constraint PKcuenta primary key (Numcuenta),
    constraint clienteFK foreign key (Cliente) references Cliente (Cedula)
);

create table transaccion.tipoMovimiento
(
    IDtipo      int auto_increment not null,
    Tipo        varchar(15) not null,
    Descripcion varchar(30) not null,

    constraint PKtipo primary key (IDtipo)
);

create table transaccion.Movimiento
(
    Numcuen     numeric(10) not null,
    Fechamov    date        not null,
    Valor_money numeric     not null,
    Tipo        int         not null,
    constraint PKmovimiento primary key (Numcuen, Fechamov),
    constraint cuentaFK foreign key (Numcuen) references Cuenta (Numcuenta),
    constraint tipoFK foreign key (Tipo) references tipoMovimiento (IDtipo)
);
