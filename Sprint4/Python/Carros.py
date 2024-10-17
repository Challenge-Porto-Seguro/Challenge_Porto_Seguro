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
                    values(:placa, :marca, :modelo, TO_DATE(:dt_veiculo, 'DD-MM-YYYY'), :pessoa_id)
                    returning cd_automovel into :cd_automovel
                """, {"placa": placa, "modelo": modelo, "pessoa_id": pessoa_id, "marca": marca, "dt_veiculo": dt_veiculo, "cd_automovel": id})

            except Exception as e:
                print(f"Erro ao cadastrar o carro: {e}")


# Função para criar um carro 
# def criar_carro(placa, modelo, pessoa_id, marca, dt_veiculo):
#     with conectar_bd() as conn:
#         with conn.cursor() as cursor:
#             try:
#                 id = cursor.var(bd.NUMBER)

#                 cursor.execute("""
#                                     insert into t_ps_automovel (sq_placa, nm_marca_veiculo, nm_modelo_veiculo, dt_veiculo, cd_pessoa)
#                                     values(:placa, :marca, :modelo, TO_DATE(:dt_veiculo, 'DD-MM-YYYY'), :pessoa_id)
#                                     returning cd_automovel into :cd_automovel
#                                 """, {"placa": placa, "modelo": modelo, "pessoa_id": pessoa_id, "marca": marca, "dt_veiculo": dt_veiculo, "cd_automovel": id})
#             except Exception:
#                 print("oi")
                

# Função para exibir carros de um usuário
def exibir_carro(email):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            cursor.execute("SELECT cd_pessoa FROM t_ps_pessoa WHERE nm_email = :nm_email", {"nm_email": email})
            id = cursor.fetchone()[0]
            cursor.execute("SELECT sq_placa, nm_marca_veiculo, cd_pessoa FROM t_ps_automovel WHERE cd_pessoa = :cd_pessoa", {"cd_pessoa": id})
            dados = []
            rows = cursor.fetchall()
            for r in rows:
                dados.append({'Placa do Veiulo': r[1], 'Modelo do Veiculo': r[2], 'Codigo do Proprietario': r[6]})
            if rows:
                print("Carros do usuário:")
                for carro in dados:
                    print(carro)
            else:
                print("Nenhum carro encontrado para este usuário.")
            return dados
        
def exibir_carro_by_id(id):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            cursor.execute("SELECT id FROM t_ps_pessoa WHERE id = :id", {"id": id})
            carro = cursor.fetchone()
            if carro != None:
                print(carro)
                return carro
            else:
                print("Nenhum carro encontrado com esse id.")

# Função para atualizar um carro
def atualizar_carro(id):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                cursor.execute("SELECT * FROM t_ps_automovel cd_automovel = :cd_automovel", {"cd_automovel": id})
                carro = cursor.fetchone()
                if not carro:
                    print(f"Erro: Carro com id {id} não encontrado.")
                    return
                novo_modelo = input("Digite o novo modelo do carro: ")
                cursor.execute(
                    "UPDATE t_ps_automovel SET nm_modelo_veiculo = :nm_modelo_veiculo WHERE sq_placa = :sq_placa",
                    {"nm_modelo_veiculo": novo_modelo, "sq_placa": carro}
                )
                conn.commit()
                print(f"Carro com id {id} foi atualizado para {novo_modelo}.")
            except Exception as e:
                print(f"Erro ao atualizar o carro: {e}")

# Função para deletar um carro
def deletar_carro(email):
    with conectar_bd() as conn:
        with conn.cursor() as cursor:
            try:
                placa = input("Digite a placa do carro que deseja deletar: ").upper()
                cursor.execute("SELECT c.sq_placa, c.nm_marca_veiculo FROM t_ps_automovel c JOIN usuarios u ON c.usuario_id = u.id WHERE u.email = :email", {"email": email})
                carro = cursor.fetchone()
                if not carro:
                    print(f"Erro: Carro com placa {placa} não encontrado.")
                    return
                cursor.execute("DELETE FROM t_ps_automovel WHERE sq_placa = :sq_placa", {"sq_placa": placa})
                conn.commit()
                print(f"Carro com placa {placa} foi deletado.")
            except Exception as e:
                print(f"Erro ao deletar o carro: {e}")
