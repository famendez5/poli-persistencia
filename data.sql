insert into transaccion.Pais values
(11111,'Colombia'),
(22222,'Brasil'),
(33333,'Venezuela'),
(44444,'Mexico'),
(55555,'Bolivia'),
(66666,'Chile'),
(77777,'Argentina');


insert into transaccion.Ciudad values
(12345,'Bogota',11111),
(23451,'Cali',11111),
(34512,'Medellin',11111),
(45123,'Barranquilla',11111),
(51234,'Cartajena',11111),
(21345,'Cucuta',11111),
(52134,'Pereira',11111),
(45213,'Manizales',11111),
(34521,'Ibague',11111);


insert into transaccion.tipoMovimiento values
(1,'Consignacion','Agrega dinero al la cuenta'),
(2,'Retiro','Saca dinero de la cuenta'),
(3,'Consulta','Estado de la cuenta');


-- insertar cliente

insert into transaccion.Cliente values (80258757,'Darwison','','Orjuela','Vallejo',12345);

-- insertar cuenta

insert into transaccion.Cuenta values(1234567890,100000,0,100000,80258757);

-- insertar telefono

insert into transaccion.Telefono values(80258757,'3202256574');
