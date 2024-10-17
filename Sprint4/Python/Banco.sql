CREATE TABLE t_ps_automovel (
    cd_automovel      NUMBER(10) NOT NULL,
    sq_placa          VARCHAR2(11) NOT NULL,
    nm_marca_veiculo  VARCHAR2(255) NOT NULL,
    nm_modelo_veiculo VARCHAR2(255) NOT NULL,
    dt_veiculo        DATE NOT NULL,
    tx_diagnostico    VARCHAR2(255),
    cd_pessoa         NUMBER(10) NOT NULL
);

COMMENT ON COLUMN t_ps_automovel.cd_automovel IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria do automovel. Esse nÃºmero Ã© sequencial e gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_automovel.sq_placa IS
    'esse atributo ira receber o numero da placa do automovel. Seu conteudo Ã© obrigatorio e unico';

COMMENT ON COLUMN t_ps_automovel.nm_marca_veiculo IS
    'esse atribtuo ira receber a marca do automovel. Seu conteudo Ã© obrigatorio';

COMMENT ON COLUMN t_ps_automovel.nm_modelo_veiculo IS
    'esse atribtuo ira receber o modelo do automovel. Seu conteudo Ã© obrigatorio';

COMMENT ON COLUMN t_ps_automovel.dt_veiculo IS
    'esse atribtuo ira receber a data do automovel. Seu conteudo Ã© obrigatorio';

COMMENT ON COLUMN t_ps_automovel.cd_pessoa IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria da pessoa. Esse nÃºmero Ã© sequencial iniciando com 1 a partir do id da pessoa  e Ã©  gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
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
    cd_pessoa                NUMBER(10) NOT NULL
);

ALTER TABLE t_ps_diagnostico
    ADD CONSTRAINT ck_t_ps_status_diagnostico CHECK ( st_diagnostico IN ( 'A', 'I' ) );

COMMENT ON COLUMN t_ps_diagnostico.cd_diagnostico IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria do diagnostico. Esse nÃºmero Ã© sequencial e gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_diagnostico.cd_automovel IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria do automovel. Esse nÃºmero Ã© sequencial iniciando com 1 a partir do id do automovel  e Ã©  gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_diagnostico.nm_descricao_diagnostico IS
    'Esse atributo irÃ¡ receber a descriÃ§Ã£o do diagnostico. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_diagnostico.dt_inicio_diagnostico IS
    'Esse atributo irÃ¡ receber a data de inicio do diagnostico. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_diagnostico.dt_fim_diagnostico IS
    'Esse atributo irÃ¡ receber a data de fim do diagnostico';

COMMENT ON COLUMN t_ps_diagnostico.st_diagnostico IS
    'Esse atributo irÃ¡ recebero status  do diagnostico. Podendo receber a de ativo ou i de inativo. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_diagnostico.cd_pessoa IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria da pessoa. Esse nÃºmero Ã© sequencial iniciando com 1 a partir do id da pessoa  e Ã©  gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
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
    'Esse atributo irÃ¡ receber a chave primÃ¡ria do endereco. Esse nÃºmero Ã© sequencial e gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_endereco.cd_pessoa IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria da pessoa. Esse nÃºmero Ã© sequencial iniciando com 1 a partir do id da pessoa  e Ã©  gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_endereco.nm_rua IS
    'esse atributo ira receber o nome da rua do endereco. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_endereco.nm_cidade IS
    'esse atributo ira receber o nome da cidade do endereco. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_endereco.nm_estado IS
    'esse atributo ira receber o nome do estado do endereco. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_endereco.nm_bairro IS
    'esse atributo ira receber o nome do bairro do endereco. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_endereco.sq_cep IS
    'esse atributo ira receber o cep do endereco. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_endereco.nr_casa IS
    'esse atriibuto ira receber o numero do endereco. Seu conteÃºdo Ã© obrigatÃ³rio.';

ALTER TABLE t_ps_endereco ADD CONSTRAINT endereco_pk PRIMARY KEY ( cd_endereco );

CREATE TABLE t_ps_itens_orcamento (
    cd_orcamento NUMBER(10) NOT NULL,
    cd_produto   NUMBER(10) NOT NULL,
    qt_pedido    NUMBER(3) NOT NULL,
    vl_pedido    NUMBER(4, 2)
);

COMMENT ON COLUMN t_ps_itens_orcamento.cd_orcamento IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria e uma foreign key  do itens_orcamento. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_itens_orcamento.cd_produto IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria e uma foreign key  do itens_orcamento. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_itens_orcamento.qt_pedido IS
    'Esse atributo irÃ¡ receber a quantidade do produto. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_itens_orcamento.vl_pedido IS
    'Esse atributo irÃ¡ receber o valor do pedido. Seu conteÃºdo Ã© obrigatÃ³rio.';

ALTER TABLE t_ps_itens_orcamento ADD CONSTRAINT pedido_pk PRIMARY KEY ( cd_orcamento,
                                                                        cd_produto );

CREATE TABLE t_ps_oficina (
    cd_pessoa             NUMBER(10) NOT NULL,
    sq_cnpj               VARCHAR2(14) NOT NULL,
    sq_inscricao_estadual VARCHAR2(12) NOT NULL
);

