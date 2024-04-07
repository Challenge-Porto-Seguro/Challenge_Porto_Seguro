opcao = 0
cadastro = False
while(opcao != 4):
    print("\nSistema de Diagnosticar Veiculo\n")

    print("1 - Cadastrar Cliente")
    print("2 - Cadastrar Veiculo")
    print("3 - Orçamento")
    print("4 - sair\n")

    opcao = int(input("Digite a opção desejada: "))

    if(opcao == 1):
        print("\nComeçando Cadastro Cliente\n")
        nome = input("Digite seu nome: ")
        cpf = input("Digite seu CPF: ")
        email = input("Digite seu email: ")
        senha = input("Digite sua senha: ")

        if(not nome or len(cpf) != 11 or not email or len(senha) < 8):
            print("Cadastro invalido")
        else:
            print("Cadastro efetuado com sucesso")
            cadastro = True
        
        print("\nVoltando para o menu")
        opcao = 0

    elif(opcao == 2):
        print("\nComeçando Cadastro Veiculo\n")
        marca = input("Digite a marca do veiculo: ")
        modelo = input("Digite o modelo do seu veiculo: ")
        ano = int(input("Digite o ano do seu veiculo: "))

        if(marca == "" or modelo == "" or ano < 0 or ano > 2024 or cadastro == False):
            print("Cadastro de veiculo invalido")
        else:
            print("Cadastro de veiculo efetuado com sucesso")
        
        print("\nVoltando para o menu")
        opcao = 0

    elif(opcao == 3):
        print("\nOrçamento\n")
        pneu_opcao1 = 50
        pneu_opcao2 = 30
        pneu_opcao3 = 60
        farol = 100
        bateria = 300

        escolha = 0
        pneu = 0
        while(escolha != 1 or escolha != 2 or escolha !=3):
            print("Pelo seu dignostico você precisa de 4 pneu escolha uma das opções de pneu que eu tenho disponivel")
            print(f"1 - pneu1 R${pneu_opcao1}")
            print(f"2 - pneu2 R${pneu_opcao2}")
            print(f"3 - pneu3 R${pneu_opcao3}")
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
        print("\nVoltando para o menu")
        opcao = 0

    elif(opcao == 4):
        print("Obrigado por usar nosso sistema")
        print("Saindo...")
        
    else:
        print("Opção invalida\n")
        print("Voltando ao menu")



