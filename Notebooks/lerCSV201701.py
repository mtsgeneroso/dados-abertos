# -*- coding: UTF-8 -*-
import csv
import pandas as pd

data = pd.read_csv('20170131_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")
#MOSTRA COLUNAS DA TABELA
print (data.columns) 

# print (data.describe())

# print (data.head())
# TIPOS DE DADOS DE CADA COLUNA
# print (data.dtypes)

# print(data.loc[805722,:])
# print(data.loc[805723,:])
# print(data.loc[11191,:])

# print (data.loc[:,['DESCRICAO_CARGO','FUNCAO']])



# for i in range(0, 805747):
	# print (data.loc[i,['SITUACAO_VINCULO','DESCRICAO_CARGO','FUNCAO']])

# for linha in data.loc[:,['SITUACAO_VINCULO','DESCRICAO_CARGO','FUNCAO']]:
# 	print (linha)
#LOC- pega todos os dados da coluna
# print (data.loc[:,['COD_ORG_LOTACAO']])


# mostra os dados ordenados por um tipo
print (data.groupby("COD_UORG_LOTACAO").size())


# df.groupby("grade").size()