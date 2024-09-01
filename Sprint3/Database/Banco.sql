CREATE TABLE T_PS_PRODUTO (
    cd_produto NUMBER(10) PRIMARY KEY,
    preco NUMBER(4, 2) NOT NULL,
    descricao CHAR(255),
    nome CHAR(255)
);

SELECT * FROM T_PS_PRODUTO;
DESC T_PS_PRODUTO;
DROP TABLE T_PS_PRODUTO;

CREATE TABLE  T_PS_Minimo_Informacoes(
    cd_minino_informacoes int primary key,
    nome CHAR(255) NOT NULL,
    email CHAR(255) NOT NULL,
    senha CHAR(255) NOT NULL
);

SELECT * FROM T_PS_Minimo_Informacoes;
DESC T_PS_Minimo_Informacoes;
DROP TABLE T_PS_Minimo_Informacoes;

CREATE TABLE  T_PS_OFICINA(
    cnpj CHAR(14) PRIMARY KEY,
    InscricaoEstadual CHAR(12) NOT NULL,
    valorPagar NUMBER(10,2) CHECK(ValorPagar > 0),
    oficina_minino_informacoes int,
    CONSTRAINT oficina_minino_informacoes FOREIGN KEY(oficina_minino_informacoes) REFERENCES T_PS_Minimo_Informacoes(cd_minino_informacoes)
);

SELECT * FROM T_PS_OFICINA;
DESC T_PS_OFICINA;
DROP TABLE T_PS_OFICINA;

CREATE TABLE T_PS_USUARIO_COMUM(
    cpf CHAR(11) PRIMARY KEY,
    QuantidadeOrcamento NUMERIC(10) NOT NULL,
    DataOrcamento DATE,
    comum_minino_informacoes int,
    CONSTRAINT comum_minino_informacoes FOREIGN KEY (comum_minino_informacoes) REFERENCES T_PS_Minimo_Informacoes(cd_minino_informacoes)
);

SELECT * FROM T_PS_USUARIO_COMUM;
DESC T_PS_USUARIO_COMUM;
DROP TABLE T_PS_USUARIO_COMUM;

CREATE TABLE T_PS_USUARIO_PORTO (
    CPF CHAR(11) PRIMARY KEY,
    CodigoSeguro CHAR(15) NOT NULL,
    QuantidadeOrcamento NUMBER(10) NOT NULL,
    DataUltimoOrcamento DATE,
    porto_minino_informacoes int,
    CONSTRAINT porto_minino_informacoes FOREIGN KEY (porto_minino_informacoes) REFERENCES T_PS_Minimo_Informacoes(cd_minino_informacoes)
);

SELECT * FROM T_PS_USUARIO_PORTO;
DESC T_PS_USUARIO_PORTO;
DROP TABLE T_PS_USUARIO_PORTO;

CREATE TABLE T_PS_ENDERECO (
    cd_endereco NUMBER(10) PRIMARY KEY,
    rua CHAR(255),
    cidade CHAR(255) NOT NULL,
    estado CHAR(255) NOT NULL,
    bairro CHAR(255),
    cep CHAR(15) NOT NULL,
    endereco_minino_informacoes int,
    CONSTRAINT endereco_minino_informacoes FOREIGN KEY (endereco_minino_informacoes) REFERENCES T_PS_Minimo_Informacoes(cd_minino_informacoes)
);

SELECT * FROM T_PS_ENDERECO;
DESC T_PS_ENDERECO;
DROP TABLE T_PS_ENDERECO;

CREATE TABLE T_PS_AUTOMOVEL (
    cd_automovel NUMBER(10) PRIMARY KEY,
    placa CHAR(11) NOT NULL,
    marca CHAR(255) NOT NULL,
    modelo CHAR(255) NOT NULL,
    ano DATE NOT NULL,
    diagnostico CHAR(255),
    automovel_minino_informacoes int,
    CONSTRAINT automovel_minino_informacoes FOREIGN KEY (automovel_minino_informacoes) REFERENCES T_PS_Minimo_Informacoes(cd_minino_informacoes)
);

SELECT * FROM T_PS_AUTOMOVEL;
DESC T_PS_AUTOMOVEL;
DROP TABLE T_PS_AUTOMOVEL;

CREATE TABLE T_PS_DIAGNOSTICO (
    cd_diagnostico NUMBER(10) PRIMARY KEY,
    nm_descricao_diagnostico CHAR(255) NOT NULL,
    dt_inicio_diagnostico DATE NOT NULL,
    dt_fim_diagnostico DATE,
    st_diagnostico CHAR(25) NOT NULL,
    diagnostico_minino_informacoes int,
    CONSTRAINT diagnostico_minino_informacoes FOREIGN KEY (diagnostico_minino_informacoes) REFERENCES T_PS_Minimo_Informacoes(cd_minino_informacoes),
    fk_diagnostico_automovel int,
    CONSTRAINT fk_diagnostico_automovel FOREIGN KEY (fk_diagnostico_automovel) REFERENCES T_PS_AUTOMOVEL(cd_automovel)
);

SELECT * FROM T_PS_DIAGNOSTICO;
DESC T_PS_DIAGNOSTICO;
DROP TABLE T_PS_DIAGNOSTICO;

CREATE TABLE T_PS_ORCAMENTO (
    cd_orcamento NUMBER(10) PRIMARY KEY,
    valor_total NUMBER(5, 2),
    status_orcamento CHAR(255) NOT NULL,
    orcamento_minino_informacoes int,
    CONSTRAINT orcamento_minino_informacoes FOREIGN KEY (orcamento_minino_informacoes) REFERENCES T_PS_Minimo_Informacoes(cd_minino_informacoes),
    fk_orcamento_automovel int,
    CONSTRAINT fk_orcamento_automovel FOREIGN KEY (fk_orcamento_automovel) REFERENCES T_PS_AUTOMOVEL(cd_automovel)
);

SELECT * FROM T_PS_ORCAMENTO;
DESC T_PS_ORCAMENTO;
DROP TABLE T_PS_ORCAMENTO;

CREATE TABLE T_PS_PEDIDO (
    cd_pedido NUMBER(10) PRIMARY KEY,
    quantidade NUMBER(3) NOT NULL,
    valor NUMBER(4, 2),
    fk_produto int,
    CONSTRAINT fk_produto FOREIGN KEY (fk_produto) REFERENCES T_PS_PRODUTO(cd_produto)
);

SELECT * FROM T_PS_PEDIDO;
DESC T_PS_PEDIDO;
DROP TABLE T_PS_PEDIDO;
