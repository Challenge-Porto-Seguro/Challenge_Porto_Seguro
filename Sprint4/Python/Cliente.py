import oracledb as bd
from Oracle import conectar_bd

# Fazendo retorno do ID
conn = conectar_bd()
cursor = conn.cursor()
idUser = cursor.var(bd.NUMBER)
dado = {"id": idUser}

# Import pro Regex que valida Email
import re

# Dicionário para armazenar os usuários cadastrados
usuarios = {}

# Validação de Telefone
def validaTelefone(telefone):
    if len(telefone) != 11:
        raise Exception("Telefone Invalido")

# Validação de Senha
def validarSenha(senha):
    if len(senha) <= 8:
        raise Exception("Senha Invalida")

# Validação de Email    
def validarEmail(email):
    # Regex para validar email
    padrao = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
    if not re.match(padrao, email):
        raise Exception("Email Invalido")

# cadastro
def cadastrar_usuario():
    if conn:
        try:
            # Solicitar informações do usuário
            nome = input("Digite seu nome: ")
            email = input("Digite seu email: ")
            validarEmail(email)
            telefone = input("Digite seu telefone: ")
            validaTelefone(telefone)
            senha = input("Digite sua senha: ")
            validarSenha(senha)
            # Verificar se o email já está cadastrado no bd
            cursor.execute("SELECT * FROM usuarios WHERE id = :id", [id])
            id = dado["id"].getvalue()[0]
            if cursor.fetchone():
                print("[-----------------------------]")
                print("[----     ID JÁ EXISTE    ----]")
                print("[-----------------------------]")
            else:
                # Inserir usuário no bd de dados
                cursor.execute("""
                    INSERT INTO usuarios (nome, email, telefone, senha)
                    VALUES (:nome, :email, :telefone, :senha)
                """, [nome, email, telefone, senha])
                conn.commit()
                print("[-------------------------------]")
                print("[----      CADASTRADO!!    -----]")
                print("[-------------------------------]")
        except bd.DatabaseError as e:
            print("Erro ao executar a operação", e)
        finally:
            cursor.close()
            conn.close()

#Login
def logar_usuario():
    conn
    if conn:
        try:
            email = input("Digite seu email: ")
            senha = input("Digite sua senha: ")

            cursor
            cursor.execute("SELECT senha FROM usuarios WHERE email = :email", [email])
            row = cursor.fetchone()

            if row and row[0] == senha:
                print("[-------------------------------]")
                print("[----  LOGIN BEM SUCEDIDO  -----]")
                print("[-------------------------------]")
                return True
            else:
                print("[-------------------------------]")
                print("[-- EMAIL OU SENHA INCORRETOS --]")
                print("[-------------------------------]")
                return False
        except bd.DatabaseError as e:
            print("Erro ao executar a operação", e)
        finally:
            cursor.close()
            conn.close()
#Exibir
def exibir_usuarios():
    conn
    if conn:
        try:
            cursor = conn.cursor()
            cursor.execute("SELECT nome, email, telefone FROM usuarios")
            rows = cursor.fetchall()

            if not rows:
                print("[-------------------------------]")
                print("[----   Nenhum Cadastro!!  -----]")
                print("[-------------------------------]")
            else:
                print("--- Usuários cadastrados: ")
                for row in rows:
                    print(f"[---- ID: {row[0]} Nome: {row[1]}, Email: {row[2]}, Telefone: {row[3]} ----]")
        except bd.DatabaseError as e:
            print("Erro ao executar a operação", e)
        finally:
            cursor.close()
            conn.close()
