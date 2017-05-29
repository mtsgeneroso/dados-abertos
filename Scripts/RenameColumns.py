# -*- coding: UTF-8 -*-
import csv
import pandas as pd
import numpy as np


ano ='2016'
listdias = ['31', '29', '31', '30', '31', '30', '31', '31', '30', '31', '30', '31' ]
listmes = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ]

# ano ='2017'
# listdias =['31', '28', '31']
# listmes = ['01', '02', '03']

m=0

for d in listdias:

	data = pd.read_csv('/home/daiane.oliveira/Documentos/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Remuneracao.csv', delimiter="\t", encoding="ISO-8859-9")

	mapa = {'REMUNERAÇÃO':'REMUNERACAO', 'BÁSICA':'BASICA', '(R$)':'REAL', '(U$)':'DOLAR', 'GRATIFICAÇÃO':'GRATIFICACAO', 'ABATE-TETO':'ABATE_TETO', 'FÉRIAS':'FERIAS', 'REMUNERAÇÕES':'REMUNERACOES', 'PSS/RPGS':'PSS_RPGS', 'PENSÃO':'PENSAO', 'SAÚDE':'SAUDE', 'DEDUÇÕES':'DEDUCOES', 'APÓS':'APOS','OBRIGATÓRIAS':'OBRIGATORIAS','INDENIZATÓRIAS':'INDENIZATORIAS', 'HONORÁRIOS':'HONORARIOS','(JETONS)':'JETONS', '(U$)(*)':'DOLAR', '(R$)(*)':'REAL'}

	colunas = data.columns

	print (ano+listmes[m]+d) 

	newColunas = []

	for x in colunas:
		palavras = x.split()
		aux = ''
		if ( len(palavras) > 1 ):
			for p in palavras:
				if p == '-':
					continue
				if p in mapa.keys():
					aux += mapa[p] +'_'
				else:
					aux+= p + '_'
			newColunas.append(aux)
		else:
			newColunas.append(x+'_')

	mapRename = {}
	for i in range(0, len(colunas)):
		mapRename[colunas[i]] = newColunas[i][0:-1]

	data = data.rename(columns= mapRename)

	# print (len(data.columns))

	data.to_csv('/home/daiane.oliveira/Documentos/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_NewRemuneracao.csv', index = False)

	# data = pd.read_csv('20170131_NewRemuneracao.csv', delimiter=",", encoding="ISO-8859-9")

	# print(data.columns) 

	m+=1
