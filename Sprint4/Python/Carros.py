from Oracle import conectar_bd

def validar_placa(placa):
    if len(placa) != 7:
        raise ValueError("Placa inválida.")

# Função para criar um carro 
def criar_carro(email):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                placa = input("Digite a placa do carro: ").upper()
                validar_placa(placa)
                nome = input("Digite o nome do carro: ")
                cursor.execute("SELECT id FROM usuarios WHERE email = :email", {"email": email})
                usuario = cursor.fetchone()
                if not usuario:
                    print(f"Erro: Usuário com email {email} não encontrado.")
                    return
                usuario_id = usuario[0] 
                cursor.execute("SELECT placa FROM carros WHERE placa = :placa", {"placa": placa})
                if cursor.fetchone():
                    print("Erro: Já existe um carro com essa placa.")
                else:
                    cursor.execute(
                        "INSERT INTO carros (placa, nome, usuario_id) VALUES (:placa, :nome, :usuario_id)",
                        {"placa": placa, "nome": nome, "usuario_id": usuario_id}
                    )
                    conn.commit()  # Confirma a transação
                    print(f"Carro com placa {placa} e nome {nome} foi adicionado para o usuário {email}.")
            except ValueError as e:
                print(e)
            except Exception as e:
                print(f"Erro ao cadastrar o carro: {e}")

# Função para exibir carros de um usuário
def exibir_carro():
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            cursor.execute("SELECT placa, nome, usuario_id FROM carros")
            dados = []
            rows = cursor.fetchall()
            for r in rows:
                dados.append({'placa': r[0], 'nome': r[1], 'usuario_id': r[2]})
            if rows:
                print("Carros do usuário:")
                print(dados)
            else:
                print("Nenhum carro encontrado para este usuário.")

# Função para atualizar um carro
def atualizar_carro(email):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                placa = input("Digite a placa do carro que deseja atualizar: ").upper()
                cursor.execute("SELECT c.placa, c.nome FROM carros c JOIN usuarios u ON c.usuario_id = u.id WHERE u.email = :email", {"email": email})
                carro = cursor.fetchone()
                if not carro:
                    print(f"Erro: Carro com placa {placa} não encontrado.")
                    return
                novo_nome = input("Digite o novo nome do carro: ")
                cursor.execute(
                    "UPDATE carros SET nome = :nome WHERE placa = :placa",
                    {"nome": novo_nome, "placa": placa}
                )
                conn.commit()
                print(f"Carro com placa {placa} foi atualizado para {novo_nome}.")
            except Exception as e:
                print(f"Erro ao atualizar o carro: {e}")

# Função para deletar um carro
def deletar_carro(email):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                placa = input("Digite a placa do carro que deseja deletar: ").upper()
                cursor.execute("SELECT c.placa, c.nome FROM carros c JOIN usuarios u ON c.usuario_id = u.id WHERE u.email = :email", {"email": email})
                carro = cursor.fetchone()
                if not carro:
                    print(f"Erro: Carro com placa {placa} não encontrado.")
                    return
                cursor.execute("DELETE FROM carros WHERE placa = :placa", {"placa": placa})
                conn.commit()
                print(f"Carro com placa {placa} foi deletado.")
            except Exception as e:
                print(f"Erro ao deletar o carro: {e}")
