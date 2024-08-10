# Dicionário para armazenar os carros
carros = {}

def validar_placa(placa):
    if len(placa) != 7:
        raise Exception("Placa Invalida, Tente Novamente")

# Função para criar um carro
def criar_carro():
    placa = input("Digite a placa do carro: ")
    validar_placa(placa)
    if placa in carros:
        print("Erro: Já existe um carro com essa placa.")
    else:
        nome = input("Digite o nome do carro: ")
        carros[placa] = nome
        print(f"Carro com placa {placa} e nome {nome} foi adicionado.")

# Função para ler os carros
def ler_carros():
    if not carros:
        print("Nenhum carro cadastrado.")
    else:
        for placa, nome in carros.items():
            print(f"Placa: {placa}, Nome: {nome}")

# Função para atualizar um carro
def atualizar_carro():
    placa = input("Digite a placa do carro que deseja atualizar: ")
    if placa in carros:
        novo_nome = input("Digite o novo nome do carro: ")
        carros[placa] = novo_nome
        print(f"Carro com placa {placa} foi atualizado para o nome {novo_nome}.")
    else:
        print("Erro: Carro não encontrado.")

# Função para deletar um carro
def deletar_carro():
    placa = input("Digite a placa do carro que deseja deletar: ")
    if placa in carros:
        del carros[placa]
        print(f"Carro com placa {placa} foi deletado.")
    else:
        print("Erro: Carro não encontrado.")



