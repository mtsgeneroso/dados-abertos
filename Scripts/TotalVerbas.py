
# -*- coding: UTF-8 -*-
import csv
import pandas as pd
import numpy as np

# O que deve ser verificado é se o atributo TOTAL DE VERBAS INDENIZATÓRIAS = VERBAS INDENIZATÓRIAS REGISTRADAS EM SISTEMAS DE PESSOAL - CIVIL (R$) + VERBAS INDENIZATÓRIAS REGISTRADAS EM SISTEMAS DE PESSOAL - MILITAR (R$)?
# Ou seria TOTAL DE VERBAS INDENIZATÓRIAS = VERBAS INDENIZATÓRIAS REGISTRADAS EM SISTEMAS DE PESSOAL - CIVIL (R$)?
# Ou seria TOTAL DE VERBAS INDENIZATÓRIAS = VERBAS INDENIZATÓRIAS REGISTRADAS EM SISTEMAS DE PESSOAL - MILITAR (R$)?

# ano ='2016'
# listdias = ['31', '29', '31', '30', '31', '30', '31', '31', '30', '31', '30', '31' ]
# listmes = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ]

ano ='2017'
listdias =['31', '28', '31']
listmes = ['01', '02', '03']

m=0

for d in listdias:
	data = pd.read_csv('/home/daiane.oliveira/Documentos/Dados Abertos/dados_servidores/'+ano+'/'+ano+listmes[m]+'_Servidores/'+ano+listmes[m]+d+'_Remuneracao.csv', delimiter="\t", encoding="ISO-8859-9")

	print (ano+listmes[m]+d)

	for i, row in data.iterrows():
		total = row['TOTAL DE VERBAS INDENIZATÓRIAS (R$)(*)']
		total = total.replace(',','.')
		civil =row['VERBAS INDENIZATÓRIAS REGISTRADAS EM SISTEMAS DE PESSOAL - CIVIL (R$)(*)']
		civil = civil.replace(',','.')
		militar = row['VERBAS INDENIZATÓRIAS REGISTRADAS EM SISTEMAS DE PESSOAL - MILITAR (R$)(*)']
		militar = militar.replace(',','.')

		if(float(total) != float(civil) + float(militar)):
			print(data.loc[i,:])
	m+=1


# Se existir algum caso que o total de verbas indenizatórias seja diferente da soma entre verbas indenizatórias registradas em sistemas de pessoal - civil e 
# verbas indenizatórias registradas em sistemas de pessoal - militar este caso será mostrado.

# O Script foi executado para todos os meses de 2016 e 2017 e como resultado NÃO mostrou nenhum caso, ou seja, podemos concluir que:
# 	o atributo TOTAL DE VERBAS INDENIZATÓRIAS = VERBAS INDENIZATÓRIAS REGISTRADAS EM SISTEMAS DE PESSOAL - CIVIL (R$) + VERBAS INDENIZATÓRIAS REGISTRADAS EM SISTEMAS DE PESSOAL - MILITAR (R$)