import Oracle as banco

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
    connection = banco.conectar_bd()
    if connection:
        try:
            cursor = connection.cursor()
            idUSer = cursor.var(banco.NUMBER)
            dado = {"id": idUSer}
            
            # Solicitar informações do usuário
            id = dado["id"].getvalue()[0]    # ID DO BANCO
            nome = input("Digite seu nome: ")
            email = input("Digite seu email: ")
            validarEmail(email)
            telefone = input("Digite seu telefone: ")
            validaTelefone(telefone)
            senha = input("Digite sua senha: ")
            validarSenha(senha)
            # Verificar se o email já está cadastrado no banco
            cursor.execute("SELECT * FROM usuarios WHERE email = :email", [email])
            if cursor.fetchone():
                print("[-------------------------------]")
                print("[----   EMAIL JÁ CADASTRADO!   -----]")
                print("[-------------------------------]")
            else:
                # Inserir usuário no banco de dados
                cursor.execute("""
                    (INSERT INTO usuarios (nome, email, telefone, senha)
                    VALUES (:nome, :email, :telefone, :senha) returning id into :id", dado)
                """, [id, nome, email, telefone, senha])
                connection.commit()
                print("[-------------------------------]")
                print("[----      CADASTRADO!!    -----]")
                print("[-------------------------------]")
        except banco.DatabaseError as e:
            print("Erro ao executar a operação", e)
        finally:
            cursor.close()
            connection.close()

#Login
def logar_usuario():
    connection = banco.conectar_bd()
    if connection:
        try:
            email = input("Digite seu email: ")
            senha = input("Digite sua senha: ")

            cursor = connection.cursor()
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
        except banco.DatabaseError as e:
            print("Erro ao executar a operação", e)
        finally:
            cursor.close()
            connection.close()

#Exibir
def exibir_usuarios():
    connection = banco.conectar_bd()
    if connection:
        try:
            cursor = connection.cursor()
            cursor.execute("SELECT nome, email, telefone FROM usuarios")
            rows = cursor.fetchall()

            if not rows:
                print("[-------------------------------]")
                print("[----   Nenhum Cadastro!!  -----]")
                print("[-------------------------------]")
            else:
                print("--- Usuários cadastrados: ")
                for row in rows:
                    print(f"[---- Nome: {row[0]}, Email: {row[1]}, Telefone: {row[2]} ----]")
        except banco.DatabaseError as e:
            print("Erro ao executar a operação", e)
        finally:
            cursor.close()
            connection.close()
