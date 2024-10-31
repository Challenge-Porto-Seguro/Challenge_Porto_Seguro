import random
from datetime import datetime, timedelta

def generate_inserts(file_name='inserts_endereco.sql'):
    with open(file_name, 'w', encoding='utf-8') as file:
        for cd in range(1, 21):
            cd_diagnostico = cd
            cd_automovel = cd
            nm_descricao_diagnostico = f"Diagnóstico {cd}"
            dt_inicio_diagnostico = (datetime.now() - timedelta(days=random.randint(1, 30))).strftime('%Y-%m-%d')
            dt_fim_diagnostico = (datetime.now() - timedelta(days=random.randint(0, 29))).strftime('%Y-%m-%d') if random.choice([True, False]) else None
            st_diagnostico = random.choice(['A', 'F']) 
            cd_pessoa = random.randint(1, 100) 
            
            insert = f"""
            INSERT INTO t_ps_diagnostico (cd_diagnostico, cd_automovel, nm_descricao_diagnostico, dt_inicio_diagnostico, dt_fim_diagnostico, st_diagnostico, cd_pessoa)
            VALUES ({cd_diagnostico}, {cd_automovel}, '{nm_descricao_diagnostico}', TO_DATE('{dt_inicio_diagnostico}', 'YYYY-MM-DD'), 
            {'NULL' if dt_fim_diagnostico is None else f"TO_DATE('{dt_fim_diagnostico}', 'YYYY-MM-DD')"}, '{st_diagnostico}', {cd_pessoa});
            """
            file.write(insert.strip() + "\n")

generate_inserts()
