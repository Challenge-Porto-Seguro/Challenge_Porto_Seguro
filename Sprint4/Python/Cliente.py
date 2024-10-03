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
def cadastrar_usuario(nome, email, telefone, senha):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                # Inserir usuário no bd de dados
                cursor.execute("""
                    INSERT INTO usuarios (nome, email, telefone, senha)
                    VALUES (:nome, :email, :telefone, :senha)
                """, {"nome": nome,"email": email,"telefone": telefone, "senha": senha})
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
                cursor.execute("SELECT id, nome, email, telefone FROM usuarios order by id")
                dados = []
                rows = cursor.fetchall()
                for r in rows:
                    dados.append({'id': r[0], 'nome': r[1], 'email': r[2], 'telefone': r[3]})
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
