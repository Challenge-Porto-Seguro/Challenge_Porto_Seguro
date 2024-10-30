import cliente_service as c
import Carros as car

def cadastrar_carro(placa, modelo, pessoa_id, marca, dt_veiculo):
    c.verifica_se_user_existe(pessoa_id)
    return car.criar_carro(placa, modelo, pessoa_id, marca, dt_veiculo)
    

def exibir_todos_carros_by_pessoa_id(id_pessoa):
    c.verifica_se_user_existe(id_pessoa)
    return car.exibir_todos_carros_por_pessoa(id_pessoa)
    
def atualizar_carro(id, novo_modelo):
    verifica_se_carro_existe(id)
    car.atualizar_carro(id, novo_modelo)
    return car.exibir_carro_by_id(id)
    
def deletar_car_by_id(id):
    verifica_se_carro_existe(id)
    car.deletar_carro(id)

def exibir_carro_by_id(id):
    verifica_se_carro_existe
    ca = car.exibir_carro_by_id(id)
    print(ca)
    return {"cd_automovel": ca[0], "sq_placa": ca[1], "nm_modelo_veiculo": ca[2], "cd_pessoa": ca[3], "nm_marca_veiculo": ca[4], "dt_veiculo": ca[5]}

def verifica_se_carro_existe(id):
    carro = car.exibir_carro_by_id(id)
    if carro == None:
        raise Exception("Não existe carro com esse id")