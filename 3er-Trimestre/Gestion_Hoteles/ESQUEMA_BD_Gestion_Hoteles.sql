/* Eliminamos todo */
DROP TABLE F_Hoteles CASCADE CONSTRAINTS;
DROP TABLE F_Habitaciones CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_F_Hoteles;
DROP SEQUENCE SEQ_F_Habitaciones;

/* Creamos secuencias */
CREATE SEQUENCE SEQ_F_Hoteles
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_F_Habitaciones
START WITH 1
INCREMENT BY 1;

/* Creamos tablas */
CREATE TABLE F_Hoteles (
    id_Hotel NUMBER(4),    
    nombre VARCHAR2(100) CONSTRAINT NN_Nombre_F_Hoteles NOT NULL,
    direccion VARCHAR2(255) CONSTRAINT NN_Direccion_F_Hoteles NOT NULL,
    ciudad VARCHAR2(50) CONSTRAINT NN_Ciudad_F_Hoteles NOT NULL,
    categoria VARCHAR2(11) CONSTRAINT NN_Categoria_F_Hoteles NOT NULL,
    fecha_Creacion TIMESTAMP CONSTRAINT NN_Fecha_Creacion_F_Hoteles NOT NULL,
    fecha_modificacion TIMESTAMP CONSTRAINT NN_Fecha_Modificacion_F_Hoteles NOT NULL,
    CONSTRAINT PK_F_Hoteles PRIMARY KEY (id_Hotel),
    CONSTRAINT CK_Categoria_F_Hoteles CHECK(UPPER(categoria) IN ('ESTRELLAS_5', 'ESTRELLAS_4', 'ESTRELLAS_3', 'ESTRELLAS_2', 'ESTRELLAS_1'))
);

CREATE TABLE F_Habitaciones (
    id_Habitacion NUMBER(4),
    id_Hotel NUMBER(4),
    descripcion VARCHAR2(255) CONSTRAINT NN_Descripcion_F_Habitaciones NOT NULL,
    tipo_Habitacion VARCHAR2(50) CONSTRAINT NN_Tipo_Habitacion_F_Habitaciones NOT NULL,
    piso NUMBER(2) CONSTRAINT NN_Piso_F_Habitaciones NOT NULL,
    precio_Noche NUMBER(6,2) CONSTRAINT NN_Precio_Noche_F_Habitaciones NOT NULL,
    capacidad NUMBER(2) CONSTRAINT NN_Capacidad_F_Habitaciones NOT NULL,
    estado_Habitacion VARCHAR2(50) CONSTRAINT NN_Estado_F_Habitaciones NOT NULL,
    CONSTRAINT PK_F_Habitaciones PRIMARY KEY (id_Habitacion),
    CONSTRAINT FK_F_Hoteles_F_Habitaciones FOREIGN KEY (id_Hotel) REFERENCES F_Hoteles ON DELETE CASCADE,
    CONSTRAINT CK_Tipo_Habitacion_F_Habitaciones CHECK(UPPER(tipo_Habitacion) IN ('BASICA', 'PREMIUM', 'SUITE', 'PENTHOUSE')),
    CONSTRAINT CK_Precio_Noche_F_Habitaciones CHECK(precio_Noche > 0),
    CONSTRAINT CK_Capacidad_F_Habitaciones CHECK(capacidad > 0),
    CONSTRAINT CK_Estado_Habitacion_F_Habitaciones CHECK(UPPER(estado_Habitacion) IN ('DISPONIBLE', 'OCUPADA', 'MANTENIMIENTO', 'LIMPIEZA', 'DESHABILITADA'))
);