import Oracle as banco

# Dicionário para armazenar os carros
carros = {}

def validar_placa(placa):
    if len(placa) != 7:
        raise ValueError("Placa Inválida.")

# Função para criar um carro
def criar_carro():
    try:
        placa = input("Digite a placa do carro: ").upper()
        validar_placa(placa)
        
        connection = banco.conectar_bd()
        if connection:
            cursor = connection.cursor()
            cursor.execute("SELECT placa FROM carros WHERE placa = :placa", [placa])
            if cursor.fetchone():
                print("Erro: Já existe um carro com essa placa.")
            else:
                nome = input("Digite o nome do carro: ")
                cliente_id = input("Digite o Codigo do cliente: ")
                cursor.execute("""
                    INSERT INTO carros (placa, nome, cliente_id)
                    VALUES (:placa, :nome, :cliente_id)
                """, [placa, nome, cliente_id])
                connection.commit()
                print(f"Carro com placa {placa}, nome {nome} e ID de cliente {cliente_id} foi adicionado.")
        cursor.close()
        connection.close()
    except banco.DatabaseError as e:
        print("Erro ao executar a operação", e)
    except ValueError as e:
        print(e)

# Função para exibir os carros
def ler_carros():
    try:
        connection = banco.conectar_bd()
        if connection:
            cursor = connection.cursor()
            cursor.execute("SELECT placa, nome, cliente_id FROM carros")
            rows = cursor.fetchall()
            if not rows:
                print("Nenhum carro cadastrado.")
            else:
                for placa, nome, cliente_id in rows:
                    print(f"Placa: {placa}, Nome: {nome}, ID do Cliente: {cliente_id}")
        cursor.close()
        connection.close()
    except banco.DatabaseError as e:
        print("Erro ao executar a operação", e)

# Função para Atualizar os carros
def atualizar_carro():
    try:
        placa = input("Digite a placa do carro que deseja atualizar: ").upper()
        connection = banco.conectar_bd()
        if connection:
            cursor = connection.cursor()
            cursor.execute("SELECT placa FROM carros WHERE placa = :placa", [placa])
            if cursor.fetchone():
                novo_nome = input("Digite o novo nome do carro: ")
                novo_cliente_id = input("Digite o novo ID do cliente: ")
                cursor.execute("""
                    UPDATE carros
                    SET nome = :nome, cliente_id = :cliente_id
                    WHERE placa = :placa
                """, [novo_nome, novo_cliente_id, placa])
                connection.commit()
                print(f"Carro com placa {placa} foi atualizado para o nome {novo_nome} e ID do cliente {novo_cliente_id}.")
            else:
                print("Erro: Carro não encontrado.")
        cursor.close()
        connection.close()
    except banco.DatabaseError as e:
        print("Erro ao executar a operação", e)

# Função para deletar os carros
def deletar_carro():
    try:
        placa = input("Digite a placa do carro que deseja deletar: ").upper()
        connection = banco.conectar_bd()
        if connection:
            cursor = connection.cursor()
            cursor.execute("SELECT placa FROM carros WHERE placa = :placa", [placa])
            if cursor.fetchone():
                cursor.execute("DELETE FROM carros WHERE placa = :placa", [placa])
                connection.commit()
                print(f"Carro com placa {placa} foi deletado.")
            else:
                print("Erro: Carro não encontrado.")
        cursor.close()
        connection.close()
    except banco.DatabaseError as e:
        print("Erro ao executar a operação", e)