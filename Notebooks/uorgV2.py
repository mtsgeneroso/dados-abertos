# -*- coding: UTF-8 -*-
import csv
import pandas as pd

ano ='2016'
listdias = ['31', '29', '31', '30', '31', '30', '31', '31', '30', '31', '30', '31' ]
listmes = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ]
m=0

for d in listdias:
	data = pd.read_csv('/home/daiane.oliveira/Documentos/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

	print (ano+listmes[m]+d)
	cont = 0
	for i, row in data.iterrows():

		# if pd.isnull(row['UORG_LOTACAO']): # == str('NaN') or row['UORG_LOTACAO'] == str('NUL') or row['UORG_LOTACAO'] == str('NULL') or row['UORG_LOTACAO'] is None:
		# 	print ("UORG_LOTACAO == nulo")
		# 	print(data.loc[i,['NOME', 'ORG_LOTACAO','ORGSUP_LOTACAO','UORG_LOTACAO']])

		if row['COD_ORG_LOTACAO'] == row['COD_ORGSUP_LOTACAO'] and (not pd.isnull(row['COD_UORG_LOTACAO']) ) :
			# print ("COD_ORGSUP_LOTACAO == COD_ORG_LOTACAO")
			# print(data.loc[i,['NOME', 'ORG_LOTACAO','ORGSUP_LOTACAO','UORG_LOTACAO']])
			cont += 1

		# if row['COD_UORG_LOTACAO'] == row['COD_ORG_LOTACAO'] :
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
	print (cont)

# É necessário confirmar essa hipótese para 2016 e 2017: quando um servidor pertence a COD_ORG_LOTACAO = COD_ORGSUP_LOTACAO, então COD_UORG_LOTACAO é nulo.



---------------------------------------------------------------------------------------------------

# Todas as Vezes para 2016 que COD_ORG_LOTACAO = COD_ORGSUP_LOTACAO e  COD_UORG_LOTACAO não é nulo
# 20160131
# 76323
# 20160229
# 75956
# 20160331
# 79760
# 20160430
# 80240
# 20160531
# 79879
# 20160630
# 79661
# 20160731
# 79657
# 20160831
# 79737
# 20160930
# 79606
# 20161031
# 79355
# 20161130
# 79373
# 20161231
# 79674


# Todas as Vezes para 2016 que COD_ORG_LOTACAO = COD_ORGSUP_LOTACAO e  COD_UORG_LOTACAO não é nulo
20170131
79205
20170228
79137



