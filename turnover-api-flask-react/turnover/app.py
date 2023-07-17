import pickle
import numpy as np
import pandas as pd
import logging, io, os, sys
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import StandardScaler
from flask import Flask, render_template, request, jsonify
import json
from flask_cors import CORS


app = Flask(__name__)
# CORS(app)
cors = CORS(app, resources={r"/previsao": {"origins": "http://localhost:3000"}})

# modelo_turnover = pickle.load(open('/static/modelo/rfc_model.p','rb'))


atributos = ['idade',
			 'pontuacao',
			 'dpto',
			 'distancia_do_trabalho',
			 'educacao',
			 'satisfacao',
			 'genero',
			 'estado_civil',
			 'renda',
			 'anos_exp',
			 'anos_ultima_empresa']

modelo_turnover = None


def mostra_imagem(contratar):

	if contratar == 0:
		contratar_str = 'Não Contratar! OU Infelizmente não foi dessa vez..blá blá blá'
	else:
		contratar_str = 'Contratar! OU Parabéns!'

	return contratar_str


@app.before_first_request
def startup():
	global modelo_turnover 
	modelo_turnover = pickle.load(open(os.path.join(app.root_path, 'static', 'model', 'rfc_model.p'), 'rb'))
	# modelo_turnover = pickle.load(open('rfc_model.p','rb'))


@app.errorhandler(500)
def server_error(e):
    logging.exception('some error')
    return """
    And internal error <pre>{}</pre>
    """.format(e), 500




@app.route('/previsao', methods = ['POST', 'GET'])
def previsao():
	form_data = request.get_json()


	data_dict = pd.DataFrame({
		'idade': [form_data['idade']],
		'pontuacao': [form_data['pontuacao']],
		'dpto': [form_data['dpto']],
		'distancia_do_trabalho': [form_data['distancia_do_trabalho']],
		'educacao': [form_data['educacao']],
		'satisfacao': [form_data['satisfacao']],
		'genero': [form_data['genero']],
		'estado_civil': [form_data['estado_civil']],
		'renda': [form_data['renda']],
		'anos_exp': [form_data['anos_exp']],
		'anos_ultima_empresa': [form_data['anos_ultima_empresa']]
		})

	print(':::::::::::::::::::::::::: DADOS DE ENTRADA ::::::::::::::::::::::::::')
	print(form_data)
	print()
	print()
	previsao = modelo_turnover.predict(data_dict[atributos])	
	print(':::::::::::::::::::::::::: RETORNO DA API ::::::::::::::::::::::::::')
	print(mostra_imagem(previsao))

	return mostra_imagem(previsao)



@app.route("/", methods = ['POST', 'GET'])
def index():
	logging.warning("index!")
	return render_template('index.html')




if __name__ == '__main__':
    app.run(debug=True)