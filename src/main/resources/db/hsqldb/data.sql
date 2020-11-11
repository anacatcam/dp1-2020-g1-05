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

INSERT INTO vehiculos(id,caracteristicas,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id) VALUES (1, '200km recorridos', 'Opel', '2484 MPW', 'Corsa', 5, 432, 13000, 1, 1);
INSERT INTO vehiculos(id,caracteristicas,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id) VALUES (2, 'ninguna', 'NISSAN', '2341 EXH', 'Qascai', 5, 432, 13000, 2, 2);
INSERT INTO vehiculos(id,caracteristicas,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id) VALUES (3, 'color a elegir', 'Renault', '6832 HDS', 'Megane', 5, 432, 13000, 2, 2);
INSERT INTO vehiculos(id,caracteristicas,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id) VALUES (4, 'asientos abatibles', 'Citroen', '4685 ADT', 'Sara', 5, 432, 13000, 1, 3);
INSERT INTO vehiculos(id,caracteristicas,marca,matricula,modelo,plazas,precio_alquiler,precio_venta,cambio_id,maletero_id) VALUES (5, 'ventana en el techo', 'Lamborgini', '6874 KJU', 'Fasterosa', 5, 432, 13000, 1, 1);
