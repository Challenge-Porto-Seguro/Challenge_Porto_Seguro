-- THIAGO THOMAZ SALES CONCEIÇÃO. RM: 557992
-- PEDRO HENRIQUE DOS SANTOS. RM:559064

DROP TABLE t_ps_automovel CASCADE CONSTRAINTS;

DROP TABLE t_ps_diagnostico CASCADE CONSTRAINTS;

DROP TABLE t_ps_endereco CASCADE CONSTRAINTS;

DROP TABLE t_ps_itens_orcamento CASCADE CONSTRAINTS;

DROP TABLE t_ps_oficina CASCADE CONSTRAINTS;

DROP TABLE t_ps_orcamento CASCADE CONSTRAINTS;

DROP TABLE t_ps_pessoa CASCADE CONSTRAINTS;

DROP TABLE t_ps_produto CASCADE CONSTRAINTS;

DROP TABLE t_ps_usuario CASCADE CONSTRAINTS;

-- DROP CASCADE

DROP TABLE t_ps_automovel CASCADE CONSTRAINTS;

DROP TABLE t_ps_diagnostico CASCADE CONSTRAINTS;

DROP TABLE t_ps_endereco CASCADE CONSTRAINTS;

DROP TABLE t_ps_itens_orcamento CASCADE CONSTRAINTS;

DROP TABLE t_ps_oficina CASCADE CONSTRAINTS;

DROP TABLE t_ps_orcamento CASCADE CONSTRAINTS;

DROP TABLE t_ps_pessoa CASCADE CONSTRAINTS;

DROP TABLE t_ps_produto CASCADE CONSTRAINTS;

DROP TABLE t_ps_usuario CASCADE CONSTRAINTS;

DROP SEQUENCE sq_t_ps_diagnostico;

DROP SEQUENCE sq_t_ps_cd_pessoa;

DROP SEQUENCE sq_t_ps_oficina;

DROP SEQUENCE sq_t_ps_orcamento;

DROP SEQUENCE sq_t_ps_produto;

DROP SEQUENCE sq_t_ps_usuario;

DROP SEQUENCE sq_t_ps_automovel;

DROP SEQUENCE sq_t_ps_cd_endereco;



CREATE TABLE t_ps_automovel (
    cd_automovel      NUMBER(10) NOT NULL,
    sq_placa          VARCHAR2(11) NOT NULL,
    nm_marca_veiculo  VARCHAR2(255) NOT NULL,
    nm_modelo_veiculo VARCHAR2(255) NOT NULL,
    dt_veiculo        DATE NOT NULL,
    cd_pessoa         NUMBER(10) NOT NULL
);

COMMENT ON COLUMN t_ps_automovel.cd_automovel IS
    'Esse atributo irá receber a chave primária do automovel. Esse número é sequencial e gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_automovel.sq_placa IS
    'esse atributo ira receber o numero da placa do automovel. Seu conteudo é obrigatorio e unico';

COMMENT ON COLUMN t_ps_automovel.nm_marca_veiculo IS
    'esse atribtuo ira receber a marca do automovel. Seu conteudo é obrigatorio';

COMMENT ON COLUMN t_ps_automovel.nm_modelo_veiculo IS
    'esse atribtuo ira receber o modelo do automovel. Seu conteudo é obrigatorio';

COMMENT ON COLUMN t_ps_automovel.dt_veiculo IS
    'esse atribtuo ira receber a data do automovel. Seu conteudo é obrigatorio';

COMMENT ON COLUMN t_ps_automovel.cd_pessoa IS
    'Esse atributo irá receber a chave primária da pessoa. Esse número é sequencial iniciando com 1 a partir do id da pessoa  e é  gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

ALTER TABLE t_ps_automovel ADD CONSTRAINT automovel_pk PRIMARY KEY ( cd_automovel );

ALTER TABLE t_ps_automovel ADD CONSTRAINT uk_ps_sq_placa UNIQUE ( sq_placa );

CREATE TABLE t_ps_diagnostico (
    cd_diagnostico           NUMBER(10) NOT NULL,
    cd_automovel             NUMBER(10) NOT NULL,
    nm_descricao_diagnostico VARCHAR2(255) NOT NULL,
    dt_inicio_diagnostico    DATE NOT NULL,
    dt_fim_diagnostico       DATE,
    st_diagnostico           VARCHAR2(1) NOT NULL,
    cd_oficina               NUMBER(10) NOT NULL
);

ALTER TABLE t_ps_diagnostico
    ADD CONSTRAINT ck_t_ps_status_diagnostico CHECK ( st_diagnostico IN ( 'A', 'I' ) );

COMMENT ON COLUMN t_ps_diagnostico.cd_diagnostico IS
    'Esse atributo irá receber a chave primária do diagnostico. Esse número é sequencial e gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_diagnostico.cd_automovel IS
    'Esse atributo irá receber a chave primária do automovel. Esse número é sequencial iniciando com 1 a partir do id do automovel  e é  gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_diagnostico.nm_descricao_diagnostico IS
    'Esse atributo irá receber a descrição do diagnostico. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_diagnostico.dt_inicio_diagnostico IS
    'Esse atributo irá receber a data de inicio do diagnostico. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_diagnostico.dt_fim_diagnostico IS
    'Esse atributo irá receber a data de fim do diagnostico';

