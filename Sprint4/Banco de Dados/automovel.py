import random
from datetime import datetime, timedelta

def gerar_data_aleatoria(inicio, fim):
    delta = fim - inicio
    dias_aleatorios = random.randint(0, delta.days)
    return inicio + timedelta(days=dias_aleatorios)

def gerar_inserts():
    data_inicio = datetime(2000, 1, 1)
    data_fim = datetime(2023, 10, 31)

    marcas = ['Fiat', 'Volkswagen', 'Ford', 'Chevrolet', 'Honda']
    modelos = ['Modelo A', 'Modelo B', 'Modelo C', 'Modelo D', 'Modelo E']

    with open('inserts_automovel.sql', 'w', encoding='utf-8') as f:
        for i in range(1, 21):
            dt_veiculo = gerar_data_aleatoria(data_inicio, data_fim)
            placa = f"{random.randint(100, 999)}-{random.choice(['ABC', 'DEF', 'GHI', 'JKL', 'MNO'])}"
            marca = random.choice(marcas)
            modelo = random.choice(modelos)

            insert = (
                f"INSERT INTO t_ps_automovel ("
                f"cd_automovel, sq_placa, nm_marca_veiculo, "
                f"nm_modelo_veiculo, dt_veiculo, cd_pessoa) "
                f"VALUES ("
                f"{i}, '{placa}', '{marca}', "
                f"'{modelo}', "
                f"TO_DATE('{dt_veiculo.strftime('%Y-%m-%d')}', 'YYYY-MM-DD') \n"
            )
            f.write(insert)

gerar_inserts()
