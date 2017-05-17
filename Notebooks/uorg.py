# -*- coding: UTF-8 -*-
import csv
import pandas as pd

ano ='2016'
listdias = ['31', '29', '31', '30', '31', '30', '31', '31', '30', '31', '30', '31' ]
listmes = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ]
m=0

for d in listdias:
	data = pd.read_csv('/home/daiane.oliveira/Documentos/Dados Abertos/dados_servidores/'
	+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

	print (ano+listmes[m]+d)

	for i, row in data.iterrows():

		if row['UORG_LOTACAO'] == str('NaN') or row['UORG_LOTACAO'] == str('NUL') or row['UORG_LOTACAO'] == str('NULL') or row['UORG_LOTACAO'] is None:
			print ("UORG_LOTACAO == nulo")
			print(data.loc[i,['NOME', 'ORG_LOTACAO','ORGSUP_LOTACAO','UORG_LOTACAO']])

		# if row['COD_ORG_LOTACAO'] == row['COD_ORGSUP_LOTACAO'] :
		# 	print ("COD_ORGSUP_LOTACAO == COD_ORG_LOTACAO")
		# 	print(data.loc[i,['NOME', 'ORG_LOTACAO','ORGSUP_LOTACAO','UORG_LOTACAO']])

		# if row['COD_UORG_LOTACAO'] == row['COD_ORGSUP_LOTACAO'] :
		# 	print ("COD_ORGSUP_LOTACAO == COD_UORG_LOTACAO")
		# 	print(data.loc[i,['NOME', 'ORG_LOTACAO','ORGSUP_LOTACAO','UORG_LOTACAO']])

	# print (data.groupby("ORGSUP_LOTACAO").size())
	# print (data.dtypes)
	# print(data['COD_UORG_LOTACAO'].value_const())

	# print(data.ORG_LOTACAO.isnull().any())
	# print(data.COD_ORG_LOTACAO.isnull().any())

	# print(data.COD_UORG_LOTACAO.isnull().any())
	# print(data.UORG_LOTACAO.isnull().any())

	# print(data.COD_ORGSUP_LOTACAO.isnull().any())
	# print(data.ORGSUP_LOTACAO.isnull().any())


	m+=1
