# Imports Gerais de Banco
import oracledb as bd
from Oracle import conectar_bd
from ErroBanco import ErroBanco

# Import pro Regex que valida Email
import re

# Validação de Telefone
def validaTelefone(telefone):
    if len(telefone) != 11:
        raise Exception("Telefone Invalido")

# Validação de Senha
def validarSenha(senha):
    if len(senha) < 8:
        raise Exception("Senha Invalida")

# Validação de Email    
def validarEmail(email):
    # Regex para validar email
    padrao = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
    if not re.match(padrao, email):
        raise Exception("Email Invalido")

def cadastrar_pessoa(nome, email, senha):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                id = cursor.var(bd.NUMBER)
                cursor.execute("""
                    INSERT INTO t_ps_pessoa (nm_nome, nm_email, sq_senha) VALUES(:nm_nome, :nm_email, :sq_senha)
                returning cd_pessoa into :cd_pessoa""", {"nm_nome": nome,"nm_email": email,"sq_senha": senha, "cd_pessoa": id})
                conn.commit()
                return id.getvalue()[0]
            except Exception as e:
                raise ErroBanco(e)

def cadastrar_usuario(cpf, id_pessoa):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                # Inserir usuário no bd de dados
                cursor.execute("""
                    INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES(:cd_pessoa, :sq_cpf)""", {"cd_pessoa": id_pessoa, "sq_cpf": cpf})
                conn.commit()
            except Exception as e:
                raise ErroBanco(e)

def logar_usuario(email, senha):
     with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                cursor.execute("SELECT p.nm_nome, p.nm_email, p.cd_pessoa, u.sq_cpf FROM t_ps_pessoa p join t_ps_usuario u on(p.cd_pessoa = u.cd_pessoa) WHERE nm_email = :email and sq_senha = :senha", {"email": email, "senha": senha})
                user = cursor.fetchone()
                return user
            except Exception as e:
                raise ErroBanco(e)

def exibir_usuarios():
     with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                cursor = conn.cursor()
                cursor.execute("SELECT cd_pessoa, nm_nome, nm_email FROM t_ps_pessoa order by cd_pessoa")
                dados = []
                rows = cursor.fetchall()
                for r in rows:
                    dados.append({'cd_pessoa': r[0], 'nm_nome': r[1], 'nm_email': r[2]})
                return dados
            except Exception as e:
                raise ErroBanco(e)

def exibir_pessoa_by_id(id):
    try:
        with conectar_bd() as conn:
            with conn.cursor() as cursor:
                cursor.execute("SELECT p.nm_nome, p.nm_email, p.cd_pessoa, u.sq_cpf FROM t_ps_usuario u join t_ps_pessoa p on(u.cd_pessoa = p.cd_pessoa) WHERE u.cd_pessoa = :cd_pessoa", {"cd_pessoa": id})
                pessoa = cursor.fetchone()
                return pessoa
    except Exception as e:
        raise ErroBanco(e)