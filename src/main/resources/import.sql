/*populate tabla clierntes*/
INSERT INTO regiones (nombre) VALUES('Sudamérica');
INSERT INTO regiones (nombre) VALUES('Centroamérica');
INSERT INTO regiones (nombre) VALUES('Norteamérica');
INSERT INTO regiones (nombre) VALUES('Europa');
INSERT INTO regiones (nombre) VALUES('Asia');
INSERT INTO regiones (nombre) VALUES('Africa');
INSERT INTO regiones (nombre) VALUES('Oceanía');
INSERT INTO regiones (nombre) VALUES('Antártida');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(1,'Patricio','Contreras','patorma@yahoo.com', '2019-12-30');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(2,'Pablo', 'Torres',  'pablo@gmail.com', '2019-11-24');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(4,'Saul','Palma', 'saul_palma@gmail.com','2019-09-15');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(4,'Daniela', 'Contreras', 'daniela_pu@gmail.com','2019-03-28')
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(4,'Alex', 'Fernandez','Alex@gmail.com','2019-05-06');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(3,'Iris','Marlene','irisita_miel@gmail.com', '1990-12-30')
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(3,'Daniela', 'Torres',  'dani@gmail.com', '1981-11-24')
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(3,'Ivan','Palma', 'ivan_elterrible@gmail.com','1995-09-15')
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(3,'Juan', 'Ortiz', 'palito_agua@gmail.com','1969-03-28');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(5,'Fernando', 'Fernandez','feña@gmail.com','2019-05-06');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(6,'Patricio','Contreras','mevoya@hotmail.com', '2019-12-30');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(7,'Maria', 'Cifuentes',  'merry@gmail.com', '2020-11-24');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(1,'Pedro','Palma', 'terminator@gmail.com','2019-09-15');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(8,'Eduardo', 'Yañez', 'edu_20245@gmail.com','2019-03-28');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(8,'Alexia', 'Perez','Alexia_bellaka@gmail.com','2019-05-06');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(7,'Tamara','Torres','tamara_torres@yahoo.com', '2019-12-30');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(8,'Rodrigo', 'Ortiz', 'rodri@gmail.com', '2019-11-24');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(1,'Samuel','Carrasco', 'samuel@gmail.com','2019-09-15');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(1,'Sebastian', 'Contreras', 'seba@gmail.com','2019-03-28');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(2,'Leslie', 'Fernandez','Leslie_bella@gmail.com','2019-05-06');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(2,'Patricio','Suarez','hombre_audaz@yahoo.com', '2019-12-30');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(5,'Lorena', 'Suarez',  'lore@gmail.com', '2019-11-24');
INSERT INTO clientes(region_id,nombre,apellido,email,create_at) VALUES(6,'Felicia','Palma', 'feliz@gmail.com','2019-09-15');

/*Creamos algunos usuarios con roles*/
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('andres','$2a$10$ApgjGPuyD93ebTJ9gVDlV.bLG96fV4mwrNESQVCiusts935bezhHG', 1,'Andres','Guzman','profesor@bolsasdeideas.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('admin','$2a$10$y//tMuLCW4tuTSc8tR4IheHAAbLqjqy8coxw0FcFN1LahsiBstcb6', 1, 'Jhon','Doe','jhon.doe@bolsasdeideas.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES (2,1);
