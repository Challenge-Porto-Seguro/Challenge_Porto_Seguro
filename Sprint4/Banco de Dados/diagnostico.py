import random
from datetime import datetime, timedelta

def gerar_data_aleatoria(inicio, fim):
    """Gera uma data aleatória entre as duas datas fornecidas."""
    delta = fim - inicio
    dias_aleatorios = random.randint(0, delta.days)
    return inicio + timedelta(days=dias_aleatorios)

def gerar_inserts():
    data_inicio = datetime(2020, 1, 1)
    data_fim = datetime(2023, 10, 31)

    with open('inserts_diagnostico.sql', 'w', encoding='utf-8') as f:
        for i in range(1, 21):
            dt_inicio = gerar_data_aleatoria(data_inicio, data_fim)
            dt_fim = gerar_data_aleatoria(dt_inicio, data_fim) 

            insert = (
                f"INSERT INTO t_ps_diagnostico ("
                f"cd_diagnostico, cd_automovel, nm_descricao_diagnostico, "
                f"dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_pessoa) "
                f"VALUES ("
                f"{i}, {i}, 'Diagnóstico {i}', "
                f"TO_DATE('{dt_inicio.strftime('%Y-%m-%d')}', 'YYYY-MM-DD'), "
                f"TO_DATE('{dt_fim.strftime('%Y-%m-%d')}', 'YYYY-MM-DD'), "
                f"'A', {i});\n"
            )
            f.write(insert)

gerar_inserts()
