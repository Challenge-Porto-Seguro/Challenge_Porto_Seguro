class Orcamento:
    
    def __init__(self, descricao, automovel) -> None:
        self.descricao = descricao
        self.automovel = automovel
        self.produtos = []

    def listar_produtos(self):
        for produto in self.produtos:
            print(produto.nome)
    
    def adicionar_produto(self, produto):
        self.produtos.append(produto)

    def calcular_valor_total(self):
        soma = 0
        for produto in self.produtos:
            soma += produto.valor_total()

        return soma
    