from flask import Flask, request, jsonify
import Cliente as cliente
import  Carros as carros

from datetime import datetime

app = Flask(__name__)

@app.route("/cliente", methods=["POST"])
def cadastro_cliente():
    dados = request.json
    try:
        cliente.cadastrar_usuario(dados["nome"], dados["email"], dados["senha"], dados["cpf"])
    except Exception as ERRO:
        print(ERRO)
        return "", 500  
    return jsonify(cliente.exibir_usuarios()), 201

@app.route("/cliente", methods=["GET"])
def clientes_cadastrados():
    return jsonify(cliente.exibir_usuarios()), 200


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
            return {"Erro": "usuario não encontrado"}, 400
    except Exception as e:
        print(str(e))
        return "", 500

if __name__ == "__main__":
    app.run(debug=True)