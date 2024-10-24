import Cliente as c

def cadastrar_usuario(nome, email, senha, cpf):
    id_pessoa = c.cadastrar_pessoa(nome, email, senha)
    c.cadastrar_usuario(cpf, id_pessoa)
    user = c.exibir_pessoa_by_id(id_pessoa)
    return {"id": user[2], "nome": user[0], "email": user[1], "cpf": user[3]}

def logar_usuario(email, senha):
    user = c.logar_usuario(email, senha)
    if user == None:
        return "email ou senha invalido"
    return {"cd_pessoa": user[2], "nome": user[0], "email": user[1], "cpf": user[3]}

def exibir_usuarios():
    users = c.exibir_usuarios()
    if len(users) > 0:
        return users
    return {"nenhum usuario cadastrado"}

def exibir_usuario_by_id(id):
    verifica_se_user_existe(id)
    user = c.exibir_pessoa_by_id(id)
    return {"id": user[2], "nome": user[0], "email": user[1], "cpf": user[3]}

def verifica_se_user_existe(id):
    if c.exibir_pessoa_by_id == None:
        raise Exception("id usuario invalido")