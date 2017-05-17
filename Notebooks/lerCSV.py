# -*-coding: UTF-8 -*-
import pandas as pd
import numpy as np
import csv

# +ver tuplas com urog não nulo
# select distinct * from cadastro201502 where uorg_lotacao is not null;



# Primeiro, vamos ler o csv usando a biblioteca pandas.
#data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/2014/201404_Servidores/20150430_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

# data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/2017/201701_Servidores/20170131_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/2017/201702_Servidores/20170228_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

# Agora vamos ver como se parecem esses dados.
#data.head()
# print (data.describe())
# tipos da coluna
# print (data.dtypes())
# TODAS AS LINHAS COM ESSAS COLUNAS
#print (data.loc[:, ['SITUACAO_VINCULO' ,'DESCRICAO_CARGO', 'FUNCAO']])

print (data.loc[:, ['Id_SERVIDOR_PORTAL', 'DESCRICAO_CARGO', "u'NOME"]])


print (data.loc[:,1000000])

#DANDO ERROS 

#
#for i in range(0, 10):
#	if data.loc[i, ['SITUACAO_VINCULO']] == 'SEM_VINCULO':
#		print (data.loc[i, ['SITUACAO_VINCULO' ,'DESCRICAO_CARGO', 'FUNCAO']])

# vendo somente a coluna situação vínculo

# print (data.groupby("SITUACAO_VINCULO").size())
# print (data.loc[:,['SITUACAO_VINCULO', 'COD_ORG_LOTACAO']])
# para verificar esse campo em 2015
#print (data.loc[:,['OPCAO_FUNCAO_TOTAL']])
# pegar todas as colunas

# no mes 4 aparece NAN na amostra que vimos.
#print (data.loc[:,['OPCAO_PARCIAL']])

print (data.columns)
#print (len(data.columns))
#print (data.groupby('OPCAO_FUNCAO_TOTAL').size())
