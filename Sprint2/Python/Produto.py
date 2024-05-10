class Produto:

    def __init__(self, nome, preco, quantidade) -> None:
        self.nome = nome
        self.preco = preco
        self.quatidade = quantidade

    def valor_total(self):
        return self.preco * self.quatidade