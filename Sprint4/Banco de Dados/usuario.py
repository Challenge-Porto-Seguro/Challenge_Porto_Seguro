import random

def gerar_cpf():
        cpf = f"{random.randint(10000000000, 99999999999)}"
        print(cpf)
        if len(cpf) == 11:  
            return cpf

with open("inserts_usuarios.sql", "w", encoding='utf-8') as arquivo_sql:
    for i in range(1, 21):
        print(i)
        cd_pessoa = i  
        sq_cpf = gerar_cpf()
        print(sq_cpf)
        
        insert_line = f"INSERT INTO t_ps_usuario (cd_pessoa, sq_cpf) VALUES ({cd_pessoa}, '{sq_cpf}');\n"
        arquivo_sql.write(insert_line)

print("Arquivo 'inserts_usuarios.sql' gerado com sucesso!")
