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

INSERT INTO vehiculos VALUES (1, 'automatico', '200km recorridos', 'grande', 'Opel', '2484 MPW', 'Corsa','5', '432', '13000');
INSERT INTO vehiculos VALUES (2, 'manual', 'ninguna', 'mediano', 'NISSAN', '2341 EXH', 'Qascai','5', '432', '13000');
INSERT INTO vehiculos VALUES (3, 'automatico', 'color a elegir', 'pequeño', 'Renault', '6832 HDS', 'Megane','5', '432', '13000');
INSERT INTO vehiculos VALUES (4, 'manual', 'asientos abatibles', 'pequeño', 'Citroen', '4685 ADT', 'Sara','5', '432', '13000');
INSERT INTO vehiculos VALUES (5, 'manual', 'ventana en el techo', 'grande', 'Lamborgini', '6874 KJU', 'Fasterosa','5', '432', '13000');

