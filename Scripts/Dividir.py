import sys
import os
import glob
import codecs
import optparse

def getNLines(N, file):
	linesList = []

	allLines = file.readlines()
	line0 = allLines[0]
	lines = [line0]
	qtdLines = N
	i = 1
	for line in allLines[1:]:
		lines.append(line)
		if i == qtdLines:
			linesList.append(lines)
			lines = [line0]
			qtdLines += N
		i += 1

	linesList.append(lines)
	return linesList

def writeLines(lines, file):
	for line in lines:
		file.write(line)

def main():

	parser = optparse.OptionParser()
	parser.add_option('-f', '--folder', type='string', dest='folderName', default='./', help='Pasta de arquivos .csv que serão divididos')
	parser.add_option('-l', '--lines', type='int', dest='qtdLines', default=300000, help='Quantidade máxima de linhas em cada arquivo após a divisão')
	parser.add_option('-e', '--encoding', type='string', dest='encoding', default='iso8859_9', help='Codificação dos arquivos .csv')

	(options, args) = parser.parse_args()

	for fileName in glob.glob(options.folderName + '*.csv'):
		print("\t[->] Abrindo arquivo {}".format(fileName))
		file = codecs.open(fileName, mode='r', encoding=options.encoding)
		lineList = getNLines(options.qtdLines, file)
		i = 1
		for lines in lineList:
			print("\t[->] Salvando arquivo dividido {}".format(i))
			newFile = codecs.open(options.folderName + "Result/" + fileName.split('.csv')[0] + '_div' + str(i) + '.csv', mode='w', encoding='iso8859_9')
			writeLines(lines, newFile)
			newFile.close()
			i += 1

if __name__ == '__main__':
	main()