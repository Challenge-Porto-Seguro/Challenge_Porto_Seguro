from flask import Flask, request, jsonify
import Cliente as cliente
import  Carros as carros
import datetime

app = Flask(__name__)

@app.route("/cliente", methods=["POST"])
def cadastro_cliente():
    dados = request.json
    try:
        cliente.cadastrar_usuario(dados["nome"], dados["email"], dados["senha"])
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
        clienteCarro = cliente.exibir_usuarios_by_id(dados["pessoa_id"])
        print(clienteCarro)
        
        if clienteCarro != None:
            carros.criar_carro(dados["placa"], dados["modelo"], int(dados["pessoa_id"]), dados['marca'], dados['dt_veiculo'])
            return jsonify(carros.exibir_carro(dados["pessoa_id"])), 200
        else :
            return {"Erro": "usuario não encontrado"}, 400
    except Exception as e:
        print(str(e))
        return "", 500
    
@app.route("/carros", methods=['GET'])
def mostrar_carros():
    try:
        dados = request.args
        return jsonify(carros.exibir_carro(dados["email"])), 200
    except TypeError:
        return f"Erro email invalido", 404

# @app.route("carros/<int: id>", methods=["PUT"])
# def altera_carro_by_id(id):
#     data = request.json
    



if __name__ == "__main__":
    app.run(debug=True)