from Automovel import Automovel
from Cliente import Cliente


def valida_informacoes_cliente(nome, cpf, email, senha):
        if(not nome):
            raise Exception("Nome não pode ser nulo")
        elif(len(cpf) != 11):
            raise Exception("CPF invalido") 
        elif(not email):
            raise Exception("Email invalido")
        elif(len(senha) < 8):
            raise Exception("Senha invalida")

def valida_informacoes_automovel(marca, modelo, ano):
        if(not marca):
            raise Exception("Marca não pode ser nulo") 
        elif(not modelo):
            raise Exception("Modelo não pode ser nulo")
        elif(ano < 0 or ano > 2024):
            raise Exception("Ano invalida")
        

def login():
    print("----------------------------------------")
    print("|                                      |")
    print("|   Sistema de Diagnostico Veicular    |")
    print("|                                      |")
    print("|        1 - Cadastrar Cliente         |")
    print("|        2 - Entrar                    |")
    print("|        3 - sair                      |")
    print("----------------------------------------")

def informacoes_menu(cliente):
    print("----------------------------------------")
    print("|{}                           |".format(cliente.__str__()))
    print("|                                      |")
    print("|   Sistema de Diagnostico Veicular    |")
    print("|                                      |")
    print("|        1 - Cadastrar Veiculo         |")
    print("|        2 - Orçamento                 |")
    print("|        3 - Voltar tela de login      |")
    print("----------------------------------------")

def usuarios(lista):
    print("----------------------------------------")
    print("|                                      |")
    print("|            Lista de Usuarios         |")
    print("|                                      |")

    for usuario in lista:
        print("|        {}                |".format(usuario.nome))
    print("----------------------------------------")

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
        valida_informacoes_cliente(nome, cpf, email, senha)
        cliente = Cliente(nome, cpf, email, senha)
        print("Cadastro Realizado")
        clientes.append(cliente)
    except Exception as e:
        print(e)
        print("Cadastro invalido")

def cadastro_veiculo(cliente, marca, modelo, ano):
    try:
        valida_informacoes_automovel(marca, modelo, ano)
        automovel = Automovel(marca, modelo, ano)
        cliente.cadastrar_veiculo(automovel)
        print("Cadastro de veiculo efetuado com sucesso")
    except Exception as e:
        print(e)
        print("Cadastro de veiculo invalido")

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

        """ verifica se todas as informações estão validas """
        cadastro_cliente(nome, cpf, email, senha)

        print("\nVoltando para o menu")
        opcao = 0

    
    elif(opcao == 2):
        usuarios(clientes)
        email = input("Digite o seu email: ")
        senha = input("Digite sua senha: ")

        cliente = 0
        for usuarios in clientes:
            if(email == usuarios.email and senha == usuarios.senha):
                cliente = usuarios
        
                opcao = 0
                while(opcao != 3):
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
                        opcao = 0
                    
                    elif(opcao == 2):
                        print("\nOrçamento\n")
                        cliente.listar_veiculos()

                        index = int(input("Digite o codigo do veiculo que irá usar: ")) - 1

                        while index != len(cliente.automoveis) - 1:
                            print("Veiculo não existente")
                            index = int(input("Digite o codigo do veiculo que esta na lista")) - 1
                            
                        orcamento()

                        print("\nVoltando para o menu")
                        opcao = 0

                    elif(opcao == 3):
                        print("Voltando a tela de login")
                        
                    else:
                        print("Opção invalida\n")
                        print("Voltando ao menu")
                    
            else:
                print("Login invalido")
                print("\nVoltando ao menu")
                opcao = 0
    elif(opcao == 3):
        print("Obrigado por usar nosso sistema")
        print("Saindo...")
        
    else:
        print("Opção invalida\n")
        print("Voltando a tela de login")



