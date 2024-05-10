from Automovel import Automovel
from Cliente import Cliente
from Orcamentos import Orcamento
from Produto import Produto     

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
    print("|        5 - Lista de Orçamentos          |")
    print("|        6 - Voltar tela de login         |")
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

def orcamento(cliente):
    print("\nOrçamento\n")
    cliente.listar_veiculos()

    if(len(cliente.automoveis) != 0):   
        index = int(input("Digite o codigo do veiculo que irá usar: ")) - 1

        while index >= len(cliente.automoveis):
            print("Veiculo não existente")
            index = int(input("Digite o codigo do veiculo que esta na lista")) - 1

        descricao = input("Descreva seu problema: ")

        produto_a_venda()
    
        print("Escolha os itens que vai ter no seu orçamento")

        orcamento = Orcamento(descricao, cliente.automoveis[index])
    
        """ variavel que define a escolha do pneu escolhido no input do usuario """
        escolha = 0

        while(escolha != 4):
            escolha = int(input("\nDigite uma das opções de Produtos: "))
            print("")
            if(escolha == 1):
                quantidade = int(input("Quantos pneu quer adicionar: "))
                orcamento.adicionar_produto(Produto("pneu", 20.0, quantidade))
            elif(escolha == 2):
                quantidade = int(input("Quantas baterias quer adicionar: "))
                orcamento.adicionar_produto(Produto("bateria", 200, quantidade))
            elif(escolha == 3):
                quantidade = int(input("Quantos farois quer adicionar: "))
                orcamento.adicionar_produto(Produto("farois", 150, quantidade))
            elif(escolha == 4):
                print("Saindo das escolhas")
            else:
                print("\nOpção invalida\n")
                print("Voltando para a escolha\n")

        if(orcamento.produtos != []):
            cliente.adicionar_orcamento(orcamento)
            print("Resumo do seu orcamento: Automovel: {}, Descrição: {}, valor total: {:.2f}R$".format(orcamento.automovel.marca, orcamento.descricao, orcamento.calcular_valor_total()))                            
        else:
            print("Você não escolheu nem um produto")

        print("\nVoltando para o menu")
    
    

def cadastro_cliente(clientes):
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
        try:
            cliente = Cliente(nome, cpf, email, senha)
            print("\nCadastro Realizado")
            clientes.append(cliente)
        except Exception as e:
            print(e)
            print("\nCadastro invalido")

def cadastro_veiculo(cliente):
    print("\nComeçando Cadastro Veiculo\n")
    marca = input("Digite a marca do veiculo: ")
    modelo = input("Digite o modelo do seu veiculo: ")
    ano = int(input("Digite o ano do seu veiculo: "))
    try:
        automovel = Automovel(marca, modelo, ano)
        cliente.cadastrar_veiculo(automovel)
        print("Cadastro de veiculo efetuado com sucesso")
    except Exception as e:
        print("\n{}".format(e))
        print("\nCadastro de veiculo invalido")

def produto_a_venda():
    print("1, pneu - 20R$")
    print("2, bateria - 200R$")
    print("3, farol - 150$")
    print("4, sair dos produtos\n")

def excluir_veiculo(cliente):
    print("\nExclusão de veiculo\n")
    cliente.listar_veiculos()

    if(len(cliente.automoveis) != 0):   
        index = int(input("Digite o codigo do veiculo que irá usar: ")) - 1

        while index >= len(cliente.automoveis):
            print("Veiculo não existente")
            index = int(input("Digite o codigo do veiculo que esta na lista: ")) - 1
                                
        excluido = cliente.excluir_veiculo(index)
        print("Veiculo da marca {} excluido".format(excluido.marca))

        print("\nVoltando para o menu")

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
        cadastro_cliente(clientes)

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
                    while(opcao != 6):
                        informacoes_menu(cliente)
                        opcao = int(input("Digite a opção desejada: "))

                        if(opcao == 1):
                            cadastro_veiculo(cliente)
                            print("\nVoltando para o menu")

                        elif(opcao == 2):
                            cliente.listar_veiculos()
                        elif(opcao == 3):
                            excluir_veiculo(cliente)
                        elif(opcao == 4):
                            orcamento(cliente)
                        elif(opcao == 5):
                            print("Listando Orçamentos\n")
                            cliente.listar_orcamentos()
                        elif(opcao == 6):
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



