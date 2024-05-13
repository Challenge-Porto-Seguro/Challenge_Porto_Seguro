class Produto:

    def __init__(self, nome, preco, quantidade) -> None:
        self.nome = nome
        self.preco = preco
        self.quatidade = quantidade

    #pega o preco do produto e multiplica com a quantidade e retorna o valor total do produto
    def valor_total(self):
        return self.preco * self.quatidade