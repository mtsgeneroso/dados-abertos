# -*- coding: UTF-8 -*-
import csv
import pandas as pd
import numpy as np




data = pd.read_csv('20170131_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")
# data2 = pd.read_csv('20170131_Remuneracao.csv', delimiter="\t", encoding="ISO-8859-9")

#MOSTRA COLUNAS DA TABELA
# print (data.columns) 

# data3 = data.columns 
# data3 = []
# for i, row in data.iterrows():
# 	if row['SITUACAO_VINCULO'] == str('SEM VINCULO') and pd.isnull(row['DESCRICAO_CARGO']) and pd.isnull(row['FUNCAO']):
# 		data3.append(data.loc[i,:])

# csvTable = pd.DataFrame(csvTable, data3)

# print (csvTable)
# print(csvTable.groupby('REGIME_JURIDICO').size())

# print(data.groupby('REGIME_JURIDICO').size())


# #TEM VINCULO, TEM CARGO, MAS NÃO TEM SALARIO ?

# tabela = data[['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO']]
# cont = 0
# for i, row in tabela.iterrows():
# 	if row['SITUACAO_VINCULO'] == str('SEM VINCULO') and pd.isnull(row['DESCRICAO_CARGO']) and pd.isnull(row['FUNCAO']): # row['DESCRICAO_CARGO'] == str('NaN') and row['FUNCAO'] == str('NaN'):
# 		if row['REGIME_JURIDICO'] != str('RESIDENCIA MULTIPROFISSIONAL') and row['REGIME_JURIDICO'] != str('MEDICO - PROGRAMA MAIS MEDICO') and row['REGIME_JURIDICO'] != str('MEDICO RESIDENTE') and row['REGIME_JURIDICO'] != str('NATUREZA ESPECIAL') and row['REGIME_JURIDICO'] != str('CONSOLIDACAO DAS LEIS DO TRABALHO') :
# 			print(tabela.loc[i,:])
# 			cont +=1
# print (cont)

tabela = data[['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO']]
#cont = 0
table = []
for i, row in tabela.iterrows():
	if row['SITUACAO_VINCULO'] == str('SEM VINCULO') and pd.isnull(row['DESCRICAO_CARGO']) and pd.isnull(row['FUNCAO']): 
		table.append(tabela.loc[i,:])


tabelaSVinculoSCargoSFuncao = pd.DataFrame(np.array(table), columns = ['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO'])

tabelaSVinculoSCargoSFuncao.to_csv('20170131SVincSOrgSFunc.csv')
#			cont +=1
#print (cont)


tabela = data[['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO']]
#cont = 0
table = []
for i, row in tabela.iterrows():
	if row['SITUACAO_VINCULO'] != str('SEM VINCULO') and pd.isnull(row['DESCRICAO_CARGO']) and pd.isnull(row['FUNCAO']): 
		table.append(tabela.loc[i,:])


tabelaSVinculoSCargoSFuncao = pd.DataFrame(np.array(table), columns = ['NOME', 'REGIME_JURIDICO','SITUACAO_VINCULO', 'DESCRICAO_CARGO', 'FUNCAO'])

tabelaSVinculoSCargoSFuncao.to_csv('20170131TVincSOrgSFunc.csv')