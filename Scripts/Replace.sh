#!/bin/bash

#nomes das pastas
pastas=$(ls -l | grep '^d' | sed -r "s/ +/ /g" |cut -f9 -d' ')
for p in $pastas
do
	#nome das pastas de p
	subpastas=$(ls -l $p| grep '^d' | sed -r "s/ +/ /g" |cut -f9 -d' ')

	for sub in $subpastas
	do
		#nomes dos arquivos dentro da pasta corrente de p
		nomes=$(ls -l $p/$sub | grep 'csv' | sed -r "s/ +/ /g" |cut -f9 -d' ')
		for f in $nomes
		do
			cat $p/$sub/$f | sed "s/\t/#/g" > aux.csv
			cat aux.csv > $p/$sub/$f

		done
	done
done


rm aux.csv
