
# -*- coding: UTF-8 -*-
import csv
import pandas as pd
import numpy as np


ano ='2016'
listdias = ['31', '29', '31', '30', '31', '30', '31', '31', '30', '31', '30', '31' ]
listmes = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ]

# ano ='2017'
# listdias = ['31', '28', '31']#,'30', '31', '30', '31', '31', '30', '31', '30', '31' ]
# listmes = ['01', '02', '03']# , '04', '05', '06', '07', '08', '09', '10', '11', '12' ]


m=0

for d in listdias:
	data = pd.read_csv('/home/lupum/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

	print (ano+listmes[m]+d)

	######################################################## PESSOAS SEM CARGO, SEM FUNÇÃO E SEM VÍNCULO ############################################################### 

	tabela = data[['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO']]
	table = []
	for i, row in tabela.iterrows():
		if row['SITUACAO_VINCULO'] == str('SEM VINCULO') and pd.isnull(row['DESCRICAO_CARGO']) and pd.isnull(row['FUNCAO']): 
			table.append(tabela.loc[i,:])


	csvTable  = pd.DataFrame(np.array(table), columns = ['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO'])

	csvTable.to_csv('/home/lupum/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_SVinculo.csv')


	######################################################## PESSOAS SEM CARGO, SEM FUNÇÃO E COM VÍNCULO ############################################################### 

	tabela = data[['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO']]
	table = []
	for i, row in tabela.iterrows():
		if row['SITUACAO_VINCULO'] != str('SEM VINCULO') and pd.isnull(row['DESCRICAO_CARGO']) and pd.isnull(row['FUNCAO']): 
			table.append(tabela.loc[i,:])


	csvTable  = pd.DataFrame(np.array(table), columns = ['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO'])

	csvTable.to_csv('/home/lupum/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_CVinculo.csv')

	m+=1