COMMENT ON COLUMN t_ps_diagnostico.st_diagnostico IS
    'Esse atributo irá recebero status  do diagnostico. Podendo receber a de ativo ou i de inativo. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_diagnostico.cd_oficina IS
    'Esse atributo irá receber a chave primária da pessoa. Esse número é sequencial iniciando com 1 a partir do id da pessoa  e é  gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

ALTER TABLE t_ps_diagnostico ADD CONSTRAINT t_ps_diagnostico_pk PRIMARY KEY ( cd_diagnostico );

CREATE TABLE t_ps_endereco (
    cd_endereco NUMBER(10) NOT NULL,
    cd_pessoa   NUMBER(10) NOT NULL,
    nm_rua      VARCHAR2(255) NOT NULL,
    nm_cidade   VARCHAR2(255) NOT NULL,
    nm_estado   VARCHAR2(255) NOT NULL,
    nm_bairro   VARCHAR2(255) NOT NULL,
    sq_cep      VARCHAR2(15) NOT NULL,
    nr_casa     NUMBER(7) NOT NULL
);

COMMENT ON COLUMN t_ps_endereco.cd_endereco IS
    'Esse atributo irá receber a chave primária do endereco. Esse número é sequencial e gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_endereco.cd_pessoa IS
    'Esse atributo irá receber a chave primária da pessoa. Esse número é sequencial iniciando com 1 a partir do id da pessoa  e é  gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_endereco.nm_rua IS
    'esse atributo ira receber o nome da rua do endereco. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_endereco.nm_cidade IS
    'esse atributo ira receber o nome da cidade do endereco. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_endereco.nm_estado IS
    'esse atributo ira receber o nome do estado do endereco. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_endereco.nm_bairro IS
    'esse atributo ira receber o nome do bairro do endereco. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_endereco.sq_cep IS
    'esse atributo ira receber o cep do endereco. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_endereco.nr_casa IS
    'esse atriibuto ira receber o numero do endereco. Seu conteúdo é obrigatório.';

ALTER TABLE t_ps_endereco ADD CONSTRAINT endereco_pk PRIMARY KEY ( cd_endereco );

CREATE TABLE t_ps_itens_orcamento (
    cd_orcamento NUMBER(10) NOT NULL,
    cd_produto   NUMBER(10) NOT NULL,
    qt_pedido    NUMBER(3) NOT NULL,
    vl_pedido    NUMBER(4, 2)
);

COMMENT ON COLUMN t_ps_itens_orcamento.cd_orcamento IS
    'Esse atributo irá receber a chave primária e uma foreign key  do itens_orcamento. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_itens_orcamento.cd_produto IS
    'Esse atributo irá receber a chave primária e uma foreign key  do itens_orcamento. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_itens_orcamento.qt_pedido IS
    'Esse atributo irá receber a quantidade do produto. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_itens_orcamento.vl_pedido IS
    'Esse atributo irá receber o valor do pedido. Seu conteúdo é obrigatório.';

ALTER TABLE t_ps_itens_orcamento ADD CONSTRAINT pedido_pk PRIMARY KEY ( cd_orcamento,
                                                                        cd_produto );

CREATE TABLE t_ps_oficina (
    cd_oficina            NUMBER(10) NOT NULL,
    sq_cnpj               VARCHAR2(14) NOT NULL,
    sq_inscricao_estadual VARCHAR2(12) NOT NULL
);

COMMENT ON COLUMN t_ps_oficina.cd_oficina IS
    'Esse atributo irá receber a chave primária da oficina. Esse número é sequencial iniciando com 1 a partir do id da pessoa  e é  gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_oficina.sq_cnpj IS
    'esse atributo ira receber o cnpj da oficina. Seu conteúdo é obrigatorio e unico';

COMMENT ON COLUMN t_ps_oficina.sq_inscricao_estadual IS
    'esse atributo ira receber a inscrição estadual da oficina. Seu conteúdo é obrigatorio e unico';

ALTER TABLE t_ps_oficina ADD CONSTRAINT t_ps_oficina_pk PRIMARY KEY ( cd_oficina );

ALTER TABLE t_ps_oficina ADD CONSTRAINT uk_ps_sq_cnpj UNIQUE ( sq_cnpj );

ALTER TABLE t_ps_oficina ADD CONSTRAINT uk_ps_inscricao_estadual UNIQUE ( sq_inscricao_estadual );

CREATE TABLE t_ps_orcamento (
    cd_orcamento   NUMBER(10) NOT NULL,
    st_orcamento   VARCHAR2(2) NOT NULL,
    cd_diagnostico NUMBER(10) NOT NULL
);

