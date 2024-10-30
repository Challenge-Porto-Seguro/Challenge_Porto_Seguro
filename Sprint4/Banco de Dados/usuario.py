import random

def gerar_cpf():
    while True:
        cpf = f"{random.randint(100000000, 999999999)}{random.randint(0, 9)}"
        if len(cpf) == 11:  
            return cpf

with open("inserts_usuarios.sql", "w") as arquivo_sql:
    for i in range(1, 21):
        cd_pessoa = i  
        sq_cpf = gerar_cpf()
        
        insert_line = f"INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES ({cd_pessoa}, '{sq_cpf}');\n"
        arquivo_sql.write(insert_line)

print("Arquivo 'inserts_usuarios.sql' gerado com sucesso!")
