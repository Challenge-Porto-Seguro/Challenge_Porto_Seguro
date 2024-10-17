from flask import Flask, request, jsonify
import Cliente as cliente
import  Carros as carros

app = Flask(__name__)

@app.route("/cliente", methods=["POST"])
def cadastro_cliente():
    dados = request.json
    campos = ["nome", "email", "telefone", "senha"]
    for campo in campos:
        if campo not in dados:
            return "", 404
    try:
        cliente.cadastrar_usuario(dados["nome"], dados["email"], dados["telefone"], dados["senha"])
    except Exception:
        return "", 404    
    return jsonify(cliente.exibir_usuarios()), 201

@app.route("/cliente", methods=["GET"])
def clientes_cadastrados():
    return jsonify(cliente.exibir_usuarios()), 200

@app.route("/carros/", methods=["POST"])
def cadastro_carros():
    dados = request.json
   
    if "email" not in dados or "modelo" not in dados or "placa" not in dados:
        return {"Erro": "Erro todos os campos são obrigatorios"}, 404
    try:
        carros.criar_carro(dados["email"], dados["placa"], dados["modelo"])

        return jsonify(carros.exibir_carro(dados["email"])), 200
    except Exception:
        return "", 500
    
@app.route("/carros", methods=['GET'])
def mostrar_carros():
    dados = request.args
    return jsonify(carros.exibir_carro(dados["email"])), 200


if __name__ == "__main__":
    app.run(debug=True)