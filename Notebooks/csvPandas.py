# -*- coding: UTF-8 -*-
import csv
import pandas as pd
# import matplotlib.pyplot as plt
import sys
data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/2017/201701_Servidores/20170131_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9", low_memory=False)

# data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/2017/201702_Servidores/20170228_Cadastro.csv', delimiter="\t", encoding="ISO-8859-9")

# data = pd.read_csv('/home/crislanio.macedo/DADOSABERTOS/Dados Abertos/dados_servidores/2015/201508_Servidores/20150831_Honorarios(Jetons).csv', delimiter="\t", encoding="ISO-8859-9", low_memory=False)

#MOSTRA COLUNAS DA TABELA
# print (data.columns) 

#print (data.head())

# print (data.describe())

# print (data.head())
# TIPOS DE DADOS DE CADA COLUNA
# print (data.dtypes)

# print(data.loc[805722,:])
# print (data.loc[:,['Id_SERVIDOR_PORTAL', 'DESCRICAO_CARGO', 'NOME']])

# print(data.loc[805723,:])
# print(data.loc[11191,:])

# print (data.loc[:,['DESCRICAO_CARGO','FUNCAO']])


'''
for i in range(0, 10): #805747
    print (data.loc[i,['Id_SERVIDOR_PORTAL','DESCRICAO_CARGO','NOME']])
#nao
#data.sort_values(by='Id_SERVIDOR_PORTAL').head()
'''
'''
# iterando cada linha e procurando pelo nome
for i, row in data.iterrows():
    if  row['NOME']== str("PAULO JOSE DOS REIS SOUZA"):# row['NOME']==str("LIVIA ALMADA CRUZ RAFAEL") or row['NOME']==str("TICIANA LINHARES COELHO DA SILVA") or row['NOME']== str("MARIA VIVIANE DE MENEZES"): 
        print (data.loc[i,['ID_SERVIDOR_PORTAL','NOME', 'EMPRESA']])
#        print (data.loc[i,['Id_SERVIDOR_PORTAL','NOME', 'DESCRICAO_CARGO']])
        break
    else:
        pass
'''    

#row['NOME']== str("PAULO JOSE DOS REIS SOUZA"): 
# ror row['NOME']== str("HO YIU CHENG"):

# for linha in data.loc[:,['SITUACAO_VINCULO','DESCRICAO_CARGO','FUNCAO']]:
#   print (linha)
#LOC- pega todos os dados da coluna
#print (data.loc[805722,['Id_SERVIDOR_PORTAL','DESCRICAO_CARGO','NOME']])
# print (data.loc[100001:100010,['Id_SERVIDOR_PORTAL','DESCRICAO_CARGO','NOME']])


# mostra os dados ordenados por um tipo
# print (data.groupby("Id_SERVIDOR_PORTAL").size())

# print (data.groupby("Id_SERVIDOR_PORTAL"))

# df.groupby("grade").size()
# ====================================Podemos concentrar U_ORG, ORG, ORG_SUP dentro de ORG?=========================
# print (data.columns) 
'''
k =0
# iterando cada linha e procurando pelo nome
for i, row in data.iterrows():
#    if  row['NOME']== str("PAULO JOSE DOS REIS SOUZA"):# row['NOME']==str("LIVIA ALMADA CRUZ RAFAEL") or row['NOME']==str("TICIANA LINHARES COELHO DA SILVA") or row['NOME']== str("MARIA VIVIANE DE MENEZES"): 
#        print (data.loc[i,['ID_SERVIDOR_PORTAL','NOME', 'EMPRESA']])
#        print (data.loc[i,['Id_SERVIDOR_PORTAL','NOME', 'DESCRICAO_CARGO']])
    print "----------------------------------------------------------------------------"
    print (data.loc[i,['UORG_LOTACAO','ORG_LOTACAO', 'ORGSUP_LOTACAO']])
#    print "----------------------------------------------------------------------------\n"
#    print (data.loc[i,['UORG_EXERCICIO','ORG_EXERCICIO', 'ORGSUP_EXERCICIO']])
    k+=1
    if k==10:
        break

print (data.groupby("ORGSUP_LOTACAO").size())

'''

#        break
#    else:
#        pass
    
'''
DE ACORDO COM OS DADOS NÃO É BOM CONCENTRAR DENTRO DE ORG POIS OS CAMṔOS DE  U_ORG, ORG, ORG_SUP APRESENTAM VALORES DIFERENTES
E PODE SER O CASO DE O CAMPO E ORG_SUP NÃO SER INFERIDO A PARTIR DE ORG. EXISTE UMA HIERARQUIA DA SEGUINTE FORMA:
UMA ORG_SUP PODE TER VÁRIAS ORG QUE TEM PODE TER VÁRIAS U_ORG. O CONTRÁRIO ACONTECE  UMA U_ORG TEM SOMENTE UMA ORG QUE TEM SOMENTE 
UMA ORG_SUP. ENTÃO CASO TENHA SOMENTE ORG O QUE DÁ PARA INFERIR DISSO É A SUA ORG_SUP. ALÉM DISSO SE RETIRARMOS O CAMPO U_ORG PERDEREMOS
MUITA INFORMAÇÃO. FICANDO COM UMA GRANULARIDADE ALTA. 


'''


