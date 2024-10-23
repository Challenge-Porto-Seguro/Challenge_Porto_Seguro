from Oracle import conectar_bd
import oracledb as bd

def validar_placa(placa):
    if len(placa) != 7:
        raise ValueError("Placa inválida.")
    
def criar_carro(placa, modelo, pessoa_id, marca, dt_veiculo):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                id = cursor.var(bd.NUMBER)
                
                cursor.execute("""
                    insert into t_ps_automovel (sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa)
                    values(:placa, :marca, :modelo, TO_DATE(:dt_veiculo, 'YYYY-MM-DD'), :pessoa_id)
                    returning cd_automovel into :cd_automovel
                """, {"placa": placa, "marca": marca, "modelo": modelo,  "dt_veiculo": dt_veiculo, "pessoa_id": pessoa_id, "cd_automovel": id})
                conn.commit()
            except Exception as e:
                print(f"Erro ao cadastrar o carro: {e}")


def exibir_carro(id_pessoa):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            cursor.execute("SELECT cd_pessoa FROM t_ps_usuario WHERE cd_pessoa = :cd_pessoa", {"cd_pessoa": id_pessoa})
            id = cursor.fetchone()[0]
            cursor.execute("SELECT sq_placa, nm_modelo_veiculo, cd_pessoa FROM t_ps_automovel WHERE cd_pessoa = :cd_pessoa", {"cd_pessoa": id})
            dados = []
            rows = cursor.fetchall()
            for r in rows:
                print(r)
                dados.append({'Placa do Veiulo': r[0], 'Modelo do Veiculo': r[1], 'Codigo do Proprietario': r[2]})
                print("teste")
            if rows:
                print("Carros do usuário:")
                for carro in dados:
                    print(carro)
            else:
                print("Nenhum carro encontrado para este usuário.")
            return dados

def atualizar_carro(placa, novo_modelo):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                cursor.execute("SELECT * FROM t_ps_automovel WHERE sq_placa = :sq_placa", {"sq_placa": placa})
                carro = cursor.fetchone()
                if not carro:
                    return {'erro': f'Carro com a placa {placa} não encontrado.'}, 404
                cursor.execute(
                    "UPDATE t_ps_automovel SET nm_modelo_veiculo = :nm_modelo_veiculo WHERE sq_placa = :sq_placa",
                    {"nm_modelo_veiculo": novo_modelo, "sq_placa": placa}
                )
                conn.commit()
                return {'mensagem': f'Carro com a placa {placa} foi atualizado para {novo_modelo}.'}, 200
            except Exception as e:
                return {'erro': f'Erro ao atualizar o carro: {e}'}, 500


def deletar_carro(placa):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                cursor.execute("SELECT * FROM t_ps_automovel WHERE sq_placa = :sq_placa", {"sq_placa": placa})
                carro = cursor.fetchone()
                if not carro:
                    return {'erro': f'Carro com a sq_placa {placa} não encontrado.'}, 404
                cursor.execute(
                    "DELETE FROM t_ps_automovel WHERE sq_placa = :sq_placa",
                    {"sq_placa": placa}
                )
                conn.commit()
                return {'mensagem': f'Carro com a sq_placa {placa} foi deletado com sucesso.'}, 200
            except Exception as e:
                return {'erro': f'Erro ao deletar o carro: {e}'}, 500
