create database EcoMart;
Use EcoMart;

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL
);

CREATE TABLE Producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stockDisponible INT NOT NULL,
    estado VARCHAR(50)
);

CREATE TABLE Carrito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuarioId INT,
    estado VARCHAR(50),
    total DECIMAL(10,2) DEFAULT 0,

    FOREIGN KEY (usuarioId) REFERENCES Usuario(id)
);

CREATE TABLE ItemCarrito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    carritoId INT,
    productoId INT,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL(10,2),
    total DECIMAL(10,2),

    FOREIGN KEY (carritoId) REFERENCES Carrito(id),
    FOREIGN KEY (productoId) REFERENCES Producto(id)
);

#Trigger para update

DELIMITER $$

CREATE TRIGGER actualizar_item_carrito
BEFORE UPDATE ON ItemCarrito
FOR EACH ROW
BEGIN
    SET NEW.total = NEW.cantidad * NEW.precioUnitario;
END$$

DELIMITER ;

#Trigger para recalcular carrito en update

DELIMITER $$

CREATE TRIGGER actualizar_total_carrito_update
AFTER UPDATE ON ItemCarrito
FOR EACH ROW
BEGIN
    UPDATE Carrito
    SET total = (
        SELECT SUM(total)
        FROM ItemCarrito
        WHERE carritoId = NEW.carritoId
    )
    WHERE id = NEW.carritoId;
END$$

DELIMITER ;

#Trigger para Delete

DELIMITER $$

CREATE TRIGGER actualizar_total_carrito_delete
AFTER DELETE ON ItemCarrito
FOR EACH ROW
BEGIN
    UPDATE Carrito
    SET total = (
        SELECT IFNULL(SUM(total), 0)
        FROM ItemCarrito
        WHERE carritoId = OLD.carritoId
    )
    WHERE id = OLD.carritoId;
END$$

DELIMITER ;

#Trigger calcular total item

DELIMITER $$

CREATE TRIGGER calcular_total_item
BEFORE INSERT ON ItemCarrito
FOR EACH ROW
BEGIN
    SET NEW.total = NEW.cantidad * NEW.precioUnitario;
END$$

DELIMITER ;

#Trigger automatico de precio

DELIMITER $$

CREATE TRIGGER asignar_precio_producto
BEFORE INSERT ON ItemCarrito
FOR EACH ROW
BEGIN
    DECLARE precio DECIMAL(10,2);

    SELECT p.precio INTO precio
    FROM Producto p
    WHERE p.id = NEW.productoId;

    SET NEW.precioUnitario = precio;
END$$

DELIMITER ;

#Trigger para controlar stock 

DELIMITER $$

CREATE TRIGGER validar_stock
BEFORE INSERT ON ItemCarrito
FOR EACH ROW
BEGIN
    DECLARE stock INT;

    SELECT stockDisponible INTO stock
    FROM Producto
    WHERE id = NEW.productoId;

    IF stock < NEW.cantidad THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Stock insuficiente';
    END IF;
END$$

DELIMITER ;


#Consultas

SELECT 
    c.id AS carrito,
    p.nombre,
    i.cantidad,
    i.precioUnitario,
    i.total
FROM Carrito c
JOIN ItemCarrito i ON c.id = i.carritoId
JOIN Producto p ON i.productoId = p.id
WHERE c.id = 1;

SELECT total FROM Carrito WHERE id = 1;
