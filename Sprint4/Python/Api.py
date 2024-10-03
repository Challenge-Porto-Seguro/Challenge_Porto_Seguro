from flask import Flask, request, jsonify
import Cliente as cliente

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


if __name__ == "__main__":
    app.run(debug=True)