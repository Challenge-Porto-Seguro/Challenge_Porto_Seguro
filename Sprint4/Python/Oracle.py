import oracledb as banco

def conectar_bd():
    try:
        #Dados do Banco Oracle
        connection = banco.connect(
            user="",  # Aqui vai o nome do seu banco no Oracle
            password="", # Aqui vai a seu senha
            dsn="oracle.fiap.com.br/orcl"  
        )
        return connection
    except banco.DatabaseError as e:
        print("Erro ao conectar ao banco de dados", e)
        return None