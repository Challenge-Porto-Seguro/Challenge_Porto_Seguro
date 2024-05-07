class Cliente:
    def __init__(self, nome, cpf, email, senha):
            
        self.nome = nome
        self.cpf = cpf
        self.email = email
        self.senha = senha
        self.automoveis = []
        

    def cadastrar_veiculo(self, veiculo):
        self.automoveis.append(veiculo)

    def excluir_veiculo(self, veiculo):
        self.automoveis.remove(veiculo)

    def listar_veiculos(self):
        print("----------------------------------------")
        print("|                                      |")
        print("|            Lista de Veiculos         |")
        print("|                                      |")

        i = 1
        for automovel in self.automoveis:
            
            print("|        {} {}             |".format(i, automovel.marca))
            i += 1
        print("----------------------------------------")


    def __str__(self) -> str:
        return "Nome: {}, CPF: {}".format(self.nome, self.cpf)