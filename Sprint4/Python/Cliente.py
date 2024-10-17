# Imports Gerais de Banco
import oracledb as bd
from Oracle import conectar_bd

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

# cadastro
def cadastrar_usuario(nome, email, senha):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                id = cursor.var(bd.NUMBER)
                # Inserir usuário no bd de dados
                cursor.execute("""
                    INSERT INTO t_ps_pessoa (nm_nome, nm_email, sq_senha) VALUES(:nm_nome, :nm_email, :sq_senha)
                returning cd_pessoa into :cd_pessoa""", {"nm_nome": nome,"nm_email": email,"sq_senha": senha, "cd_pessoa": id})
                conn.commit()
                print("[-------------------------------]")
                print("[----      CADASTRADO!!    -----]")
                print("[-------------------------------]")
            except bd.DatabaseError as e:
                print("Erro ao executar a operação", e)

#Login
def logar_usuario():
     with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                email = input("Digite seu email: ")
                senha = input("Digite sua senha: ")

                cursor
                cursor.execute("SELECT * FROM usuarios WHERE email = :email", {"email": email})
                row = cursor.fetchone()

                if row and row[4] == senha:
                    print("[-------------------------------]")
                    print("[----  LOGIN BEM SUCEDIDO  -----]")
                    print("[-------------------------------]")
                    return [True, email]
                else:
                    print("[-------------------------------]")
                    print("[-- EMAIL OU SENHA INCORRETOS --]")
                    print("[-------------------------------]")
                    return [False, None]
            except bd.DatabaseError as e:
                print("Erro ao executar a operação", e)
        
#Exibir
def exibir_usuarios():
     with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                cursor = conn.cursor()
                cursor.execute("SELECT cd_pessoa, nm_nome, nm_email, sq_senha FROM t_ps_pessoa order by cd_pessoa")
                dados = []
                rows = cursor.fetchall()
                for r in rows:
                    dados.append({'cd_pessoa': r[0], 'nm_nome': r[1], 'nm_email': r[2], 'sq_senha': r[3]})
                if not rows:
                    print("[-------------------------------]")
                    print("[----   Nenhum Cadastro!!  -----]")
                    print("[-------------------------------]")
                else:
                    print("--- Usuários cadastrados: ")
                    for cliente in dados:
                        print(cliente)
                return dados
            except bd.DatabaseError as e:
                print("Erro ao executar a operação", e)

def exibir_usuarios_by_id(id):
     with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                cursor = conn.cursor()
                cursor.execute("SELECT * FROM t_ps_pessoa WHERE cd_pessoa = :cd_pessoa", {"cd_pessoa": id})
                r = cursor.fetchone()
                usuarios = ({'cd_pessoa': r[0], 'nm_nome': r[1], 'nm_email': r[2], 'sq_senha': r[3]})
                return usuarios
            except bd.DatabaseError as e:
                print("Erro ao executar a operação", e)
