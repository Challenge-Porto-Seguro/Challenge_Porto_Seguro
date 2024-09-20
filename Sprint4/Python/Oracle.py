import oracledb as bd

def conectar_bd():
    try:
        connection = bd.connect(
            user="rm557992",  
            password="240504", 
            dsn="oracle.fiap.com.br/orcl"  
        )
        print("Conexão bem-sucedida!")
        return connection
    except bd.DatabaseError as e:
        print("Erro ao conectar ao banco de dados:", e)
        return None