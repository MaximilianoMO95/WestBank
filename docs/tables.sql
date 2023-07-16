CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,

    run                 INT NOT NULL,
    dv                  CHAR(1) NOT NULL,
    nombre              VARCHAR(60) NOT NULL,
    ap_materno          VARCHAR(30) NOT NULL,
    ap_paterno          VARCHAR(30) NOT NULL,
    tel                 VARCHAR(16) NOT NULL,
    comuna              VARCHAR(30) NOT NULL,
    domicilio           VARCHAR(60) NOT NULL,
    clave               VARCHAR(15) NOT NULL,

    /* Cuenta */
    numero_cuenta       INT NOT NULL,
    saldo_cuenta        INT NOT NULL,
    tipo_cuenta         VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS historial_transf (
        id_transaccion INT AUTO_INCREMENT PRIMARY KEY,

        cuenta_origen   INT NOT NULL,
        monto           INT NOT NULL,
        cuenta_destino  INT NOT NULL,
        fecha_hora      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE cliente ADD INDEX idx_numero_cuenta (numero_cuenta);
ALTER TABLE historial_transf
    ADD FOREIGN KEY (cuenta_origen) REFERENCES cliente(numero_cuenta),
    ADD FOREIGN KEY (cuenta_destino) REFERENCES cliente(numero_cuenta);