ALTER TABLE t_ps_orcamento
    ADD CONSTRAINT ck_t_ps_status_orcamento CHECK ( st_orcamento IN ( 'A', 'I' ) );

COMMENT ON COLUMN t_ps_orcamento.cd_orcamento IS
    'Esse atributo irá receber a chave primária do orcamento. Esse número é sequencial e gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_orcamento.st_orcamento IS
    'Esse atributo irá recebero status  do orcamento. Podendo receber a de ativo ou i de inativo. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_orcamento.cd_diagnostico IS
    'Esse atributo irá receber a chave primária do diagnosticol. Esse número é sequencial iniciando com 1 a partir do cd do diagnostico  e é  gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

CREATE UNIQUE INDEX t_ps_orcamento__idx ON
    t_ps_orcamento (
        cd_diagnostico
    ASC );

ALTER TABLE t_ps_orcamento ADD CONSTRAINT orcamento_pk PRIMARY KEY ( cd_orcamento );

CREATE TABLE t_ps_pessoa (
    cd_pessoa NUMBER(10) NOT NULL,
    nm_nome   VARCHAR2(255) NOT NULL,
    nm_email  VARCHAR2(255) NOT NULL,
    sq_senha  VARCHAR2(255) NOT NULL
);

COMMENT ON COLUMN t_ps_pessoa.cd_pessoa IS
    'Esse atributo irá receber a chave primária da pessoa. Esse número é sequencial e gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_pessoa.nm_nome IS
    'esse atributo ira receber o nome da pessoa. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_pessoa.nm_email IS
    'esse atributo ira receber o email da pessoa. Seu conteúdo é obrigatório e unico.';

COMMENT ON COLUMN t_ps_pessoa.sq_senha IS
    'esse atributo ira receber a senha do email da pessoa. Seu conteúdo é obrigatório.';

ALTER TABLE t_ps_pessoa ADD CONSTRAINT t_ps_cd_pessoa PRIMARY KEY ( cd_pessoa );

ALTER TABLE t_ps_pessoa ADD CONSTRAINT uk_ps_nm_email UNIQUE ( nm_email );

CREATE TABLE t_ps_produto (
    cd_produto NUMBER(10) NOT NULL,
    vl_produto NUMBER(7, 2) NOT NULL,
    ds_produto VARCHAR2(255),
    nm_produto VARCHAR2(255) NOT NULL
);

COMMENT ON COLUMN t_ps_produto.cd_produto IS
    'Esse atributo irá receber a chave primária do produto. Esse número é sequencial e gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_produto.vl_produto IS
    'Esse atributo irá receber  o valor do produto. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_produto.ds_produto IS
    'Esse atributo irá receber a descrição do produto. Seu conteúdo é obrigatório.';

COMMENT ON COLUMN t_ps_produto.nm_produto IS
    'Esse atributo irá recebero nome  do produto. Seu conteúdo é obrigatório.';

ALTER TABLE t_ps_produto ADD CONSTRAINT produto_pk PRIMARY KEY ( cd_produto );

CREATE TABLE t_ps_usuario (
    cd_pessoa NUMBER(10) NOT NULL,
    sq_cpf    VARCHAR2(11) NOT NULL
);

COMMENT ON COLUMN t_ps_usuario.cd_pessoa IS
    'Esse atributo irá receber a chave primária do usuario. Esse número é sequencial iniciando com 1 a partir do id da pessoa  e é  gerado automaticamente pelo sistema. Seu conteúdo é obrigatório.'
    ;

COMMENT ON COLUMN t_ps_usuario.sq_cpf IS
    'esse atributo ira receber o numero do cpf da pessoa. Seu conteúdo e obrigatorio e unico';

ALTER TABLE t_ps_usuario ADD CONSTRAINT t_ps_usuario_pk PRIMARY KEY ( cd_pessoa );

ALTER TABLE t_ps_usuario ADD CONSTRAINT uk_ps_sq_cpf UNIQUE ( sq_cpf );

ALTER TABLE t_ps_diagnostico
    ADD CONSTRAINT fk_automovel_diagnostico FOREIGN KEY ( cd_automovel )
        REFERENCES t_ps_automovel ( cd_automovel );

ALTER TABLE t_ps_orcamento
    ADD CONSTRAINT fk_diagnostico_orcamentov1 FOREIGN KEY ( cd_diagnostico )
        REFERENCES t_ps_diagnostico ( cd_diagnostico );

ALTER TABLE t_ps_endereco
    ADD CONSTRAINT fk_minimo_informacoes_endereco FOREIGN KEY ( cd_pessoa )
        REFERENCES t_ps_pessoa ( cd_pessoa );

ALTER TABLE t_ps_diagnostico
    ADD CONSTRAINT fk_oficina_diagnostico FOREIGN KEY ( cd_oficina )
        REFERENCES t_ps_oficina ( cd_oficina );

ALTER TABLE t_ps_itens_orcamento
    ADD CONSTRAINT fk_orcamento_pedido FOREIGN KEY ( cd_orcamento )
        REFERENCES t_ps_orcamento ( cd_orcamento );

