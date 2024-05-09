class Cliente:
    def __init__(self, nome, cpf, email, senha):   
        self.valida_informacoes_cliente(nome, cpf, email, senha) 
        self.nome = nome
        self.cpf = cpf
        self.email = email
        self.senha = senha
        self.automoveis = []
        
        
    def valida_informacoes_cliente(self, nome, cpf, email, senha):
        if(not nome):
            raise Exception("Nome não pode ser nulo")
        elif(not self.valida_cpf(cpf)):
            raise Exception("CPF invalido") 
        elif(not self.valida_email(email)):
            raise Exception("Email invalido")
        elif(len(senha) < 8):
            raise Exception("Senha invalida")

    def cadastrar_veiculo(self, veiculo):
        self.automoveis.append(veiculo)

    def excluir_veiculo(self, veiculo):
        self.automoveis.remove(veiculo)

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
    
    def __str__(self) -> str:
        return "Nome: {}, CPF: {}".format(self.nome, self.cpf)