# -*-coding: UTF-8 -*-
import pandas as pd
import numpy as np
import csv
import sys

ano ='2016'
listdias = ['31', '29', '31', '30', '31', '30', '31', '31', '30', '31', '30', '31' ]
listmes = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ]

# ano ='2017'
# listdias = ['31', '28', '31']
# listmes = ['01', '02', '03']


# gerar o script para criar todos os valores da tabela Orgao com cod_org e nome_org

m=0

for d in listdias:
	data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

	print (ano+listmes[m]+d)


	# # Resumo das variáveis até o momento.Escolhendo as colunas para colocar em outro csv
	csvTable = data[['COD_UORG_LOTACAO','UORG_LOTACAO', 'COD_ORG_LOTACAO', 'ORG_LOTACAO', 'ORGSUP_LOTACAO','COD_ORGSUP_LOTACAO' ]]
	# csvTable.info()

	# #Pegando os valores das colunas selecionadas e adicionando a tabelaOrgaoDF
	tabelaOrgaoDF = pd.DataFrame(csvTable, columns = ['COD_UORG_LOTACAO','UORG_LOTACAO', 'COD_ORG_LOTACAO', 'ORG_LOTACAO', 'COD_ORGSUP_LOTACAO','ORGSUP_LOTACAO' ])
	# print(type(tabelaOrgaoDF))

	# removendo valores repetidos
	dataDropDupCODUORG = data.drop_duplicates(subset=['COD_UORG_LOTACAO','UORG_LOTACAO'], keep = 'first') # keep False drop todas as ocorrências

	dataDropDupCODORG = data.drop_duplicates(subset=['COD_ORG_LOTACAO','ORG_LOTACAO'], keep = 'first')

	dataDropDupCODORGSUP = data.drop_duplicates(subset=['COD_ORGSUP_LOTACAO','ORGSUP_LOTACAO'], keep = 'first')


	# #Pegando os valores das colunas selecionadas e adicionando a tabelaOrgaoDF
	tabelaUOrgaoDROPDUP = pd.DataFrame(dataDropDupCODUORG, columns = ['COD_UORG_LOTACAO','UORG_LOTACAO'])
	tabelaOrgaoDROPDUP = pd.DataFrame(dataDropDupCODORG, columns = ['COD_ORG_LOTACAO', 'ORG_LOTACAO'])
	tabelaOrgaoSDROPDUP = pd.DataFrame(dataDropDupCODORGSUP, columns = ['COD_ORGSUP_LOTACAO','ORGSUP_LOTACAO' ])


	listUORG = tabelaUOrgaoDROPDUP["UORG_LOTACAO"].tolist()
	listCODUORG = tabelaUOrgaoDROPDUP["COD_UORG_LOTACAO"].tolist()
	listORG = tabelaOrgaoDROPDUP["ORG_LOTACAO"].tolist()
	listCODORG = tabelaOrgaoDROPDUP["COD_ORG_LOTACAO"].tolist()
	listORGSUP = tabelaOrgaoSDROPDUP["ORGSUP_LOTACAO"].tolist()
	listCODORGSUP = tabelaOrgaoSDROPDUP["COD_ORGSUP_LOTACAO"].tolist()


	# Fazendo a junção do cod_uorg e uorg para lista de tuplas e depois para conjunto para que não tenha repetição.
	junUORG = [] # é lento essa operação prar gerar o conjunto
	for i in range(0, len(listCODUORG)):
	    junUORG.append((listCODUORG[i], listUORG[i]))     
	# print(len(junUORG))    
	# print(junUORG)    

	# Fazendo a junção do cod_org e org para lista de tuplas e depois para conjunto para que não tenha repetição.
	junORG = []
	for i in range(0, len(listCODORG)):
	    junORG.append((listCODORG[i], listORG[i]))
	# print(len(junORG))    

	# Fazendo a junção do cod_orgsup e orgsup para lista de tuplas e depois para conjunto para que não tenha repetição.
	junORGSUP = []
	for i in range(0, len(listCODORGSUP)):
	    junORGSUP.append((listCODORGSUP[i], listORGSUP[i]))     
	# print(len(junORGSUP))     

	UOrg = pd.DataFrame(junUORG, columns = ['Cod', 'Org'])
	print(len(UOrg))
	Org = pd.DataFrame(junORG, columns = ['Cod', 'Org'])
	print(len(Org))
	OrgSup = pd.DataFrame(junORGSUP, columns = ['Cod', 'Org'])
	print(len(OrgSup))
	Uniao = pd.concat([UOrg, Org, OrgSup])
	print(len(Uniao))


	# Gerando um cvs através do dataframe
	Uniao.to_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores'+ano+listmes[m]+d+'_ORG.csv')
	print("Feito com sucesso")

	
	m+=1



# data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/2017/201701_Servidores/20170131_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9", low_memory=False)



