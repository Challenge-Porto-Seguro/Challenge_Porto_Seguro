import random

num_inserts = 20
file_name = "inserts_t_ps_itens_orcamento.sql"

inserts = []
for _ in range(num_inserts):
    cd_orcamento = random.randint(1, 20)  
    cd_produto = random.randint(1, 20)    
    qt_pedido = random.randint(1, 100)    
    vl_pedido = round(random.uniform(1, 999.99), 2)  

    inserts.append(f"INSERT INTO t_ps_itens_orcamento (cd_orcamento, cd_produto, qt_pedido, vl_pedido) VALUES ({cd_orcamento}, {cd_produto}, {qt_pedido}, {vl_pedido});")

with open(file_name, 'w') as file:
    for insert in inserts:
        file.write(insert + "\n")

print(f"Arquivo '{file_name}' gerado com {num_inserts} inserts.")
