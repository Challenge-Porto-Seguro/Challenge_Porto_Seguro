import oracledb as banco

def conectar_bd():
    try:
        connection = banco.connect(
            user="rm557992",  
            password="240504", 
            dsn="oracle.fiap.com.br/orcl"  
        )
        return connection
    except banco.DatabaseError as e:
        print("Erro ao conectar ao banco de dados", e)
        return None