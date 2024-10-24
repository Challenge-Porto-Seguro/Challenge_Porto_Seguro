from Oracle import conectar_bd
import oracledb as bd
from ErroBanco import ErroBanco

def validar_placa(placa):
    if len(placa) != 7:
        raise ValueError("Placa inválida.")
    
def criar_carro(placa, modelo, pessoa_id, marca, dt_veiculo):
    try:
        with conectar_bd() as conn:
            with conn.cursor() as cursor:
                id = cursor.var(bd.NUMBER)
                cursor.execute("""
                    insert into t_ps_automovel (sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa)
                    values(:placa, :marca, :modelo, TO_DATE(:dt_veiculo, 'DD/MM/YYYY'), :pessoa_id)
                    returning cd_automovel into :cd_automovel
                """, {"placa": placa, "marca": marca, "modelo": modelo,  "dt_veiculo": dt_veiculo, "pessoa_id": pessoa_id, "cd_automovel": id})
                conn.commit()
                return mostrar_carro(id.getvalue()[0], marca, modelo, placa, dt_veiculo, pessoa_id)
    except Exception as e:
        raise ErroBanco(e)


def exibir_todos_carros_por_pessoa(id_pessoa):
    try:
        with conectar_bd() as conn:
            with conn.cursor() as cursor:
                cursor.execute("SELECT sq_placa, nm_modelo_veiculo, cd_pessoa FROM t_ps_automovel WHERE cd_pessoa = :cd_pessoa", {"cd_pessoa": id_pessoa})
                dados = []
                rows = cursor.fetchall()
                for r in rows:
                    dados.append({'Placa do Veiulo': r[0], 'Modelo do Veiculo': r[1], 'Codigo do Proprietario': r[2]})
                return dados
    except Exception as e:
        raise ErroBanco(e)
    
def exibir_carro_by_id(id):
    try:
        with conectar_bd() as conn:
            with conn.cursor() as cursor:
                cursor.execute("SELECT cd_automovel, sq_placa, nm_modelo_veiculo, cd_pessoa, nm_marca_veiculo, to_char(dt_veiculo, 'DD/MM/YYYY') FROM t_ps_automovel WHERE cd_automovel = :cd_automovel", {"cd_automovel": id})
                carro = cursor.fetchone()
                return carro
    except Exception as e:
        raise ErroBanco(e)    

def atualizar_carro(id, novo_modelo):
    try:
        with conectar_bd() as conn:
            with conn.cursor() as cursor:
                cursor.execute(
                    "UPDATE t_ps_automovel SET nm_modelo_veiculo = :nm_modelo_veiculo WHERE cd_automovel = :cd_automovel",
                    {"nm_modelo_veiculo": novo_modelo, "cd_automovel": id}
                )
                conn.commit()
    except Exception as e:
        raise ErroBanco(e) 
            
def deletar_carro(id):
    try:
        with conectar_bd() as conn:
            with conn.cursor() as cursor:
                cursor.execute(
                    "DELETE FROM t_ps_automovel WHERE cd_automovel = :cd_automovel",
                    {"cd_automovel": id}
                )
                conn.commit()
    except Exception as e:
        raise ErroBanco(e)

def mostrar_carro(id, marca, modelo, placa, dt_veiculo, pessoa_id):
    return {"cd_automovel": int(id.getvalue()[0]), "marca": marca, "modelo": modelo, "sq_placa": placa, "dt_veiculo": dt_veiculo, "pessoa_id": pessoa_id}