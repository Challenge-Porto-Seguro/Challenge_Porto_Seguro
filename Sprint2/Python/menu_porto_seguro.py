from Automovel import Automovel
from Cliente import Cliente     

def login():
    print("-------------------------------------------")
    print("|                                         |")
    print("|   Sistema de Diagnostico Veicular       |")
    print("|                                         |")
    print("|        1 - Cadastrar Cliente            |")
    print("|        2 - Entrar                       |")
    print("|        3 - sair                         |")
    print("-------------------------------------------\n")

def informacoes_menu(cliente):
    tamanho = len(cliente.__str__())
    print("-------------------------------------------")
    print("|{}".format(cliente.__str__()), " " * (39 - tamanho), "|")
    print("|                                         |")
    print("|   Sistema de Diagnostico Veicular       |")
    print("|                                         |")
    print("|        1 - Cadastrar Veiculo            |")
    print("|        2 - Listar Veiculo               |")
    print("|        3 - Excluir Veiculo              |")
    print("|        4 - Orçamento                    |")
    print("|        5 - Voltar tela de login         |")
    print("-------------------------------------------\n")

def usuarios(lista):
    print("-------------------------------------------")
    print("|                                         |")
    print("|            Lista de Usuarios            |")
    print("|                                         |")

    for usuario in lista:
        tamanho = len(usuario.nome) + 8
        print("|        {}".format(usuario.nome), " " * (39 - tamanho), "|")
    print("-------------------------------------------\n")

def orcamento():
    descricao = input("Descreva seu problema: ")
    pneu_opcao1 = 50
    pneu_opcao2 = 30
    pneu_opcao3 = 60
    farol = 100
    bateria = 300

    """ variavel que define a escolha do pneu escolhido no input do usuario """
    escolha = 0
    """ variavel que vai receber o valor do pneu escolhido """
    pneu = 0
    while(escolha != 1 or escolha != 2 or escolha !=3):
        print("Pelo seu dignostico você precisa de 4 pneu escolha uma das opções de pneu que eu tenho disponivel")
        print(f"1 - pneu1 R${pneu_opcao1}")
        print(f"2 - pneu2 R${pneu_opcao2}")
        print(f"3 - pneu3 R${pneu_opcao3}")
        """ input do usuarui para a escolha do pneu """
        escolha = int(input("Digite uma das opções de pneu: "))

        if(escolha == 1):
            pneu = pneu_opcao1
            break
        elif(escolha == 2):
            pneu = pneu_opcao2
            break
        elif(escolha == 3):
            pneu = pneu_opcao3
            break
        else:
            print("\nOpção invalida\n")
            print("Voltando para a escolha\n")

    orcamento = (4 * pneu) + (2 * farol) + bateria
    print(f"Pelo diagnostico seu carro precisa dos pneus, 2 farois e 1 bateria o valor total = {orcamento}")

def cadastro_cliente(nome, cpf, email, senha):
    try:
        cliente = Cliente(nome, cpf, email, senha)
        print("Cadastro Realizado")
        clientes.append(cliente)
    except Exception as e:
        print(e)
        print("Cadastro invalido")

def cadastro_veiculo(cliente, marca, modelo, ano):
    try:
        automovel = Automovel(marca, modelo, ano)
        cliente.cadastrar_veiculo(automovel)
        print("Cadastro de veiculo efetuado com sucesso")
    except Exception as e:
        print("\n", e)
        print("\nCadastro de veiculo invalido")

""" inicia a variavel opcao para entrar no while """
opcao = 0

clientes = []
""" entra no laço de repetição """
while(opcao != 3):

    login()

    """ altera a variavel opção pegando um input digitado pelo usuario """
    opcao = int(input("Digite a opção desejada: "))

    """ entra na verificação se opção e um dessas alternativas """
    if(opcao == 1):
        print("\nComeçando Cadastro Cliente\n")
        nome = input("Digite seu nome: ")
        cpf = input("Digite seu CPF: ")
        email = input("Digite seu email: ")
        senha = input("Digite sua senha: ")

        email_ja_cadastrado = False
        cpf_ja_cadastrado = False
        for usuario in clientes:
            if(usuario.email.__eq__(email)):
                email_ja_cadastrado = True
            if(usuario.cpf.__eq__(cpf)):
                cpf_ja_cadastrado = True
        if(email_ja_cadastrado):
            print("Esse email ja esta vinculado a um Usuario")
        elif(cpf_ja_cadastrado):
            print("Esse cpf ja esta vinculado a um Usuario")
        else:
            cadastro_cliente(nome, cpf, email, senha)

        print("\nVoltando para o menu")
        opcao = 0

    
    elif(opcao == 2):
        usuarios(clientes)
        if(len(clientes) != 0):
            email = input("Digite o seu email: ")
            senha = input("Digite sua senha: ")

            cliente = None
            for usuario in clientes:
                if(email.__eq__(usuario.email) and senha.__eq__(usuario.senha)):
                    cliente = usuario
            
                    opcao = 0
                    while(opcao != 5):
                        informacoes_menu(cliente)
                        opcao = int(input("Digite a opção desejada: "))

                        if(opcao == 1):
                            print("\nComeçando Cadastro Veiculo\n")
                            marca = input("Digite a marca do veiculo: ")
                            modelo = input("Digite o modelo do seu veiculo: ")
                            ano = int(input("Digite o ano do seu veiculo: "))

                            """ verifica se todas as informações estão validas e cadastra veiculo"""
                            cadastro_veiculo(cliente, marca, modelo, ano)

                            print("\nVoltando para o menu")

                        elif(opcao == 2):
                            cliente.listar_veiculos()
                        
                        elif(opcao == 3):
                            print("\nExclusão de veiculo\n")
                            cliente.listar_veiculos()

                            if(len(cliente.automoveis) != 0):   
                                index = int(input("Digite o codigo do veiculo que irá usar: ")) - 1

                                while index != len(cliente.automoveis) - 1:
                                    print("Veiculo não existente")
                                    index = int(input("Digite o codigo do veiculo que esta na lista")) - 1
                                
                                automovel = cliente.automoveis[index]
                                cliente.excluir_veiculo(automovel)
                                print("Veiculo da marca {} excluido".format(automovel.marca))

                                print("\nVoltando para o menu")
                            else:
                                print("Não existe veiculo cadastrado")
                        elif(opcao == 4):
                            print("\nOrçamento\n")
                            cliente.listar_veiculos()

                            if(len(cliente.automoveis) != 0):   
                                index = int(input("Digite o codigo do veiculo que irá usar: ")) - 1

                                while index != len(cliente.automoveis) - 1:
                                    print("Veiculo não existente")
                                    index = int(input("Digite o codigo do veiculo que esta na lista")) - 1
                                    
                                orcamento()

                                print("\nVoltando para o menu")
                            else:
                                print("Não existe veiculo cadastrado")

                        elif(opcao == 5):
                            print("Voltando a tela de login")
                            
                        else:
                            print("Opção invalida\n")
                            print("Voltando ao menu")
                        
            if(cliente == None):
                print("Login invalido")
                print("\nVoltando ao menu")

        else:
            print("Não existe usuario cadastrado")

        opcao = 0

    elif(opcao == 3):
        print("Obrigado por usar nosso sistema")
        print("Saindo...")
        
    else:
        print("Opção invalida\n")
        print("Voltando a tela de login")



