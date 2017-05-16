# Pode existir algum servidor COM cargo e SEM VÍNCULO?

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
	data = pd.read_csv('/home/daiane.oliveira/Documentos/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

	print (ano+listmes[m]+d)

	for i, row in data.iterrows():
		if row['SITUACAO_VINCULO'] == str('SEM VINCULO') and not pd.isnull(row['FUNCAO']): 
			print (data.loc[i,:])
	m+=1


# print (data.groupby("SITUACAO_VINCULO").size())

# COM CARGO e SEM VÍNCULO -- SIM
# COM FUNÇÃO e SEM VÍNCULO -- 
# SEM CARGO e SEM VÍNCULO -- SIM
# SEM FUNÇÃO e SEM VÍNCULO -- SIM