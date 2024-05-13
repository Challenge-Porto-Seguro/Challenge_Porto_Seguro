class Orcamento:
    
    def __init__(self, descricao, automovel) -> None:
        self.descricao = descricao
        self.automovel = automovel
        self.produtos = []

    #lista todos os produtos que tem na lista de produtos
    def listar_produtos(self):
        for produto in self.produtos:
            print(produto.nome)
    
    #adiciona produto a lista de produtos
    def adicionar_produto(self, produto):
        self.produtos.append(produto)

    #pega o valor total de cada produto que tem na lista de produtos, faz a soma e retorna o valor total 
    def calcular_valor_total(self):
        soma = 0
        for produto in self.produtos:
            soma += produto.valor_total()

        return soma
    