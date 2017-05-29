# -*- coding: UTF-8 -*-
import csv
import pandas as pd

ano ='2016'
listdias = ['31', '29', '31', '30', '31', '30', '31', '31', '30', '31', '30', '31' ]
listmes = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ]

# ano ='2017'
# listdias = ['31', '28', '31']#,'30', '31', '30', '31', '31', '30', '31', '30', '31' ]
# listmes = ['01', '02', '03']# , '04', '05', '06', '07', '08', '09', '10', '11', '12' ]

m=0

# for d in listdias:
# 	data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

# 	print (ano+listmes[m]+d)
# 	if  row['NOME']== str("TICIANA LINHARES COELHO DA SILVA") or row['NOME']==str("ATSLANDS REGO DA ROCHA"): 
# 		print (data.loc[i,['NOME', 'COD_UORG_LOTACAO','UORG_LOTACAO', 'COD_ORG_LOTACAO', 'ORG_LOTACAO', 'ORGSUP_LOTACAO','COD_ORGSUP_LOTACAO']])
# 		print("_"*10)
	
# 	m+=1

# Vendo o que os campos sobre o cargo descrevem (PADRAO_CARGO).
for d in listdias:
	data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

	print (ano+listmes[m]+d)
	for i, row in data.iterrows():
		if  str("HENRY DE HOLANDA CAMPOS") in row['NOME'] and str("CEARA") in row['ORG_LOTACAO']:  
			print (data.loc[i,['NOME', 'PADRAO_CARGO','DESCRICAO_CARGO', 'CLASSE_CARGO','NIVEL_CARGO', 'FUNCAO','COD_UORG_LOTACAO','UORG_LOTACAO', 'COD_ORG_LOTACAO', 'ORG_LOTACAO', 'ORGSUP_LOTACAO','COD_ORGSUP_LOTACAO']])
			print("_"*10)
				
	m+=1

