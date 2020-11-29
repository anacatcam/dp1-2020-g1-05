-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
--usuario Juan Jose
INSERT INTO users(username,password,enabled) VALUES ('juaperpla','JacoLeah',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'juaperpla','owner');
-- owner user Álvaro
INSERT INTO users(username,password,enabled) VALUES ('alvechdel','alv1710',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'alvechdel','owner');
-- owner user Antonio
INSERT INTO users(username,password,enabled) VALUES ('antpervaz','antdp1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'antpervaz','owner');
--owner user Manuel
INSERT INTO users(username,password,enabled) VALUES ('manpercar1','pakito123',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'manpercar1','owner');
--owner user Daniel
INSERT INTO users(username,password,enabled) VALUES ('dantorval','dtv123',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'dantorval','owner');
INSERT INTO users(username,password,enabled) VALUES ('daniuser','dtv123',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'daniuser','user');

INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('15442601', 'Juan José', 'Pérez Plata', '608960166', 'juan@gmail.com', 1500);
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('15442609', 'Paco', 'Pérez Rodríguez', '608960162', 'paco@gmail.com', 1500);
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('15442608', 'Pepe', 'Reina López', '608960161', 'pepe@gmail.com', 1500);
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('15442613', 'Antonio', 'Amador Jiménez', '608960852', 'antonio@gmail.com', 1500);
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('15442612', 'Jose Manuel', 'Huertas Barroso', '608960156', 'jose@gmail.com', 1500);

INSERT INTO mecanico(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('15442602', 'Álvaro', 'Echegoyán Delgado', '608960167', 'alvaro@gmail.com', 1500);
INSERT INTO mecanico(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('15442603', 'Antonio', 'Pérez Vázquez', '608960168', 'antonio@gmail.com', 1500);

INSERT INTO cliente(dni,nombre,apellidos,telefono,email,es_conflictivo) VALUES ('15442604', 'Manuel', 'Pérez Carrillo', '608960169', 'manuel@gmail.com', false);
INSERT INTO cliente(dni,nombre,apellidos,telefono,email,es_conflictivo) VALUES ('15442605', 'Daniel', 'Toro Valle', '608960170', 'daniel@gmail.com', false);
INSERT INTO cliente(dni,nombre,apellidos,telefono,email,es_conflictivo) VALUES ('15442606', 'Alejandro', 'Piury Pinzón', '608960171', 'alejandro@gmail.com', false);

INSERT INTO cambio VALUES (1, 'automático');
INSERT INTO cambio VALUES (2, 'manual');

INSERT INTO concesionario(id,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (1, 'concesionario1@gmail.com', '608555102', 'Sevilla', 'Lora del Río', 'Calle Los Pacos', '41063', 'España');
INSERT INTO concesionario(id,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (2, 'concesionario2@gmail.com', '608555174', 'Cádiz', 'Ubrique', 'Calle Alameda del Cura', '11600', 'España');
INSERT INTO concesionario(id,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (3, 'concesionario3@gmail.com', '608555319', 'Huelva', 'Punta Umbría', 'Calle Huelva mismo', '23462', 'España');
INSERT INTO concesionario(id,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (4, 'concesionario4@gmail.com', '608555368', 'Sevilla', 'Marchena', 'Calle Amor de Dios', '41009', 'España');
INSERT INTO concesionario(id,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (5, 'concesionario5@gmail.com', '608555323', 'Cádiz', 'Olvera', 'Calle Avenida España', '83282', 'España');
INSERT INTO concesionario(id,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (6, 'concesionario6@gmail.com', '608556842', 'Sevilla', 'Utrera', 'Calle José Antonio', '16852', 'España');

INSERT INTO concesionarios_gestores VALUES (1, '15442601');
INSERT INTO concesionarios_gestores VALUES (2, '15442609');
INSERT INTO concesionarios_gestores VALUES (3, '15442608');
INSERT INTO concesionarios_gestores VALUES (2, '15442613');
INSERT INTO concesionarios_gestores VALUES (4, '15442609');
INSERT INTO concesionarios_gestores VALUES (5, '15442613');
INSERT INTO concesionarios_gestores VALUES (6, '15442612');

INSERT INTO disponible(id,name) VALUES (1, 'alquiler');
INSERT INTO disponible(id,name) VALUES (2, 'venta');
INSERT INTO disponible(id,name) VALUES (3, 'alquiler o venta');

INSERT INTO combustible(id,name) VALUES (1, 'gasolina');
INSERT INTO combustible(id,name) VALUES (2, 'diesel');
INSERT INTO combustible(id,name) VALUES (3, 'eléctrico');

INSERT INTO compania(id,nombre,telefono,email) VALUES (1, 'Seguros Pakito', '621832854', 'pakitoElMejor@gmail.com');
INSERT INTO compania(id,nombre,telefono,email) VALUES (2, 'Better Call Saúl', '684525318', 'saul@gmail.com');

INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (1, '65215', 60.35, 'Mapfre', 'Seguros a terceros', '2020-09-07', '2021-09-07', 1);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (2, '86452', 100.67, 'Mapfre', 'Terceros ampliado', '2020-09-07', '2021-09-07', 2);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (3, '35418', 200.32, 'Mapfre', 'Seguros a terceros', '2020-09-07', '2021-09-07', 1);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (4, '32151', 200.32, 'Mapfre', 'A todo riesgo', '2020-09-07', '2021-09-07', 2);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (5, '96843', 200.32, 'Mapfre', 'Terceros ampliado', '2020-09-07', '2021-09-07', 2);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (6, '65482', 200.32, 'Mapfre', 'Seguros a terceros', '2020-09-07', '2021-09-07', 1);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (7, '84358', 200.32, 'Mapfre', 'A todo riesgo', '2020-09-07', '2021-09-07', 2);

INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (1, '2484 MPW', 432, 13000, 'Opel', 'Corsa', 4, 5, 2, 100, 10000, 'Seguridad en caso de accidente', 'Bien a pesar de los kilómetros recorridos', 1, 1, 2, 2);
INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (2, '2341 EXH', 432, 13000, 'NISSAN', 'Qascai', 4, 5, 2, 300, 5000, 'Ahorro de combustible', 'Nuevo', 1, 2, 3, 1);
INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (3, '6832 HDS', 432, 13000, 'Renault', 'Megane', 2, 2, 2, 600, 20000, 'Fiabilidad, comodidad', 'Desgastado. Pendiente de reemplazo de piezas', 3, 1, 1, 3);
INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (4, '4685 ADT', 432, 13000, 'Citroen', 'Sara', 2, 5, 1, 90, 3000, 'Bajo coste de mantenimiento', 'Nuevo', 3, 3, 2, 6);
INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (5, '6874 KJU', 432, 13000, 'Lamborghini', 'Gallardo', 2, 4, 1, 100, 1000, 'Espacioso / Amplio', 'Nuevo', 2, 2, 1, 4);
INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (6, '6548 SED', 432, 13000, 'BMW', 'Serie 1', 4, 5, 2, 150, 15000, 'Respetuoso con el medio ambiente', 'Antigüo', 3, 1, 3, 5);
INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (7, '8352 DTR', 432, 13000, 'Mercedes', 'Benz', 4, 6, 1, 500, 6000, 'Espacioso / Amplio', 'Nuevo', 3, 1, 3, 7);

INSERT INTO oferta VALUES (1, 'Oferta 1', 300.50, '2020-12-07', '00:00:00', 3);
INSERT INTO oferta VALUES (2, 'Oferta 2', 600.50, '2021-05-30', '00:00:00', 1);
INSERT INTO oferta VALUES (3, 'Oferta 3', 700.00, '2021-01-24', '00:00:00', 2);
INSERT INTO oferta VALUES (4, 'Oferta 4', 500.00, '2021-03-16', '00:00:00', 4);
INSERT INTO oferta VALUES (5, 'Oferta 5', 300.00, '2021-02-08', '00:00:00', 6);

INSERT INTO incidencia VALUES (1, 'Golpe en la parte frontal', false, 1);
INSERT INTO incidencia VALUES (2, 'Luna trasera rota', false, 3);
INSERT INTO incidencia VALUES (3, 'Espejillo derecho arrancado', true, 5);
INSERT INTO incidencia VALUES (4, 'Rueda derecha anterior pinchada', true, 2);
INSERT INTO incidencia VALUES (5, 'Raíl del asiento delantero roto', true, 4);
INSERT INTO incidencia VALUES (6, 'Rueda izquierda posterior pinchada', true, 2);

INSERT INTO incidencias_mecanicos VALUES (1, '15442602');
INSERT INTO incidencias_mecanicos VALUES (2, '15442602');
INSERT INTO incidencias_mecanicos VALUES (3, '15442603');
INSERT INTO incidencias_mecanicos VALUES (3, '15442602');
INSERT INTO incidencias_mecanicos VALUES (4, '15442603');
INSERT INTO incidencias_mecanicos VALUES (5, '15442603');
INSERT INTO incidencias_mecanicos VALUES (6, '15442603');
INSERT INTO incidencias_mecanicos VALUES (6, '15442602');

INSERT INTO reserva(id,fecha_gastos,fianza,cliente_dni) VALUES (1,'2010-09-07',100.0,'15442604');
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_dni) VALUES (2,'2010-09-08',122.0,'15442605');
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_dni) VALUES (3,'2010-09-09',120.0,'15442606');
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_dni) VALUES (4,'2010-09-09',120.0,'15442604');
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_dni) VALUES (5,'2020-09-07',120.0,'15442606');
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_dni) VALUES (6,'2020-09-09',100.0,'15442604');

INSERT INTO venta(id,cliente_dni,vehiculo_id,reserva_id) VALUES (1,'15442604',7,1);
INSERT INTO venta(id,cliente_dni,vehiculo_id,reserva_id) VALUES (2,'15442605',6,2);
INSERT INTO venta(id,cliente_dni,vehiculo_id,reserva_id) VALUES (3,'15442606',5,3);

INSERT INTO envio(id,provincia,localidad,direccion,codigo_postal,pais,hora,mecanico_dni) VALUES (1,'Sevilla','Sevilla','C/Aznalcazar','41005','España','10:00','15442602');
INSERT INTO envio(id,provincia,localidad,direccion,codigo_postal,pais,hora,mecanico_dni) VALUES (2,'Huelva','Huelva','C/San Pedro','21004','España','11:00','15442602');

INSERT INTO recogida(id,provincia,localidad,direccion,codigo_postal,pais,hora,mecanico_dni) VALUES (1,'Sevilla','Sevilla','C/Aznalcazar','41005','España','11:00','15442602');
INSERT INTO recogida(id,provincia,localidad,direccion,codigo_postal,pais,hora,mecanico_dni) VALUES (2,'Huelva','Huelva','C/San Pedro','21004','España','13:00','15442602');

INSERT INTO alquiler(id,cliente_dni,envio_id,recogida_id,reserva_id,vehiculo_id,fecha_inicio,/*fecha_fin,*/limite_KM) 
	VALUES (1,15442604,1,1,4,4,'2010-09-07',/*'2010-09-28',*/1000);
INSERT INTO alquiler(id,cliente_dni,envio_id,recogida_id,reserva_id,vehiculo_id,fecha_inicio,/*fecha_fin,*/limite_KM) 
	VALUES (2,15442605,2,2,5,5,'2020-09-07',/*'2020-12-16',*/800);
INSERT INTO alquiler(id,cliente_dni,envio_id,recogida_id,reserva_id,vehiculo_id,fecha_inicio,/*fecha_fin,*/limite_KM) 
	VALUES (3,15442605,null,null,6,6,'2020-09-07',/*'2020-11-05',*/1500);
