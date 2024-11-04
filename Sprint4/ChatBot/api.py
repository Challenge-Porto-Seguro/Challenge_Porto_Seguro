from flask import Flask, request, jsonify
import joblib
import numpy as np

# Carregando o modelo previamente salvo
modelo_lda = joblib.load('modelo_lda.joblib')

# Inicializando o app Flask
app = Flask(__name__)

# Endpoint para prever o resultado baseado em novos dados
@app.route('/predict', methods=['POST'])
def predict():
    try:
        dados = request.json
        
        variaveis_esperadas = [
            "velocidade",
            "voltagem_bateria",
            "corrente_bateria",
            "temperatura_trocador_calor",
            "torque_motor",
            "temperatura_bateria"
        ]
        
        # Verifica se todos os campos necessários estão presentes
        if not all(var in dados for var in variaveis_esperadas):
            return jsonify({"erro": "Dados incompletos. Por favor, forneça todas as variáveis requeridas."}), 400
        
        # Extrai os valores na ordem correta
        novos_dados = np.array([dados[var] for var in variaveis_esperadas]).reshape(1, -1)
        
        # Realiza a previsão com o modelo carregado
        previsao = modelo_lda.predict(novos_dados)
        
        estado = "Boa" if previsao[0] == 1 else "Ruim" 
        # Retorna o resultado da previsão em JSON
        return jsonify({'previsao': estado})
    
    except Exception as e:
        return jsonify({'erro': str(e)}), 400

# Roda o servidor Flask
if __name__ == '__main__':
    app.run(debug=True)