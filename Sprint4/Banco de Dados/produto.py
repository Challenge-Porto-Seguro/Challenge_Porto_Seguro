import random

def gerar_nome_peca():
    pecas = [
        "Filtro de Óleo", "Pneu", "Bateria", "Pastilha de Freio", "Velas de Ignição",
        "Amortecedor", "Radiador", "Bomba de Combustível", "Filtro de Ar", "Correia Dentada",
        "Disco de Freio", "Alternador", "Catalisador", "Sensor de Oxigênio", "Farol",
        "Lanterna Traseira", "Retrovisor", "Parachoque", "Escapamento", "Embreagem"
    ]
    return random.choice(pecas)

def gerar_descricao_peca():
    descricoes = [
        "Peça original de alta qualidade", "Compatível com diversos modelos",
        "Resistente e durável", "Produto novo e garantido",
        "Ideal para manutenção preventiva", "Peça essencial para segurança",
        "Alta eficiência de desempenho", "Instalação fácil e rápida",
        "Reduz ruídos e vibrações", "Melhora a performance do veículo"
    ]
    return random.choice(descricoes)

def gerar_valor_peca():
    return round(random.uniform(30, 1000), 2)

with open("inserts_pecas_carro.sql", "w", encoding='utf-8') as arquivo_sql:
    for i in range(1, 21):
        nome_peca = gerar_nome_peca()
        descricao_peca = gerar_descricao_peca()
        valor_peca = gerar_valor_peca()
        
        insert_line = f"INSERT INTO t_ps_produto (cd_produto, vl_produto, ds_produto, nm_produto) VALUES ({i}, {valor_peca}, '{descricao_peca}', '{nome_peca}');\n"
        arquivo_sql.write(insert_line)

print("Arquivo 'inserts_pecas_carro.sql' gerado com sucesso!")
