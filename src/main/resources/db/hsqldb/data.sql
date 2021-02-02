-- Admin general
INSERT INTO users(username,password,enabled) VALUES ('admin1','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
--admin Juan Jose
INSERT INTO users(username,password,enabled) VALUES ('juaperpla','JacoLeah',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'juaperpla','admin');
--admin Álvaro
INSERT INTO users(username,password,enabled) VALUES ('alvechdel','echegoyan',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'alvechdel','admin');
--admin Antonio
INSERT INTO users(username,password,enabled) VALUES ('antpervaz','antdp1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'antpervaz','admin');
--admin Manuel
INSERT INTO users(username,password,enabled) VALUES ('manpercar1','pakito123',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'manpercar1','admin');
--admin Daniel
INSERT INTO users(username,password,enabled) VALUES ('dantorval','dtv123',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'dantorval','admin');
--admin Alejandro
INSERT INTO users(username,password,enabled) VALUES ('alepiupin','piuryp',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'alepiupin','admin');

--Gestores
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('78542089X', 'Juan José', 'Domínguez García', '678117290', 'jdomgarcia@gmail.com', 2275.5);
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('56137491B', 'Paco', 'Pérez Rodríguez', '640496862', 'pacoperez@gmail.com', 2275.5);
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('61939644T', 'Pepe', 'Reina López', '661506234', 'peperelop@gmail.com', 2275.5);
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('72679664D', 'Antonio', 'Amador Jiménez', '681572524', 'antodor@gmail.com', 2275.5);
INSERT INTO gestor(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('88682022W', 'Jose Manuel', 'Huertas Barroso', '610519128', 'josehubar@gmail.com', 2275.5);

INSERT INTO mecanico(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('47565973E', 'Álvaro', 'Molinas Trujillo', '625496828', 'alvmoltrujillo@gmail.com', 1730.);
INSERT INTO mecanico(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('25652228Y', 'Antonio', 'Guerra Galera', '697386649', 'antonioguegal@gmail.com', 1730.);
INSERT INTO mecanico(dni,nombre,apellidos,telefono,email,sueldo) VALUES ('66493193D', 'Elias', 'Acuña Roldan', '629279637', 'antonioguegal@gmail.com', 1730.);

--Clientes (con sus cuentas de usuario)
INSERT INTO users(username,password,enabled) VALUES ('manuel','contraseña1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'manuel','cliente');
INSERT INTO cliente(id,dni,first_name,last_name,telefono,email,es_conflictivo,username) VALUES (1,'12422051G', 'Manuel', 'Aviles Campillo', '604223062', 'manuCamp32@gmail.com', 'No', 'manuel');

INSERT INTO users(username,password,enabled) VALUES ('daniel','contraseña2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'daniel','cliente');
INSERT INTO cliente(id,dni,first_name,last_name,telefono,email,es_conflictivo,username) VALUES (2,'31998039W', 'Daniel', 'Barranco Llanos', '660257585', 'danBarll@gmail.com', 'No', 'daniel');

INSERT INTO users(username,password,enabled) VALUES ('alejandro','contraseña3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'alejandro','cliente');
INSERT INTO cliente(id,dni,first_name,last_name,telefono,email,es_conflictivo,username) VALUES (3,'31004225H', 'Alejandro', 'Castellano Sanz', '637666517', 'alejcastz@gmail.com', 'No', 'alejandro');

INSERT INTO cambio VALUES (1, 'Automático');
INSERT INTO cambio VALUES (2, 'Manual');

INSERT INTO estados_envio VALUES (1,'Pendiente');
INSERT INTO estados_envio VALUES (2,'Enviado');
INSERT INTO estados_envio VALUES (3,'Cancelado');
INSERT INTO estados_envio VALUES (4,'Entregado');


INSERT INTO concesionario(id,nombre,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (1, 'Systasa','systasaAuto@gmail.com', '953551391', 'Sevilla', 'Lora del Río', 'Calle Guadaldora', '41440', 'España');
INSERT INTO concesionario(id,nombre,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (2, 'Iberica Formula','iberformula@gmail.com', '956959672', 'Cádiz', 'Ubrique', 'Calle Alameda del Cura', '11600', 'España');
INSERT INTO concesionario(id,nombre,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (3, 'Veyser auto','veyserauto@gmail.com', '959203528', 'Huelva', 'Punta Umbría', 'Calle Amura', '21100', 'España');
INSERT INTO concesionario(id,nombre,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (4, 'Motor Carinsa' ,'carinsaMotor@gmail.com', '952076464', 'Sevilla', 'Marchena', 'Calle de Duarte', '41620', 'España');
INSERT INTO concesionario(id,nombre,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (5, 'Gysa' ,'gysaVehicles@gmail.com', '956571106', 'Cádiz', 'Olvera', 'Calle de Guarino', '11690', 'España');
INSERT INTO concesionario(id,nombre,email,telefono,provincia,localidad,direccion,codigo_postal,pais) VALUES (6, 'Autos Cervican' ,'cervicanMotors@gmail.com', '957565319', 'Sevilla', 'Utrera', 'Calle Albahaca', '41710', 'España');

INSERT INTO concesionarios_gestores VALUES (1, '78542089X');
INSERT INTO concesionarios_gestores VALUES (2, '56137491B');
INSERT INTO concesionarios_gestores VALUES (3, '61939644T');
INSERT INTO concesionarios_gestores VALUES (2, '72679664D');
INSERT INTO concesionarios_gestores VALUES (4, '56137491B');
INSERT INTO concesionarios_gestores VALUES (5, '72679664D');
INSERT INTO concesionarios_gestores VALUES (6, '88682022W');

INSERT INTO disponible(id,name) VALUES (1, 'Alquiler');
INSERT INTO disponible(id,name) VALUES (2, 'Venta');
INSERT INTO disponible(id,name) VALUES (3, 'Alquiler o venta');
INSERT INTO disponible(id,name) VALUES (4, 'Alquilado');
INSERT INTO disponible(id,name) VALUES (5, 'Vendido');
INSERT INTO disponible(id,name) VALUES (6, 'Reservado');
INSERT INTO disponible(id,name) VALUES (7, 'No disponible');


INSERT INTO combustible(id,name) VALUES (1, 'Gasolina');
INSERT INTO combustible(id,name) VALUES (2, 'Diesel');
INSERT INTO combustible(id,name) VALUES (3, 'Eléctrico');

INSERT INTO compania(id,nombre,telefono,email) VALUES (1, 'Mapfre', '955710750', 'mapfreSeguros@gmail.com');
INSERT INTO compania(id,nombre,telefono,email) VALUES (2, 'Better Call Saul', '977710750', 'saulSecures@gmail.com');

INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (1, '7711248065240', 90.35, 100, 'Seguros a terceros con franquicia', '2020-08-07', '2021-09-07', 1);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (2, '9478729634370', 110.67, 0, 'Terceros ampliado', '2020-09-07', '2021-08-07', 2);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (3, '5084164430093', 103.32, 0, 'Seguros a terceros', '2020-06-07', '2021-05-07', 1);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (4, '1610320245021', 388.70, 0, 'A todo riesgo', '2020-05-07', '2021-04-07', 2);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (5, '4233648812433', 175.00, 180, 'Terceros ampliado con franquicia', '2020-10-07', '2021-03-07', 2);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (6, '7953871640149', 388.70, 0, 'Seguros a terceros', '2020-09-07', '2021-11-07', 1);
INSERT INTO seguro_vehiculo(id,numero_poliza,precio,franquicia,cobertura,fecha_inicio,fecha_fin,compania_id) VALUES (7, '6791402232262', 420.00, 300, 'A todo riesgo con franquicia', '2020-08-07', '2021-10-07', 2);

INSERT INTO oferta(id,name,descuento,fecha_limite,hora_limite)VALUES (1, 'Oferta 1', 32.5, '2020-12-07', '00:00:00');
INSERT INTO oferta(id,name,descuento,fecha_limite,hora_limite)VALUES (2, 'Oferta 2', 61.0, '2021-05-30', '00:00:00');
INSERT INTO oferta(id,name,descuento,fecha_limite,hora_limite)VALUES (3, 'Oferta 3', 70.0, '2021-01-24', '00:00:00');
INSERT INTO oferta(id,name,descuento,fecha_limite,hora_limite)VALUES (4, 'Oferta 4', 55.0, '2021-03-16', '00:00:00');
INSERT INTO oferta(id,name,descuento,fecha_limite,hora_limite)VALUES (5, 'Oferta 5', 48.85, '2021-02-08', '00:00:00');


INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (1, '2484 MPW', 332, 11364, 'Opel', 'Corsa', 4, 5, 2, 100, 10000, 'Seguridad en caso de accidente', 'Bien a pesar de los kilómetros recorridos', 1, 1, 2, 1);
INSERT INTO vehiculos(id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (2, '2341 EXH', 483, 21120, 'NISSAN', 'Qascai', 4, 5, 2, 300, 5000, 'Ahorro de combustible', 'Nuevo', 1, 2, 3, 2);
INSERT INTO vehiculos(id,oferta_id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (3,1, '6832 HDS', 432, 14300, 'Renault', 'Megane', 2, 2, 2, 600, 20000, 'Fiabilidad, comodidad', 'Desgastado. Pendiente de reemplazo de piezas', 3, 1, 1, 3);
INSERT INTO vehiculos(id,oferta_id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (4,1, '4685 ADT', 575, 17090, 'Citroen', 'Sara', 2, 5, 1, 90, 3000, 'Bajo coste de mantenimiento', 'Nuevo', 3, 3, 2, 6);
INSERT INTO vehiculos(id,oferta_id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (5,1, '6874 KJU', 1362, 128000, 'Lamborghini', 'Gallardo', 2, 4, 1, 100, 1000, 'Espacioso / Amplio', 'Nuevo', 2, 2, 1, 4);
INSERT INTO vehiculos(id,oferta_id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (6,1, '9553 GCL', 726, 24371, 'BMW', 'Serie 1', 4, 5, 2, 150, 15000, 'Respetuoso con el medio ambiente', 'Antigüo', 3, 1, 3, 5);
INSERT INTO vehiculos(id,oferta_id,matricula,precio_alquiler,precio_venta,marca,modelo,puertas,plazas,cambio_id,maletero,km_actuales,caracteristicas,estado,disponible_id,combustible_id,concesionario_id,seguro_vehiculo_id) VALUES (7,1, '8352 DTR', 798, 27638, 'Mercedes', 'Benz', 4, 6, 1, 500, 6000, 'Espacioso / Amplio', 'Nuevo', 3, 1, 3, 7);

INSERT INTO incidencia(id,descripcion,solucionada,vehiculos_id,cliente_id) VALUES (1, 'Golpe en la parte frontal', false, 1, 1);
INSERT INTO incidencia(id,descripcion,solucionada,vehiculos_id,cliente_id) VALUES (2, 'Luna trasera rota', false, 3, 1);
INSERT INTO incidencia(id,descripcion,solucionada,vehiculos_id,cliente_id) VALUES (3, 'Espejillo derecho arrancado', true, 5, 3);
INSERT INTO incidencia(id,descripcion,solucionada,vehiculos_id,cliente_id) VALUES (4, 'Rueda derecha anterior pinchada', true, 2, 2);
INSERT INTO incidencia(id,descripcion,solucionada,vehiculos_id,cliente_id) VALUES (5, 'Raíl del asiento delantero roto', true, 4, 3);
INSERT INTO incidencia(id,descripcion,solucionada,vehiculos_id,cliente_id) VALUES (6, 'Rueda izquierda posterior pinchada', true, 2, 2);

INSERT INTO incidencias_mecanicos VALUES (1, '47565973E');
INSERT INTO incidencias_mecanicos VALUES (2, '47565973E');
INSERT INTO incidencias_mecanicos VALUES (3, '25652228Y');
INSERT INTO incidencias_mecanicos VALUES (3, '66493193D');
INSERT INTO incidencias_mecanicos VALUES (4, '25652228Y');
INSERT INTO incidencias_mecanicos VALUES (5, '66493193D');
INSERT INTO incidencias_mecanicos VALUES (6, '25652228Y');
INSERT INTO incidencias_mecanicos VALUES (6, '47565973E');

INSERT INTO reserva(id,fecha_gastos,fianza,cliente_id) VALUES (1,'2016-09-03',317.8,1);
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_id) VALUES (2,'2015-05-08',122.0,2);
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_id) VALUES (3,'2012-09-02',226.0,3);
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_id) VALUES (4,'2010-03-23',173.4,1);
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_id) VALUES (5,'2020-06-07',586.0,3);
INSERT INTO reserva(id,fecha_gastos,fianza,cliente_id) VALUES (6,'2020-07-15',428.5,1);


INSERT INTO envio(id,provincia,localidad,direccion,codigo_postal,pais,fecha,hora,estado_id,mecanico_dni) VALUES (1,'Sevilla','Sevilla','C/Aznalcazar','41005','España','2010-09-03','10:00',4,'25652228Y');
INSERT INTO envio(id,provincia,localidad,direccion,codigo_postal,pais,fecha,hora,estado_id,mecanico_dni) VALUES (2,'Huelva','Huelva','C/San Pedro','21004','España','2010-08-07','11:00',2,'47565973E');
INSERT INTO envio(id,provincia,localidad,direccion,codigo_postal,pais,fecha,hora,estado_id,mecanico_dni) VALUES (3,'Córdoba','Aguilar de la Frontera','C/Monturque','43055','España','2020-07-07','16:30',2,'25652228Y');
INSERT INTO envio(id,provincia,localidad,direccion,codigo_postal,pais,fecha,hora,estado_id,mecanico_dni) VALUES (4,'Sevilla','Utrera','C/Sierpes','41710','España','2020-02-07','12:30',3,'47565973E');
INSERT INTO envio(id,provincia,localidad,direccion,codigo_postal,pais,fecha,hora,estado_id,mecanico_dni) VALUES (5,'Sevilla','Los Palacios y Villafranca','C/Martínez Montañés','41720','España','2019-09-17','10:30',1,'47565973E');

INSERT INTO recogida(id,provincia,localidad,direccion,codigo_postal,pais,hora,mecanico_dni) VALUES (1,'Sevilla','Sevilla','C/Aznalcazar','41005','España','12:00','47565973E');
INSERT INTO recogida(id,provincia,localidad,direccion,codigo_postal,pais,hora,mecanico_dni) VALUES (2,'Huelva','Huelva','C/San Pedro','21004','España','13:00','66493193D');

INSERT INTO venta(id,cliente_id,envio_id,vehiculo_id,reserva_id) VALUES (1,1,3,7,1);
INSERT INTO venta(id,cliente_id,envio_id,vehiculo_id,reserva_id) VALUES (2,2,4,6,2);
INSERT INTO venta(id,cliente_id,envio_id,vehiculo_id,reserva_id) VALUES (3,3,5,5,3);

INSERT INTO alquiler(id,cliente_id,envio_id,recogida_id,reserva_id,vehiculo_id,fecha_inicio,fecha_fin,limite_KM,dep_lleno, devuelto) 
	VALUES (1,1,1,1,4,4,'2010-09-03','2010-09-22',20000,true, true);
INSERT INTO alquiler(id,cliente_id,envio_id,recogida_id,reserva_id,vehiculo_id,fecha_inicio,fecha_fin,limite_KM,dep_lleno, devuelto) 
	VALUES (2,2,2,2,5,1,'2010-08-07','2010-09-01',38000,true, false);
INSERT INTO alquiler(id,cliente_id,envio_id,recogida_id,reserva_id,vehiculo_id,fecha_inicio,fecha_fin,limite_KM,dep_lleno, devuelto) 
	VALUES (3,2,null,null,6,3,'2020-09-05','2020-09-10',16500,false, true);
