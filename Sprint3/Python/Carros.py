# Listas pra armazenar placas e nomes dos veiculos
placas = []
nomes = []

def validar_placa(placa):
    if len(placa) != 7:
        raise ValueError("Placa Invalida.")
       
# Função para criar um carro
def criar_carro():
    try:
        placa = input("Digite a placa do carro: ").upper()
        validar_placa(placa)
        if placa in placas:
            print("Erro: Já existe um carro com essa placa.")
        else:
            nome = input("Digite o nome do carro: ")
            placas.append(placa)
            nomes.append(nome)
            print(f"Carro com placa {placa} e nome {nome} foi adicionado.")
    except ValueError as e:
        print(e)

# Função para ler os carros
def ler_carros():
    if not placas:
        print("Nenhum carro cadastrado.")
    else:
        for i in range(len(placas)):
            print(f"Placa: {placas[i]}, Nome: {nomes[i]}")

# Função para atualizar um carro
def atualizar_carro():
    placa = input("Digite a placa do carro que deseja atualizar: ").upper()
    if placa in placas:
        index = placas.index(placa)
        novo_nome = input("Digite o novo nome do carro: ")
        nomes[index] = novo_nome
        print(f"Carro com placa {placa} foi atualizado para o nome {novo_nome}.")
    else:
        print("Erro: Carro não encontrado.")

# Função para deletar um carro
def deletar_carro():
    placa = input("Digite a placa do carro que deseja deletar: ").upper()
    if placa in placas:
        index = placas.index(placa)
        del placas[index]
        del nomes[index]
        print(f"Carro com placa {placa} foi deletado.")
    else:
        print("Erro: Carro não encontrado.")