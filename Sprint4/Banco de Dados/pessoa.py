import random
import string

def gerar_nome():
    primeiros_nomes = ["Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda", "Gabriel", "Heloísa", "Igor", "Juliana"]
    sobrenomes = ["Silva", "Santos", "Oliveira", "Pereira", "Costa", "Rodrigues", "Almeida", "Sousa", "Lima", "Ribeiro"]
    return f"{random.choice(primeiros_nomes)} {random.choice(sobrenomes)}"

def gerar_email(nome):
    provedores = ["gmail.com", "yahoo.com", "outlook.com", "mail.com", "hotmail.com"]
    email_nome = nome.lower().replace(" ", ".")
    return f"{email_nome}@{random.choice(provedores)}"

def gerar_senha(tamanho=10):
    caracteres = string.ascii_letters + string.digits
    return ''.join(random.choice(caracteres) for _ in range(tamanho))

with open("inserts_pessoas.sql", "w") as arquivo_sql:
    for i in range(1, 21):
        nome = gerar_nome()
        email = gerar_email(nome)
        senha = gerar_senha()
        
        insert_line = f"INSERT INTO t_ps_pessoa (cd_pessoa, nm_nome, nm_email, sq_senha) VALUES ({i}, '{nome}', '{email}', '{senha}');\n"
        arquivo_sql.write(insert_line)

print("Arquivo 'inserts_pessoas.sql' gerado com sucesso!")
