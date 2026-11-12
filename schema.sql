
-- =====================================================
-- BASE DE DATOS: gestion_vehiculos
-- MOTOR: MySQL 8+
-- =====================================================

CREATE DATABASE IF NOT EXISTS defaultdb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE gestion_vehiculos;

-- =====================================================
-- TABLA: USERS
-- =====================================================

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE INDEX idx_users_name ON users(name);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_status ON users(status);

-- =====================================================
-- TABLA: VEHICULOS
-- =====================================================

CREATE TABLE IF NOT EXISTS vehiculos (
    placa VARCHAR(7) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    version VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    num_puestos INT NOT NULL,
    num_puertas INT NOT NULL,
    combustible VARCHAR(30) NOT NULL,
    kilometros INT NOT NULL,
    cilindraje INT NOT NULL,
    categoria VARCHAR(50) NOT NULL,

    CONSTRAINT pk_vehiculos PRIMARY KEY (placa),
    CONSTRAINT chk_vehiculos_puestos CHECK (num_puestos > 0),
    CONSTRAINT chk_vehiculos_puertas CHECK (num_puertas >= 0),
    CONSTRAINT chk_vehiculos_kilometros CHECK (kilometros >= 0),
    CONSTRAINT chk_vehiculos_cilindraje CHECK (cilindraje > 0)
);

CREATE INDEX idx_vehiculos_marca ON vehiculos(marca);
CREATE INDEX idx_vehiculos_modelo ON vehiculos(modelo);
CREATE INDEX idx_vehiculos_categoria ON vehiculos(categoria);

-- =====================================================
-- USUARIO ADMINISTRADOR
-- La contraseña se insertará mediante Python + BCrypt.
-- =====================================================

-- =====================================================
-- VERIFICACIONES
-- =====================================================

SHOW TABLES;
DESCRIBE users;
DESCRIBE vehiculos;
