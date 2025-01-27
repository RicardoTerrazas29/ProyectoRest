CREATE DATABASE base;

USE base;

CREATE TABLE IF NOT EXISTS persona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- La columna 'id' será la clave primaria y se incrementará automáticamente
    nombre VARCHAR(255),                   -- Columna 'nombre' para almacenar el nombre de la persona
    telefono VARCHAR(255)                  -- Columna 'telefono' para almacenar el número de teléfono de la persona
);


INSERT INTO persona (nombre, telefono)
VALUES
("ricardo","987456123");

SELECT*FROM persona;
