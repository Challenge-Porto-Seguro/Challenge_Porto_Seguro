from flask import Flask, request, jsonify
import Cliente as cliente
import  Carros as carros

from datetime import datetime

app = Flask(__name__)

@app.route("/cliente", methods=["POST"])
def cadastro_cliente():
    dados = request.json
    try:
        var = cliente.cadastrar_usuario(dados["nome"], dados["email"], dados["senha"], dados["cpf"])
        if var == False:
            return "", 400 
        return jsonify(cliente.exibir_usuarios()), 201
    except Exception as ERRO:
        print(ERRO)
        return "", 500  

@app.route("/cliente", methods=["GET"])
def clientes_cadastrados():
    return jsonify(cliente.exibir_usuarios()), 200


@app.route("/cliente/<int:id>")
def exibir_pessoa_by_id(id):
    return jsonify(cliente.exibir_pessoa_by_id(id))

@app.route("/carros", methods=["POST"])
def cadastro_carros():
    dados = request.json
    print(dados)
    if "pessoa_id" not in dados or "modelo" not in dados or "placa" not in dados:
        return {"Erro": "Erro todos os campos são obrigatorios"}, 404
    try:
        clienteCarro = cliente.exibir_pessoa_by_id(dados["pessoa_id"])
        if clienteCarro != None:
            carros.criar_carro(dados["placa"], dados["modelo"], dados["pessoa_id"], dados['marca'], datetime.strptime(dados['dt_veiculo'], '%Y-%m-%d').date())
            return jsonify(carros.exibir_carro(dados["pessoa_id"])), 200
        else :
            return {dados["pessoa_id"]}, 400
    except Exception as e:
        print(str(e))
        return "", 500
    
@app.route("/carros/<int:id>", methods=['GET'])
def mostrar_carros(id):
    try:
        carro_dados = carros.exibir_carro(id)
        if carro_dados:
            return jsonify(carro_dados), 200
        else:
            return jsonify({'message': 'Carro não encontrado'}), 404
    except Exception as e:
        return jsonify({'message': 'Erro ao buscar dados do carro', 'error': str(e)}), 500

@app.route('/carros/<int:id>', methods=['PUT'])
def atualizar_carro(id):
    data = request.get_json()
    novo_modelo = data.get('nm_modelo_veiculo')

    if not novo_modelo:
        return jsonify({'erro': 'O campo modelo é obrigatório.'}), 400
    data = carros.atualizar_carro(id, novo_modelo)
    return jsonify(data)

@app.route('/carros/<placa>', methods=['DELETE'])
def deletar_carro_endpoint(placa):
    resultado, status_code = carros.deletar_carro(placa)

    return jsonify(resultado), status_code

if __name__ == "__main__":
    app.run(debug=True)