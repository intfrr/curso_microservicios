CREATE TABLE catalogos_referencias (
  id      VARCHAR(255) PRIMARY KEY,
  nombre VARCHAR(500) NOT NULL,
  descripcion VARCHAR(500) NOT NULL,
  estatus BOOLEAN NOT NULL DEFAULT FALSE
);

insert into catalogos_referencias(id, nombre, descripcion, estatus) VALUES(1,'companiero de trabajo','minimo 1 anio de conocer',true);
insert into catalogos_referencias(id, nombre, descripcion, estatus) VALUES(2,'familiar cercano','familiares cercanos a padres',true);

CREATE TABLE catalogos_productos (
  id      VARCHAR(255) PRIMARY KEY,
  nombre VARCHAR(500) NOT NULL,
  descripcion VARCHAR(500) NOT NULL,
  estatus BOOLEAN NOT NULL DEFAULT FALSE
);

insert into catalogos_productos(id, nombre, descripcion, estatus) VALUES(1,'credito productos','credito para comprar online',true);
insert into catalogos_productos(id, nombre, descripcion, estatus) VALUES(2,'credito efectivo','dinero efectivo en MXN',true);
insert into catalogos_productos(id, nombre, descripcion, estatus) VALUES(3,'credito de auto','deposito directo a cuenta',true);


CREATE TABLE catalogos_estados (
  id      VARCHAR(255) PRIMARY KEY,
  nombre VARCHAR(500) NOT NULL,
  descripcion VARCHAR(500) NOT NULL,
  estatus BOOLEAN NOT NULL DEFAULT FALSE
);

insert into catalogos_estados(id, nombre, descripcion, estatus) VALUES(1,'morelos','mexico morelos',true);
insert into catalogos_estados(id, nombre, descripcion, estatus) VALUES(2,'guerrero','mexico guerrero',true);

CREATE TABLE catalogos_municipios (
  id      VARCHAR(255) PRIMARY KEY,
  nombre VARCHAR(500) NOT NULL,
  descripcion VARCHAR(500) NOT NULL,
  estatus BOOLEAN NOT NULL DEFAULT FALSE
);

insert into catalogos_municipios(id, nombre, descripcion, estatus) VALUES(1,'jiutepec','municipio en morelos',true);
insert into catalogos_municipios(id, nombre, descripcion, estatus) VALUES(2,'huautla','municipio en cuautla',true);


CREATE TABLE catalogos_sucursal (
  id      VARCHAR(255) PRIMARY KEY,
  nombre VARCHAR(500) NOT NULL,
  descripcion VARCHAR(500) NOT NULL,
  telefono_sucursal VARCHAR(255) NOT NULL,
  id_estado_sucursal VARCHAR(255) PRIMARY KEY,
  id_municipio_sucursal Integer,
  estatus BOOLEAN NOT NULL DEFAULT FALSE,
  FOREIGN KEY (id_estado_sucursal) REFERENCES catalogos_estados (id),
  FOREIGN KEY (id_municipio_sucursal) REFERENCES catalogos_municipios (id),
  FOREIGN KEY (id_sucursal) REFERENCES catalogos_sucursal (id)
);

insert into catalogos_sucursal(id, nombre, descripcion, estatus) VALUES(100,'cdmx','ciudad de mexico',true);
insert into catalogos_sucursal(id, nombre, descripcion, estatus) VALUES(101,'toluca','estado de mexico',true);

CREATE TABLE catalogos_canales (
  id      VARCHAR(255) PRIMARY KEY,
  nombre VARCHAR(500) NOT NULL,
  descripcion VARCHAR(500) NOT NULL,
  estatus BOOLEAN NOT NULL DEFAULT FALSE
);

insert into catalogos_canales(id, nombre, descripcion, estatus) VALUES(1001,'cajero','atm',true);
insert into catalogos_canales(id, nombre, descripcion, estatus) VALUES(1002,'sucursal','sucursales',true);
insert into catalogos_canales(id, nombre, descripcion, estatus) VALUES(1003,'web','sitio web',true);
insert into catalogos_canales(id, nombre, descripcion, estatus) VALUES(1004,'movil','aplicacion movil',true);


CREATE TABLE catalogos_paises (
  id      VARCHAR(255) PRIMARY KEY,
  nombre VARCHAR(500) NOT NULL,
  descripcion VARCHAR(500) NOT NULL,
  estatus BOOLEAN NOT NULL DEFAULT FALSE
);

insert into catalogos_paises(id, nombre, descripcion, estatus) VALUES(1,'mexico','ciudad de mexico',true);
insert into catalogos_paises(id, nombre, descripcion, estatus) VALUES(2,'brasil','brasil',true);
insert into catalogos_paises(id, nombre, descripcion, estatus) VALUES(3,'morelos','estado de morelos',true);

CREATE TABLE creditos (
  id_credito VARCHAR(255) NOT NULL,
  id_cliente Integer,
  fecha  VARCHAR(255) NOT NULL,
  id_pais Integer,
  id_canal Integer,
  id_sucursal Integer,
  id_producto Integer,
  monto double precision DEFAULT NULL,
  plazo Integer,
  PRIMARY KEY(id_credito, id_cliente),
  FOREIGN KEY (id_producto) REFERENCES catalogos_productos (id),
  FOREIGN KEY (id_canal) REFERENCES catalogos_canales (id),
  FOREIGN KEY (id_pais) REFERENCES catalogos_paises (id)
);


CREATE TABLE credito_referencias (
  id VARCHAR(255),
  id_credito VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  apellidos VARCHAR(1000) NOT NULL,
  tipo_referencia Integer,
  anios_conocimiento Integer,
  PRIMARY KEY(id, id_credito),
  FOREIGN KEY (id_credito) REFERENCES creditos (id_credito),
  FOREIGN KEY (tipo_referencia) REFERENCES catalogos_referencias (id)
);

CREATE TABLE credito_autorizaciones (
  id VARCHAR(255) ,
  id_credito VARCHAR(255) NOT NULL,
  estatus BOOLEAN NOT NULL DEFAULT FALSE,
  fecha VARCHAR(255) NOT NULL,
  fecha_confirmacion VARCHAR(255) NOT NULL,
  PRIMARY KEY(id, id_credito),
  FOREIGN KEY (id_credito) REFERENCES creditos (id_credito)
);

CREATE TABLE credito_saldo (
  id VARCHAR(255) ,
  id_credito VARCHAR(255) NOT NULL,
  monto double precision DEFAULT NULL,
  monto_liquidacion double precision DEFAULT NULL,
  fecha_confirmacion VARCHAR(255) NOT NULL,
  PRIMARY KEY(id, id_credito),
  FOREIGN KEY (id_credito) REFERENCES creditos (id_credito)
);

CREATE TABLE credito_pagos (
  id VARCHAR(255),
  id_credito VARCHAR(255) NOT NULL,
  id_concepto Integer,
  importe double precision DEFAULT NULL,
  fecha VARCHAR(255) NOT NULL,
  PRIMARY KEY(id, id_credito),
  FOREIGN KEY (id_credito) REFERENCES creditos (id_credito)
);


		