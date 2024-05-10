import datetime

class Automovel:
    def __init__(self, marca, modelo, ano) -> None:
        self.valida_informacoes_automovel(marca, modelo, ano)
        self.marca = marca
        self.modelo = modelo
        self.ano = ano

    
    def valida_informacoes_automovel(self, marca, modelo, ano):
        if(not marca or not marca.replace(" ", "").isalpha()):
            raise Exception("Marca invalida") 
        elif(not modelo or not modelo.replace(" ", "").isalpha()):
            raise Exception("Modelo invalido")
        elif(ano < 1886 or ano > datetime.date.today().year):
            raise Exception("Ano invalida")