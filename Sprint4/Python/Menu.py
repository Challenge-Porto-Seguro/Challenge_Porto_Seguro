import Carros as carros
import Cliente as cliente

def menu_cliente():
    while True:
        print("\n--- Menu Cliente ---")
        print("1. Cadastrar novo usuário")
        print("2. Exibir usuários cadastrados")
        print("3. Login")
        print("4. Sair")
        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            try:
                nome = input("Digite o nome: ")
                email = input("Digite o email: ")
                senha = input("Digite a senha (mínimo 8 caracteres): ")
                
                # Validação dos dados antes de cadastrar
                cliente.validarEmail(email)
                cliente.validarSenha(senha)
                
                # Cadastrando o usuário
                id_pessoa = cliente.cadastrar_pessoa(nome, email, senha)
                cpf = input("Digite o CPF (11 dígitos): ")
                cliente.validaTelefone(cpf)
                cliente.cadastrar_usuario(cpf, id_pessoa)
                
                print("Usuário cadastrado com sucesso!")
            except Exception as msgErro:
                print(msgErro.args[0] if msgErro.args else "Erro desconhecido.")
        elif opcao == "2":
            usuarios = cliente.exibir_usuarios()
            for usuario in usuarios:
                print(f"ID: {usuario['cd_pessoa']}, Nome: {usuario['nm_nome']}, Email: {usuario['nm_email']}")
        elif opcao == "3":
            email = input("Digite seu email: ")
            senha = input("Digite sua senha: ")
            usuario = cliente.logar_usuario(email, senha)
            if usuario:
                print(f"Bem-vindo, {usuario[0]}!")
                menu_porto(email)
            else:
                print("Falha no login. Tente novamente.")
        elif opcao == "4":
            print("[-------------------------------]")
            print("[----        SAINDO...     -----]")
            print("[-------------------------------]")
            break
        else:
            print("Opção inválida. Tente novamente.")

def menu_porto(email):
    while True:
        print("\n--- Menu Porto ---")
        print("1. Criar um novo carro")
        print("2. Listar todos os carros")
        print("3. Atualizar um carro")
        print("4. Deletar um carro")
        print("5. Voltar ao menu de Cliente")

        escolha = input("Escolha uma opção: ")
        if escolha == '1':
            try:
                placa = input("Digite a placa do carro (7 caracteres): ")
                modelo = input("Digite o modelo do carro: ")
                marca = input("Digite a marca do carro: ")
                dt_veiculo = input("Digite a data de fabricação (DD/MM/YYYY): ")
                carros.validar_placa(placa)
                carros.criar_carro(placa, modelo, email, marca, dt_veiculo)
                print("Carro cadastrado com sucesso!")
            except Exception as msgErro:
                print(msgErro.args[0])
        elif escolha == '2':
            carros_por_pessoa = carros.exibir_todos_carros_por_pessoa(email)
            for carro in carros_por_pessoa:
                print(f"Placa: {carro['Placa do Veiculo']}, Modelo: {carro['Modelo do Veiculo']}, Proprietário: {carro['Codigo do Proprietario']}")
        elif escolha == '3':
            id_carro = input("Digite o ID do carro que deseja atualizar: ")
            novo_modelo = input("Digite o novo modelo: ")
            carros.atualizar_carro(id_carro, novo_modelo)
            print("Carro atualizado com sucesso!")
        elif escolha == '4':
            id_carro = input("Digite o ID do carro que deseja deletar: ")
            carros.deletar_carro(id_carro)
            print("Carro deletado com sucesso!")
        elif escolha == '5':
            print("Voltando ao menu principal...")
            break
        else:
            print("Opção inválida. Tente novamente.")

# Executar o menu
menu_cliente()
