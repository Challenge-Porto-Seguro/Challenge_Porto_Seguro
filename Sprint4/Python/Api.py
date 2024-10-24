from flask import Flask, request, jsonify
import carro_service
import cliente_service
from ErroBanco import ErroBanco

from datetime import datetime

app = Flask(__name__)

@app.route("/cliente", methods=["POST"])
def cadastro_cliente():
    dados = request.json
    try:
        if "nome" not in dados or "email" not in dados or "senha" not in dados or "cpf" not in dados:
            return {"erro": "campos (nome, email, senha e cpf) todos os campos deve ser preenchidos"}, 400
        user = cliente_service.cadastrar_usuario(dados["nome"], dados["email"], dados["senha"], dados["cpf"])
        return jsonify(user), 201
    except ErroBanco as erro:
        return {"error": str(erro)}, 500  

@app.route("/cliente", methods=["GET"])
def clientes_cadastrados():
    try:
        return jsonify(cliente_service.exibir_usuarios()), 200
    except ErroBanco as e:
        return {"error": str(e)}, 500

@app.route("/cliente/<int:id>")
def exibir_pessoa_by_id(id):
    try:
        return jsonify(cliente_service.exibir_usuario_by_id(id))
    except ErroBanco as e:
        return {"error": str(e)}, 500
    except Exception as e:
        return {"error": str(e)}, 404

@app.route("/login", methods=["POST"])
def logar():
    dados = request.json
    try:
        response = cliente_service.logar_usuario(dados["email"], dados["senha"])
        return jsonify(response), 200
    except ErroBanco as e:
        return {"error": str(e)}, 500
    except Exception as e:
        return {"error": str(e)}, 400
    

# mapeamento de carros --------------------------------------

@app.route("/carros", methods=["POST"])
def cadastro_carros():
    dados = request.json
    if "pessoa_id" not in dados or "modelo" not in dados or "placa" not in dados:
        return {"Erro": "Erro todos os campos são obrigatorios"}, 400
    try:
        carro = carro_service.cadastrar_carro(dados["placa"], dados["modelo"], dados["pessoa_id"], dados['marca'], dados['dt_veiculo'])
        return jsonify(carro), 200
    except ErroBanco as e:
        return {"erro": str(e)}, 500
    except Exception as e:
        return {"erro": str(e)}, 400
    
@app.route("/carros/<int:id>", methods=['GET'])
def mostrar_carro_by_id(id):
    try:
        carro_dados = carro_service.exibir_carro_by_id(id)
        return jsonify(carro_dados), 200
    except ErroBanco as e:
        return jsonify({'message': str(e)}), 500
    except Exception as e:
        return jsonify({'message': str(e)}), 404
        

@app.route('/carros/<int:id>', methods=['PUT'])
def atualizar_carro(id):
    data = request.json()
    novo_modelo = data["modelo"]
    if not novo_modelo:
        return jsonify({'erro': 'O campo modelo é obrigatório.'}), 400
    try:
        data = carro_service.atualizar_carro(id, novo_modelo)
        return jsonify(data)
    except ErroBanco as e:
        return {"error": str(e)}, 500
    except Exception as e:
        return {"error": str(e)}

@app.route('/carros/<int:id>', methods=['DELETE'])
def deletar_carro(id):
    try:
        carro_service.deletar_car_by_id(id)
        return "", 204
    except ErroBanco as e:
        return {"error": str(e)}, 500
    except Exception as e:
        return {"error": str(e)}, 404

if __name__ == "__main__":
    app.run(debug=True)