from Oracle import conectar_bd

def validar_placa(placa):
    if len(placa) != 7:
        raise ValueError("Placa inválida.")

# Função para criar um carro e inserir no banco de dados
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
