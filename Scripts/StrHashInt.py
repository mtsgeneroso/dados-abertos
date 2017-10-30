import glob
import optparse
import os
import pandas as pd

def str2int(_str, mapeamento):
	result = ''
	for c in _str:
		result += mapeamento[c]
	return int(result)

def main():

	parser = optparse.OptionParser()
	parser.add_option('-f', '--folder', type='string', dest='folderName', default='./', help='Pasta de arquivos .csv que serão divididos')
	parser.add_option('-e', '--encoding', type='string', dest='encoding', default='iso8859_9', help='Codificação dos arquivos .csv')
	parser.add_option('-s', '--sep', type='string', dest='separador', default='\t', help='Separador dos arquivos .csv')
	
	(options, args) = parser.parse_args()

	mapeamento = {}
	
	n = 10
	for i in range(ord('a'), ord('z')+1):
		mapeamento[chr(i)] = str(n)
		n += 1 

	for i in range(ord('A'), ord('Z')+1):
		mapeamento[chr(i)] = str(n)
		n += 1

	for i in range(ord('0'), ord('9')+1):
		mapeamento[chr(i)] = str(n)
		n += 1

	for fileName in glob.glob(options.folderName + '*.csv'):
		print("\t[->] Abrindo arquivo {}".format(fileName))
		data = pd.read_csv(fileName, sep=options.separador, header = 0, encoding=options.encoding)
		data['Id Favorecido'] = 0
		data['Id Ação'] = 0
		for i in range(len(data)):
			if not isdigit(data['Código Favorecido']):
				data['Id Favorecido'] = str2int(data['Código Favorecido'][i], mapeamento)
			else:
				data['Id Favorecido'] = int(data['Código Favorecido'][i])

			if not isdigit(data['Código Ação']):
				data['Id Ação'] = str2int(data['Código Ação'][i], mapeamento)
			else:
				data['Id Ação'] = int(data['Código Ação'][i])

			print('ARQUIVO: {} -> {}% concluído...'.format(fileName, (i*100)/len(data)))
			os.system('clear')

		data.to_csv('Result/{}'.format(fileName), sep=',', encoding='utf-8', index=False)

if __name__ == '__main__':
	main()