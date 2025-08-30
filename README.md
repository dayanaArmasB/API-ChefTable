para este proyecto se deja las querys para la creacion de la base de datos y las tablas respectivas

CREATE DATABASE spring;
USE spring;
CREATE TABLE categorias (
    cat_id INT AUTO_INCREMENT PRIMARY KEY,
    cat_nombre VARCHAR(100) NOT NULL,
    cat_descripcion TEXT
}
CREATE TABLE productos (
    pro_id INT AUTO_INCREMENT PRIMARY KEY,
    pro_nombre VARCHAR(255) NOT NULL,
    pro_cantidad INT NOT NULL,
    pro_precio DECIMAL(10,2) NOT NULL,
    cat_id INT,
    FOREIGN KEY (cat_id) REFERENCES categorias(cat_id) ON DELETE SET NULL
);
