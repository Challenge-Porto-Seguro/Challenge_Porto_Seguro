import random

num_inserts = 20
file_name = "inserts_t_ps_orcamento.sql"

inserts = []
for i in range(1, num_inserts + 1):
    cd_orcamento = i 
    st_orcamento = random.choice(['A', 'B'])  
    cd_diagnostico = random.randint(1, 100)  
    
    inserts.append(f"INSERT INTO t_ps_orcamento (cd_orcamento, st_orcamento, cd_diagnostico) VALUES ({cd_orcamento}, '{st_orcamento}', {cd_diagnostico});")

with open(file_name, 'w') as file:
    for insert in inserts:
        file.write(insert + "\n")

print(f"Arquivo '{file_name}' gerado com {num_inserts} inserts.")
