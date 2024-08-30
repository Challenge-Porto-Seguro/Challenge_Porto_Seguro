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

def cadastrar_usuario():
    print("[-------------------------------]")
    print("[---- Bem-vindo ao sistema -----]")
    print("[-------------------------------]")

    # Solicitar Informações do Usuario.
    nome = input("Digite seu nome: ")
    email = input("Digite seu email: ")
    validarEmail(email)
    telefone = input("Digite seu telefone: ")
    validaTelefone(telefone)
    senha = input("Digite sua senha: ")
    validarSenha(senha)

    # Verificar se o email já está cadastrado
    if email in usuarios:
        print("[-------------------------------]")
        print("[----   EMAIL JÁ CADASTRADO!   -----]")
        print("[-------------------------------]")
    else:
        # Salvar as informações no dicionário
        usuarios[email] = {"nome": nome, "telefone": telefone, "senha": senha}
        print("[-------------------------------]")
        print("[----      CADASTRADO!!    -----]")
        print("[-------------------------------]")

def logar_usuario():
    email = input("Digite seu email: ")
    senha = input("Digite sua senha: ")

    if email in usuarios and usuarios[email]["senha"] == senha:
        print("[-------------------------------]")
        print("[----  LOGIN BEM SUCEDIDO  -----]")
        print("[-------------------------------]")
        return True
    else:
        print("[-------------------------------]")
        print("[-- EMAIL OU SENHA INCORRETOS --]")
        print("[-------------------------------]")
        return False

def exibir_usuarios():
    if not usuarios:
        print("[-------------------------------]")
        print("[----   Nenhum Cadastro!!  -----]")
        print("[-------------------------------]")
    else:
        print("--- Usuários cadastrados: ")
        for email, dados in usuarios.items():
            print(f"[---- Nome: {dados['nome']}, Email: {email}, Telefone: {dados['telefone']} ----]")