ALTER TABLE t_ps_oficina
    ADD CONSTRAINT fk_pessoa_oficina FOREIGN KEY ( cd_oficina )
        REFERENCES t_ps_pessoa ( cd_pessoa );

ALTER TABLE t_ps_usuario
    ADD CONSTRAINT fk_pessoa_usuario FOREIGN KEY ( cd_pessoa )
        REFERENCES t_ps_pessoa ( cd_pessoa );

ALTER TABLE t_ps_itens_orcamento
    ADD CONSTRAINT fk_produto_pedido FOREIGN KEY ( cd_produto )
        REFERENCES t_ps_produto ( cd_produto );

ALTER TABLE t_ps_automovel
    ADD CONSTRAINT fk_usuario_automovel FOREIGN KEY ( cd_pessoa )
        REFERENCES t_ps_usuario ( cd_pessoa );

--  ERROR: No Discriminator Column found in Arc INFORMACOES_PRIMARIAS - constraint trigger for Arc cannot be generated 

--  ERROR: No Discriminator Column found in Arc INFORMACOES_PRIMARIAS - constraint trigger for Arc cannot be generated

CREATE SEQUENCE sq_t_ps_automovel START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tg_t_ps_automovel BEFORE
    INSERT ON t_ps_automovel
    FOR EACH ROW
    WHEN ( new.cd_automovel IS NULL )
BEGIN
    :new.cd_automovel := sq_t_ps_automovel.nextval;
END;
/

CREATE SEQUENCE sq_t_ps_diagnostico START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tg_t_ps_diagnostico BEFORE
    INSERT ON t_ps_diagnostico
    FOR EACH ROW
    WHEN ( new.cd_diagnostico IS NULL )
BEGIN
    :new.cd_diagnostico := sq_t_ps_diagnostico.nextval;
END;
/

CREATE SEQUENCE sq_t_ps_cd_endereco START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tg_t_ps_cd_endereco BEFORE
    INSERT ON t_ps_endereco
    FOR EACH ROW
    WHEN ( new.cd_endereco IS NULL )
BEGIN
    :new.cd_endereco := sq_t_ps_cd_endereco.nextval;
END;
/

CREATE SEQUENCE sq_t_ps_oficina START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tg_t_ps_oficina BEFORE
    INSERT ON t_ps_oficina
    FOR EACH ROW
    WHEN ( new.cd_oficina IS NULL )
BEGIN
    :new.cd_oficina := sq_t_ps_oficina.nextval;
END;
/

CREATE SEQUENCE sq_t_ps_orcamento START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tg_t_ps_orcamento BEFORE
    INSERT ON t_ps_orcamento
    FOR EACH ROW
    WHEN ( new.cd_orcamento IS NULL )
BEGIN
    :new.cd_orcamento := sq_t_ps_orcamento.nextval;
END;
/

CREATE SEQUENCE sq_t_ps_cd_pessoa START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tg_t_ps_cd_pessoa BEFORE
    INSERT ON t_ps_pessoa
    FOR EACH ROW
    WHEN ( new.cd_pessoa IS NULL )
BEGIN
    :new.cd_pessoa := sq_t_ps_cd_pessoa.nextval;
END;
/

CREATE SEQUENCE sq_t_ps_produto START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tg_t_ps_produto BEFORE
    INSERT ON t_ps_produto
    FOR EACH ROW
    WHEN ( new.cd_produto IS NULL )
BEGIN
    :new.cd_produto := sq_t_ps_produto.nextval;
END;
/

CREATE SEQUENCE sq_t_ps_usuario START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tg_t_ps_usuario BEFORE
    INSERT ON t_ps_usuario
    FOR EACH ROW
    WHEN ( new.cd_pessoa IS NULL )
BEGIN
    :new.cd_pessoa := sq_t_ps_usuario.nextval;
END;
/

INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (1, 'Fernanda Ribeiro', 'fernanda.ribeiro@mail.com', '1f1XwPlZal');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (2, 'Gabriel Santos', 'gabriel.santos@mail.com', 'bOD8aTqGgo');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (3, 'Bruno Silva', 'bruno.silva@outlook.com', 'YAXWKAPP2S');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (4, 'Carlos Almeida', 'carlos.almeida@outlook.com', '0lIAnbLkb6');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (5, 'Fernanda Santos', 'fernanda.santos@gmail.com', 'jXWHVFvtDd');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (6, 'Bruno Sousa', 'bruno.sousa@outlook.com', 'd1ucs60bEb');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (7, 'Bruno Sousa', 'bruno.sousa@yahoo.com', 'VbFbLHJcmd');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (8, 'Bruno Rodrigues', 'bruno.rodrigues@gmail.com', 'Z5vhBy37rB');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (9, 'heloisa Sousa', 'heloisa.sousa@outlook.com', 'AXobca4zMc');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (10, 'Juliana Pereira', 'juliana.pereira@gmail.com', 'NjlhnqFqrr');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (11, 'heloisa Lima', 'heloisa.lima@gmail.com', 'ge8cesdzJG');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (12, 'heloisa Pereira', 'heloisa.pereira@mail.com', 'CqibZZRslj');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (13, 'Daniela Oliveira', 'daniela.oliveira@mail.com', 'NHVNTUdOrc');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (14, 'Gabriel Rodrigues', 'gabriel.rodrigues@gmail.com', 'C71MKUEfXG');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (15, 'Igor Santos', 'igor.santos@yahoo.com', 'lGMiGnI0y9');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (16, 'Daniela Oliveira', 'daniela.oliveira@hotmail.com', 'VDxbhZrlIA');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (17, 'Carlos Sousa', 'carlos.sousa@outlook.com', 'cIk3bH1vKp');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (18, 'Igor Costa', 'igor.costa@yahoo.com', 'oPFWxvKg2e');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (19, 'Fernanda Sousa', 'fernanda.sousa@gmail.com', 'Du6IBJmycs');
INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES (20, 'Fernanda Ribeiro', 'fernanda.ribeiro@yahoo.com', 'IRrIhPU5qj');

-- Concluido 

INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (1, '35054790781');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (2, '93499013433');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (3, '76919958261');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (4, '51721155868');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (5, '55580459503');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (6, '41955902892');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (7, '22402694435');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (8, '47861900203');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (9, '16997809233');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (10, '70396652351');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (11, '71616868141');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (12, '72425493827');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (13, '29287465791');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (14, '72439202195');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (15, '43786960411');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (16, '18533194205');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (17, '70193273690');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (18, '90519138678');
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (19, '66839917646')
INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES (20, '32670587840');

-- Concluido

INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (1, 640.64, 'Reduz ruidos e vibrações', 'Filtro de oleo');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (2, 547.74, 'instalação facil e rapida', 'Pneu');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (3, 358.88, 'peça essencial para segurança', 'Sensor de oxigenio');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (4, 917.97, 'peça original de alta qualidade', 'Radiador');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (5, 637.25, 'Melhora a performance do veiculo', 'Velas de ignição');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (6, 311.25, ' com diversos modelos', 'Alternador');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (7, 414.7, 'Ideal para manunteção preventiva', 'Disco de Freio');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (8, 65.58, ' com diversos modelos', 'Farol');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (9, 830.23, 'Melhora a performance do veiculo', 'Retrovisor');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (10, 937.85, 'Ideal para manunteção preventiva', 'Catalisador');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (11, 946.92, 'Resistente e duravel', 'Escapamento');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (12, 248.73, 'Reduz ruidos e vibrações', 'Escapamento');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (13, 514.76, 'Alta eficiencia de desempenho', 'Correia Dentada');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (14, 159.97, 'Resistente e duravel', 'Lanterna Traseira');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (15, 235.39, 'instalação facil e rapida', 'Correia Dentada');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (16, 611.2, 'instalação facil e rapida', 'Bomba de Combustivel');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (17, 634.03, ' com diversos modelos', 'Embreagem');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (18, 813.22, 'peça essencial para segurança', 'Parachoque');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (19, 116.71, 'Alta eficiencia de desempenho', 'Bateria');
INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES (20, 997.98, 'Alta eficiencia de desempenho', 'Alternador');

-- Concluido 

INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (1, 'JKL1379', 'Fiat', 'Modelo E', TO_DATE('2000-10-10', 'YYYY-MM-DD'), 1);
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (2, 'MNO5784', 'Ford', 'Modelo B', TO_DATE('2010-08-26', 'YYYY-MM-DD'), 2);
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (3, 'DEF8098', 'Chevrolet', 'Modelo D', TO_DATE('2004-06-01', 'YYYY-MM-DD'),3); 
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (4, 'JKL5372', 'Chevrolet', 'Modelo E', TO_DATE('2017-02-07', 'YYYY-MM-DD'),4); 
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (5, 'ABC8087', 'Chevrolet', 'Modelo D', TO_DATE('2019-09-13', 'YYYY-MM-DD'),5) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (6, 'JKL5820', 'Volkswagen', 'Modelo D', TO_DATE('2007-12-18', 'YYYY-MM-DD'),6) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (7, 'DEF9746', 'Fiat', 'Modelo C', TO_DATE('2006-08-05', 'YYYY-MM-DD'),7) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (8, 'ABC9578', 'Chevrolet', 'Modelo E', TO_DATE('2007-06-23', 'YYYY-MM-DD'),8); 
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (9, 'JKL7502', 'Ford', 'Modelo E', TO_DATE('2003-07-06', 'YYYY-MM-DD'),9) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (10, 'JKL7991', 'Volkswagen', 'Modelo B', TO_DATE('2018-04-10', 'YYYY-MM-DD'),10); 
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (11, 'JKL8188', 'Volkswagen', 'Modelo A', TO_DATE('2013-05-15', 'YYYY-MM-DD'),11) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (12, 'MNO3174', 'Volkswagen', 'Modelo C', TO_DATE('2007-08-07', 'YYYY-MM-DD'),12) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (13, 'GHI4644', 'Volkswagen', 'Modelo B', TO_DATE('2015-01-29', 'YYYY-MM-DD'),13) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (14, 'MNO9489', 'Ford', 'Modelo A', TO_DATE('2015-01-01', 'YYYY-MM-DD'),14) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (15, 'MNO9217', 'Volkswagen', 'Modelo A', TO_DATE('2002-07-30', 'YYYY-MM-DD'),15); 
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (16, 'ABC9403', 'Ford', 'Modelo C', TO_DATE('2006-04-17', 'YYYY-MM-DD'),16) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (17, 'MNO4968', 'Fiat', 'Modelo A', TO_DATE('2019-07-25', 'YYYY-MM-DD'),17) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (18, 'MNO7764', 'Honda', 'Modelo C', TO_DATE('2016-12-31', 'YYYY-MM-DD'),18) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (19, 'GHI6336', 'Chevrolet', 'Modelo E', TO_DATE('2004-10-23', 'YYYY-MM-DD'),19) ;
INSERT INTO t_ps_automovel (cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa) VALUES (20, 'JKL6317', 'Chevrolet', 'Modelo E', TO_DATE('2000-04-04', 'YYYY-MM-DD'),20) ;

INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (1, '14044503645798', '77354967694');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (2, '74931690892758', '75771841599');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (3, '94500333067741', '87059967462');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (4, '37418012662612', '80685017367');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (5, '81038795926408', '30864254424');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (6, '49451637987548', '68082935449');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (7, '31823713971108', '45038269699');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (8, '51810301362430', '46767675929');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (9, '50194353561144', '23503597234');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (10, '63433432502662', '64024923371');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (11, '41106115237996', '97764820850');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (12, '80519580488232', '54402904889');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (13, '71784020395228', '35335500856');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (14, '31564360634605', '39011921469');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (15, '63423095111068', '32109175267');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (16, '90171404500680', '59386748247');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (17, '73073724130410', '44749135481');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (18, '69081145825108', '37112359694');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (19, '25450401581825', '20229607856');
INSERT INTO t_ps_oficina (cd_oficina, sq_cnpj, sq_inscricao_estadual) VALUES (20, '16178770207910', '92049621378');

INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (1, 1, 'Diagn�stico 1', TO_DATE('2022-07-30', 'YYYY-MM-DD'), TO_DATE('2023-07-17', 'YYYY-MM-DD'), 'A', 1);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (2, 2, 'Diagn�stico 2', TO_DATE('2020-03-29', 'YYYY-MM-DD'), TO_DATE('2022-10-20', 'YYYY-MM-DD'), 'A', 2);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (3, 3, 'Diagn�stico 3', TO_DATE('2023-07-23', 'YYYY-MM-DD'), TO_DATE('2023-09-17', 'YYYY-MM-DD'), 'A', 3);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (4, 4, 'Diagn�stico 4', TO_DATE('2022-10-27', 'YYYY-MM-DD'), TO_DATE('2023-05-10', 'YYYY-MM-DD'), 'A', 4);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (5, 5, 'Diagn�stico 5', TO_DATE('2021-07-04', 'YYYY-MM-DD'), TO_DATE('2022-03-25', 'YYYY-MM-DD'), 'A', 5);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (6, 6, 'Diagn�stico 6', TO_DATE('2023-07-19', 'YYYY-MM-DD'), TO_DATE('2023-08-29', 'YYYY-MM-DD'), 'A', 6);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (7, 7, 'Diagn�stico 7', TO_DATE('2020-12-11', 'YYYY-MM-DD'), TO_DATE('2022-05-21', 'YYYY-MM-DD'), 'A', 7);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (8, 8, 'Diagn�stico 8', TO_DATE('2023-02-01', 'YYYY-MM-DD'), TO_DATE('2023-07-17', 'YYYY-MM-DD'), 'A', 8);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (9, 9, 'Diagn�stico 9', TO_DATE('2023-01-26', 'YYYY-MM-DD'), TO_DATE('2023-10-19', 'YYYY-MM-DD'), 'A', 9);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (10, 10, 'Diagn�stico 10', TO_DATE('2022-03-16', 'YYYY-MM-DD'), TO_DATE('2023-08-17', 'YYYY-MM-DD'), 'A', 10);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (11, 11, 'Diagn�stico 11', TO_DATE('2022-11-14', 'YYYY-MM-DD'), TO_DATE('2022-12-09', 'YYYY-MM-DD'), 'A', 11);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (12, 12, 'Diagn�stico 12', TO_DATE('2021-12-03', 'YYYY-MM-DD'), TO_DATE('2022-05-11', 'YYYY-MM-DD'), 'A', 12);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (13, 13, 'Diagn�stico 13', TO_DATE('2021-06-25', 'YYYY-MM-DD'), TO_DATE('2023-08-10', 'YYYY-MM-DD'), 'A', 13);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (14, 14, 'Diagn�stico 14', TO_DATE('2023-05-04', 'YYYY-MM-DD'), TO_DATE('2023-10-09', 'YYYY-MM-DD'), 'A', 14);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (15, 15, 'Diagn�stico 15', TO_DATE('2020-03-12', 'YYYY-MM-DD'), TO_DATE('2021-11-08', 'YYYY-MM-DD'), 'A', 15);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (16, 16, 'Diagn�stico 16', TO_DATE('2020-11-30', 'YYYY-MM-DD'), TO_DATE('2023-06-04', 'YYYY-MM-DD'), 'A', 16);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (17, 17, 'Diagn�stico 17', TO_DATE('2020-01-29', 'YYYY-MM-DD'), TO_DATE('2021-01-05', 'YYYY-MM-DD'), 'A', 17);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (18, 18, 'Diagn�stico 18', TO_DATE('2023-08-04', 'YYYY-MM-DD'), TO_DATE('2023-10-08', 'YYYY-MM-DD'), 'A', 18);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (19, 19, 'Diagn�stico 19', TO_DATE('2022-01-20', 'YYYY-MM-DD'), TO_DATE('2023-10-27', 'YYYY-MM-DD'), 'A', 19);
INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_oficina) VALUES (20, 20, 'Diagn�stico 20', TO_DATE('2022-02-21', 'YYYY-MM-DD'), TO_DATE('2023-06-14', 'YYYY-MM-DD'), 'A', 20);

INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (1, 'A', 1);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (2, 'I', 2);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (3, 'A', 3);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (4, 'A', 4);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (5, 'A', 5);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (6, 'I', 6);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (7, 'A', 7);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (8, 'A', 8);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (9, 'I', 9);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (10, 'A', 10);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (11, 'I', 11);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (12, 'A', 12);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (13, 'I', 13);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (14, 'A', 14);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (15, 'I', 15);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (16, 'A', 16);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (17, 'I', 17);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (18, 'I', 18);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (19, 'A', 19);
INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES (20, 'I', 20);

INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (1, 1, 5, 10.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (2, 2, 3, 15.50);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (3, 3, 10, 20.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (4, 4, 2, 5.75);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (5, 5, 7, 30.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (6, 6, 4, 9.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (7, 7, 6, 12.25);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (8, 8, 8, 25.50);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (9, 9, 1, 6.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (10, 10, 5, 11.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (11, 11, 3, 8.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (12, 12, 9, 19.50);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (13, 13, 4, 7.50);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (14, 14, 2, 4.25);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (15, 15, 6, 13.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (16, 16, 7, 16.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (17, 17, 5, 7.50);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (18, 18, 8, 22.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (19, 19, 10, 30.00);
INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES (20, 20, 3, 18.00);

INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (1, 1, 'Rua A', 'Cidade A', 'Estado A', 'Bairro A', '12345-678', 10);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (2, 2, 'Rua B', 'Cidade B', 'Estado B', 'Bairro B', '23456-789', 20);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (3, 3, 'Rua C', 'Cidade C', 'Estado C', 'Bairro C', '34567-890', 30);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (4, 4, 'Rua D', 'Cidade D', 'Estado D', 'Bairro D', '45678-901', 40);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (5, 5, 'Rua E', 'Cidade E', 'Estado E', 'Bairro E', '56789-012', 50);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (6, 6, 'Rua F', 'Cidade F', 'Estado F', 'Bairro F', '67890-123', 60);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (7, 7, 'Rua G', 'Cidade G', 'Estado G', 'Bairro G', '78901-234', 70);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (8, 8, 'Rua H', 'Cidade H', 'Estado H', 'Bairro H', '89012-345', 80);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (9, 9, 'Rua I', 'Cidade I', 'Estado I', 'Bairro I', '90123-456', 90);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (10, 10, 'Rua J', 'Cidade J', 'Estado J', 'Bairro J', '01234-567', 100);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (11, 11, 'Rua K', 'Cidade K', 'Estado K', 'Bairro K', '12345-678', 110);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (12, 12, 'Rua L', 'Cidade L', 'Estado L', 'Bairro L', '23456-789', 120);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (13, 13, 'Rua M', 'Cidade M', 'Estado M', 'Bairro M', '34567-890', 130);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (14, 14, 'Rua N', 'Cidade N', 'Estado N', 'Bairro N', '45678-901', 140);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (15, 15, 'Rua O', 'Cidade O', 'Estado O', 'Bairro O', '56789-012', 150);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (16, 16, 'Rua P', 'Cidade P', 'Estado P', 'Bairro P', '67890-123', 160);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (17, 17, 'Rua Q', 'Cidade Q', 'Estado Q', 'Bairro Q', '78901-234', 170);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (18, 18, 'Rua R', 'Cidade R', 'Estado R', 'Bairro R', '89012-345', 180);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (19, 19, 'Rua S', 'Cidade S', 'Estado S', 'Bairro S', '90123-456', 190);
INSERT INTO t_ps_endereco (cd_endereco, cd_pessoa, nm_rua, nm_cidade, nm_estado, nm_bairro, sq_cep, nr_casa) VALUES (20, 20, 'Rua T', 'Cidade T', 'Estado T', 'Bairro T', '01234-567', 200);

-- Relatório utilizando classificação de dados.
SELECT cd_automovel, sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, 
CASE WHEN EXTRACT(YEAR FROM dt_veiculo) < 2000 THEN 'Antigo' WHEN EXTRACT(YEAR FROM dt_veiculo) BETWEEN 2000 AND 2015 THEN 'Usado' ELSE 'Recente'
END AS categoria FROM t_ps_automovel ORDER BY dt_veiculo;
    
SELECT nm_marca_veiculo, COUNT(*) AS quantidade FROM t_ps_automovel
GROUP BY nm_marca_veiculo ORDER BY quantidade DESC;
    
SELECT nm_marca_veiculo, nm_modelo_veiculo,
EXTRACT(YEAR FROM dt_veiculo) AS ano_fabricacao,sq_placa
FROM t_ps_automovel ORDER BY nm_marca_veiculo, ano_fabricacao;

-- Relatório utilizando alguma função do tipo numérica simples.
SELECT nm_marca_veiculo,COUNT(*) AS total_automoveis
FROM t_ps_automovel
GROUP BY nm_marca_veiculo
ORDER BY total_automoveis DESC;

SELECT AVG(EXTRACT(YEAR FROM dt_veiculo)) AS media_ano_fabricacao
FROM t_ps_automovel;

-- Relatório utilizando alguma função de grupo.
SELECT nm_modelo_veiculo, COUNT(*) AS total_automoveis
FROM t_ps_automovel
GROUP BY nm_modelo_veiculo
ORDER BY total_automoveis DESC;

SELECT nm_modelo_veiculo, AVG(cd_automovel) AS media_preco
FROM  t_ps_automovel
GROUP BY nm_modelo_veiculo
ORDER BY media_preco DESC;

-- Relatório utilizando sub consulta. 
SELECT p.nm_produto, p.vl_produto, (SELECT COUNT(o.cd_orcamento) 
FROM t_ps_orcamento o WHERE o.st_orcamento = 'I' 
       AND EXISTS (
           SELECT 1 
           FROM t_ps_itens_orcamento io 
           WHERE io.cd_orcamento = o.cd_orcamento 
             AND io.cd_produto = p.cd_produto
       )
    ) AS total_orcamentos_pendentes FROM 
    t_ps_produto p WHERE 
    EXISTS (
        SELECT 1 
        FROM t_ps_itens_orcamento io 
        WHERE io.cd_produto = p.cd_produto
    );

SELECT
    a.sq_placa,
    a.nm_marca_veiculo,
    a.nm_modelo_veiculo,
    (SELECT d.nm_descricao_diagnostico FROM t_ps_diagnostico d 
     WHERE d.cd_automovel = a.cd_automovel 
     AND d.st_diagnostico = 'A'  
     ORDER BY 
         d.dt_inicio_diagnostico DESC
     FETCH FIRST 1 ROWS ONLY) AS ultimo_diagnostico,
    (SELECT o.sq_cnpj FROM t_ps_oficina o 
     JOIN t_ps_diagnostico d ON o.cd_oficina = d.cd_oficina 
     WHERE 
         d.cd_automovel = a.cd_automovel 
     AND d.st_diagnostico = 'A' 
FETCH FIRST 1 ROWS ONLY) AS oficina_atual FROM t_ps_automovel a;

-- Relatório utilizando junção de tabelas.
SELECT 
    a.sq_placa,
    a.nm_marca_veiculo,
    a.nm_modelo_veiculo,
    o.sq_cnpj,
    d.dt_inicio_diagnostico
FROM t_ps_automovel a JOIN t_ps_diagnostico d ON a.cd_automovel = d.cd_automovel
JOIN t_ps_oficina o ON d.cd_oficina = o.cd_oficina;

SELECT
    a.sq_placa AS "Placa do Automóvel",
    a.nm_marca_veiculo AS "Marca do Veículo",
    a.nm_modelo_veiculo AS "Modelo do Veículo",
    d.nm_descricao_diagnostico AS "Descrição do Diagnóstico",
    d.dt_inicio_diagnostico AS "Data de Início do Diagnóstico",
    d.dt_fim_diagnostico AS "Data de Fim do Diagnóstico",
    o.sq_cnpj AS "CNPJ da Oficina",
    o.sq_inscricao_estadual AS "Inscrição Estadual da Oficina"
FROM t_ps_automovel a
JOIN t_ps_diagnostico d ON a.cd_automovel = d.cd_automovel
JOIN t_ps_oficina o ON d.cd_oficina = o.cd_oficina
WHERE d.st_diagnostico = 'A';  








