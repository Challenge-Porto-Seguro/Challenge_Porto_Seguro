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

def verifica_se_carro_existe(id):
    carro = car.exibir_carro_by_id()
    if carro == None:
        raise Exception("Não existe carro com esse id")