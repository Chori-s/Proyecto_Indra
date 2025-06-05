-- Creacion de las tablas

CREATE TABLE Usuario (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100),
    contrasena VARCHAR(100)
);

CREATE TABLE Organizador (
    id INT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE Ubicacion (
    id INT PRIMARY KEY,
    ciudad VARCHAR(100),
    direccion VARCHAR(100)
);

CREATE TABLE Categoria (
    id INT PRIMARY KEY,
    nombre_categoria VARCHAR(100)
);

CREATE TABLE Evento (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    fecha DATE,
    id_organizador INT,
    id_ubicacion INT,
    id_categoria INT,
    FOREIGN KEY (id_organizador) REFERENCES Organizador(id),
    FOREIGN KEY (id_ubicacion) REFERENCES Ubicacion(id),
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id)
);

CREATE TABLE Inscripcion (
    id_usuario INT,
    id_evento INT,
    PRIMARY KEY (id_usuario, id_evento),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    FOREIGN KEY (id_evento) REFERENCES Evento(id)
);

-- Creacion de funciones y procedures basicos
DELIMITER //
CREATE PROCEDURE CrearEvento(
    IN p_id INT,
    IN p_nombre VARCHAR(100),
    IN p_fecha DATE,
    IN p_organizador INT,
    IN p_ubicacion INT,
    IN p_categoria INT
)
BEGIN
    INSERT INTO Evento(id, nombre, fecha, id_organizador, id_ubicacion, id_categoria)
    VALUES(p_id, p_nombre, p_fecha, p_organizador, p_ubicacion, p_categoria);
END;
//
DELIMITER ;

DELIMITER //
CREATE FUNCTION ContarInscritos(p_evento INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM Inscripcion WHERE id_evento = p_evento;
    RETURN total;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER evitar_doble_inscripcion
BEFORE INSERT ON Inscripcion
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1 FROM Inscripcion 
        WHERE id_usuario = NEW.id_usuario AND id_evento = NEW.id_evento
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario ya está inscrito en este evento.';
    END IF;
END;
//
DELIMITER ;

INSERT INTO Usuario VALUES (1, 'Iván', 'ivan@mail.com', '1234');
INSERT INTO Organizador VALUES (1, 'EcoOrg');
INSERT INTO Ubicacion VALUES (1, 'Madrid', 'Calle Verde 12');
INSERT INTO Categoria VALUES (1, 'Sostenibilidad');

CALL CrearEvento(1, 'Taller de reciclaje', '2025-06-15', 1, 1, 1);

INSERT INTO Inscripcion VALUES (1, 1);

