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

INSERT INTO cambio VALUES (1, 'automático');
INSERT INTO cambio VALUES (2, 'manual');

INSERT INTO maletero VALUES (1, 'pequeño');
INSERT INTO maletero VALUES (2, 'mediano');
INSERT INTO maletero VALUES (3, 'grande');

INSERT INTO concesionario VALUES (1, 'concesionario1@gmail.com', '6085551023');
INSERT INTO concesionario VALUES (2, 'concesionario2@gmail.com', '6085551749');
INSERT INTO concesionario VALUES (3, 'concesionario3@gmail.com', '6085553198');

INSERT INTO vehiculos(id,alquilado,vendido,caracteristicas,disponible,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id,concesionario_id) VALUES (1, false, false, '200km recorridos', true, 'Opel', '2484 MPW', 'Corsa', 5, 432, 13000, 1, 1, 2);
INSERT INTO vehiculos(id,alquilado,vendido,caracteristicas,disponible,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id,concesionario_id) VALUES (2, true, false, 'ninguna', false, 'NISSAN', '2341 EXH', 'Qascai', 4, 432, 13000, 2, 2, 3);
INSERT INTO vehiculos(id,alquilado,vendido,caracteristicas,disponible,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id,concesionario_id) VALUES (3, false, true, 'color a elegir', false, 'Renault', '6832 HDS', 'Megane', 3, 432, 13000, 2, 2, 1);
INSERT INTO vehiculos(id,alquilado,vendido,caracteristicas,disponible,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id,concesionario_id) VALUES (4, false, false, 'asientos abatibles', true, 'Citroen', '4685 ADT', 'Sara', 5, 432, 13000, 1, 3, 2);
INSERT INTO vehiculos(id,alquilado,vendido,caracteristicas,disponible,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id,concesionario_id) VALUES (5, true, false, 'ventana en el techo', false, 'Lamborgini', '6874 KJU', 'Bicharraco', 2, 432, 13000, 1, 1, 3);
INSERT INTO vehiculos(id,alquilado,vendido,caracteristicas,disponible,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id,concesionario_id) VALUES (6, false, false, 'dos ruea como mi nabo', false, 'El trastó', '6548 SED', 'de tuawela', 1, 432, 13000, 1, 1, 1);

INSERT INTO oferta VALUES (1, 'ahorarse los dineros', 300.50, '2020-12-07', '00:00:00', 3);
INSERT INTO oferta VALUES (2, 'encogíos de mierda', 600.50, '2021-05-30', '00:00:00', 1);
INSERT INTO oferta VALUES (3, 'bugas guapos pa los tiesos', 700.00, '2021-01-24', '00:00:00', 2);
INSERT INTO oferta VALUES (4, 'mi abuela en bragas', 500.00, '2021-03-16', '00:00:00', 4);
INSERT INTO oferta VALUES (5, 'primavera en el cortingles', 300.00, '2021-02-08', '00:00:00', 6);

INSERT INTO incidencia VALUES (1, 'rozonaso en la ventana y to bollao por enfrente', false, 1);
INSERT INTO incidencia VALUES (2, 'enverda no le pasa na', false, 3);
INSERT INTO incidencia VALUES (3, 'lan reventao el cristal', true, 5);
INSERT INTO incidencia VALUES (4, 'una ruea pinxa', true, 2);
INSERT INTO incidencia VALUES (5, 'el asiento de atrás esta lleno de pota', true, 4);
INSERT INTO incidencia VALUES (6, 'faltan 20 céntimos en el salpicaero', true, 2);