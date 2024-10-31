import random

num_inserts = 20
file_name = "inserts_t_ps_oficina.sql"

inserts = []
for cd_pessoa in range(1, num_inserts + 1):
    sq_cnpj = f"{random.randint(10000000000000, 99999999999999)}"  
    sq_inscricao_estadual = f"{random.randint(10000000000, 99999999999)}"  
    
    inserts.append(f"INSERT INTO t_ps_oficina (cd_pessoa, sq_cnpj, sq_inscricao_estadual) VALUES ({cd_pessoa}, '{sq_cnpj}', '{sq_inscricao_estadual}');")

with open(file_name, 'w', encoding='utf-8') as file:
    for insert in inserts:
        file.write(insert + "\n")

print(f"Arquivo '{file_name}' gerado com {num_inserts} inserts.")