COMMENT ON COLUMN t_ps_oficina.cd_pessoa IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria da oficina. Esse nÃºmero Ã© sequencial iniciando com 1 a partir do id da pessoa  e Ã©  gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_oficina.sq_cnpj IS
    'esse atributo ira receber o cnpj da oficina. Seu conteÃºdo Ã© obrigatorio e unico';

COMMENT ON COLUMN t_ps_oficina.sq_inscricao_estadual IS
    'esse atributo ira receber a inscriÃ§Ã£o estadual da oficina. Seu conteÃºdo Ã© obrigatorio e unico';

ALTER TABLE t_ps_oficina ADD CONSTRAINT t_ps_oficina_pk PRIMARY KEY ( cd_pessoa );

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
    'Esse atributo irÃ¡ receber a chave primÃ¡ria do orcamento. Esse nÃºmero Ã© sequencial e gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_orcamento.st_orcamento IS
    'Esse atributo irÃ¡ recebero status  do orcamento. Podendo receber a de ativo ou i de inativo. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_orcamento.cd_diagnostico IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria do diagnosticol. Esse nÃºmero Ã© sequencial iniciando com 1 a partir do cd do diagnostico  e Ã©  gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
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
    sq_senha  VARCHAR2(50) NOT NULL
);

COMMENT ON COLUMN t_ps_pessoa.cd_pessoa IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria da pessoa. Esse nÃºmero Ã© sequencial e gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_pessoa.nm_nome IS
    'esse atributo ira receber o nome da pessoa. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_pessoa.nm_email IS
    'esse atributo ira receber o email da pessoa. Seu conteÃºdo Ã© obrigatÃ³rio e unico.';

COMMENT ON COLUMN t_ps_pessoa.sq_senha IS
    'esse atributo ira receber a senha do email da pessoa. Seu conteÃºdo Ã© obrigatÃ³rio.';

ALTER TABLE t_ps_pessoa ADD CONSTRAINT t_ps_cd_pessoa PRIMARY KEY ( cd_pessoa );

ALTER TABLE t_ps_pessoa ADD CONSTRAINT uk_ps_nm_email UNIQUE ( nm_email );

CREATE TABLE t_ps_produto (
    cd_produto NUMBER(10) NOT NULL,
    vl_produto NUMBER(4, 2) NOT NULL,
    ds_produto VARCHAR2(255),
    nm_produto VARCHAR2(255) NOT NULL
);

COMMENT ON COLUMN t_ps_produto.cd_produto IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria do produto. Esse nÃºmero Ã© sequencial e gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_produto.vl_produto IS
    'Esse atributo irÃ¡ receber  o valor do produto. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_produto.ds_produto IS
    'Esse atributo irÃ¡ receber a descriÃ§Ã£o do produto. Seu conteÃºdo Ã© obrigatÃ³rio.';

COMMENT ON COLUMN t_ps_produto.nm_produto IS
    'Esse atributo irÃ¡ recebero nome  do produto. Seu conteÃºdo Ã© obrigatÃ³rio.';

ALTER TABLE t_ps_produto ADD CONSTRAINT produto_pk PRIMARY KEY ( cd_produto );

CREATE TABLE t_ps_usuario (
    cd_pessoa NUMBER(10) NOT NULL,
    sq_cpf    VARCHAR2(11) NOT NULL
);

COMMENT ON COLUMN t_ps_usuario.cd_pessoa IS
    'Esse atributo irÃ¡ receber a chave primÃ¡ria do usuario. Esse nÃºmero Ã© sequencial iniciando com 1 a partir do id da pessoa  e Ã©  gerado automaticamente pelo sistema. Seu conteÃºdo Ã© obrigatÃ³rio.'
    ;

COMMENT ON COLUMN t_ps_usuario.sq_cpf IS
    'esse atributo ira receber o numero do cpf da pessoa. Seu conteÃºdo e obrigatorio e unico';

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
    ADD CONSTRAINT fk_oficina_diagnostico FOREIGN KEY ( cd_pessoa )
        REFERENCES t_ps_oficina ( cd_pessoa );

ALTER TABLE t_ps_itens_orcamento
    ADD CONSTRAINT fk_orcamento_pedido FOREIGN KEY ( cd_orcamento )
        REFERENCES t_ps_orcamento ( cd_orcamento );

ALTER TABLE t_ps_oficina
    ADD CONSTRAINT fk_pessoa_oficina FOREIGN KEY ( cd_pessoa )
        REFERENCES t_ps_pessoa ( cd_pessoa );

ALTER TABLE t_ps_usuario
    ADD CONSTRAINT fk_pessoa_usuario FOREIGN KEY ( cd_pessoa )
        REFERENCES t_ps_pessoa ( cd_pessoa );

ALTER TABLE t_ps_itens_orcamento
    ADD CONSTRAINT fk_produto_pedido FOREIGN KEY ( cd_produto )
        REFERENCES t_ps_produto ( cd_produto );

ALTER TABLE t_ps_automovel
    ADD CONSTRAINT fk_usuario_automovel FOREIGN KEY ( cd_pessoa )
        REFERENCES t_ps_usuario ( 
            
         );

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
    WHEN ( new.
     IS NULL )
BEGIN
    :new.cd_pessoa := sq_t_ps_oficina.nextval;
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