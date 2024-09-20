CREATE TABLE usuarios (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    telefone VARCHAR2(11) NOT NULL,
    senha VARCHAR2(100) NOT NULL
);

SELECT * FROM usuarios;

DROP TABLE usuarios;

CREATE TABLE carros (
    placa VARCHAR2(7) PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    usuario_id NUMBER,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

SELECT * FROM carros;

DROP TABLE carros;
