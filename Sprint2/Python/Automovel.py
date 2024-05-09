import datetime

class Automovel:
    def __init__(self, marca, modelo, ano) -> None:
        self.valida_informacoes_automovel(marca, modelo, ano)
        self.marca = marca
        self.modelo = modelo
        self.ano = ano

    
    def valida_informacoes_automovel(self, marca, modelo, ano):
        if(not marca):
            raise Exception("Marca não pode ser nulo") 
        elif(not modelo):
            raise Exception("Modelo não pode ser nulo")
        elif(ano < 0 or ano > datetime.date.today().year):
            raise Exception("Ano invalida")