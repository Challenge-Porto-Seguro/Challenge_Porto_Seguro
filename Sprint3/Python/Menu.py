import Carros as carros
import Cliente as cliente

def menu_cliente():
    while True:
        print("\nMenu:")
        print("1. Cadastrar novo usuário")
        print("2. Exibir usuários cadastrados")
        print("3. Login")
        print("4. Sair")
        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            try:
                cliente.cadastrar_usuario()
            except Exception as msgErro:
                print(msgErro.args[0] if msgErro.args else "Erro desconhecido.")
        elif opcao == "2":
            cliente.exibir_usuarios()
        elif opcao == "3":
            if cliente.logar_usuario():
                menu_porto()
            else:
                print("Falha no login. Tente novamente.")
        elif opcao == "4":
            print("[-------------------------------]")
            print("[----        SAINDO..      -----]")
            print("[-------------------------------]")
            break
        else:
            print("Opção inválida. Tente novamente.")

def menu_porto():
    while True:
        print("\n--- MENU PORTO ---")
        print("1. Criar um novo carro")
        print("2. Listar todos os carros")
        print("3. Atualizar um carro")
        print("4. Deletar um carro")
        print("5. Voltar ao menu principal")

        escolha = input("Escolha uma opção: ")
        

        if escolha == '1':
            try:
                carros.criar_carro()
            except Exception as msgErro:
                print(msgErro.args[0])
        elif escolha == '2':
            carros.ler_carros()
        elif escolha == '3':
            carros.atualizar_carro()
        elif escolha == '4':
            carros.deletar_carro()
        elif escolha == '5':
            print("Voltando ao menu principal...")
            break
        else:
            print("Opção inválida. Tente novamente.")

# Executar o menu
menu_cliente()
