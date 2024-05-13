class Cliente:
    def __init__(self, nome, cpf, email, senha):
        # Verifica se a informação recebidas para criar o objeto e valida, se não for lança uma exception   
        self.valida_informacoes_cliente(nome, cpf, email, senha) 
        self.nome = nome
        self.cpf = cpf
        self.email = email
        self.senha = senha
        self.automoveis = []
        self.orcamento = []
        
    # Faz a verificação das informações enviadas    
    def valida_informacoes_cliente(self, nome, cpf, email, senha):
        if(not nome or not nome.replace(" ", "").isalpha()):
            raise Exception("Nome invalido")
        elif(not self.valida_cpf(cpf)):
            raise Exception("CPF invalido") 
        elif(not self.valida_email(email)):
            raise Exception("Email invalido")
        elif(len(senha) < 8):
            raise Exception("Senha invalida")

    #adiciona o veiculo na lista de veiculos
    def cadastrar_veiculo(self, veiculo):
        self.automoveis.append(veiculo)
    #tira o veiculo da lista de veiculos
    def excluir_veiculo(self, index):
        return self.automoveis.pop(index)

    #Lista todos os veiculo que existem na lista deve veiculos
    def listar_veiculos(self):
        print("-------------------------------------------")
        print("|                                         |")
        print("|            Lista de Veiculos            |")
        print("|                                         |")

        i = 1
        for automovel in self.automoveis:
            tamenho = len(automovel.marca) + 10
            print("|        {} {}".format(i, automovel.marca), " " * (39 - tamenho), "|")
            i += 1
        print("-------------------------------------------")

        if(len(self.automoveis) == 0):
            print("\nNenhum automovel cadastrado")
        

    # Faz a verificação se o CPF e valido se for retorna True se não for False
    def valida_cpf(self, cpf):
        if(len(cpf) != 11):
            return False
        for i in range(10):
            if(cpf.__eq__(str(i) * 11)):
                return False
            
        digitos_verificadores = [cpf[9:10], cpf[10:11]]
        digitos_unicos_origem = cpf[0:9]
        digitos_recebido = []
        i = 1
        sum = 0
        for digito in digitos_unicos_origem:
            sum += int(digito) * i
            i += 1

        resto_divisao = sum % 11
        if(resto_divisao == 10):
              digitos_recebido.append("0")
        else:
            digitos_recebido.append(str(resto_divisao))
        
        digitos_unicos_origem += str(digitos_recebido[0])

        i = 0
        sum = 0
        for digito in digitos_unicos_origem:
            sum += int(digito) * i
            i += 1
        
        resto_divisao = sum % 11
        if(resto_divisao == 10):
              digitos_recebido.append("0")
        else:
            digitos_recebido.append(str(resto_divisao))

        if(digitos_verificadores.__eq__(digitos_recebido)):
            return True

        return False
    
    #Verifica se email e valido se for retorna true se não for retorna false
    def valida_email(self, email):
        validacao = []
        for i in email:
            if(i.__eq__("@")):
                validacao.append(i)

        if(email.count(".") == 2):
            if(email[-7:-3].__eq__(".com")):
                validacao.append(".com")
        else:
             if(email[-4:].__eq__(".com")):
                validacao.append(".com")
             
        if(validacao.__eq__(["@", ".com"])):
            return True
        return False
    
    # Adiciona orçamento na lista de orçamentos
    def adicionar_orcamento(self, orcamento):
        self.orcamento.append(orcamento)

    def listar_orcamentos(self):
        print("------------------------------------------")
        print("                                         ")
        print("            Lista de Orçamentos          ")
        print("                                         ")

        i = 1
        for n in self.orcamento:
            print("{}, Marca: {}, Descrição: {}, valor total: {:.2f}R$".format(i, n.automovel.marca, n.descricao, n.calcular_valor_total()))
            i += 1
        print("-------------------------------------------")
        #Se orçamento estiver vazio retorna nenhum orçamento valido
        if(len(self.orcamento) == 0):
            print("\nNenhum orçamento feito")
    
    # retorna o objeto formatado
    def __str__(self) -> str:
        return "Nome: {}, CPF: {}".format(self.nome, self.cpf)