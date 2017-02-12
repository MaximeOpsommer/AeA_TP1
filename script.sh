#!/bin/bash
rm -rf bin
mkdir bin
find -name "*.java" > sources.txt
javac -encoding utf8 -d bin/ @sources.txt
rm sources.txt
java -cp bin/ main.Main "data-mirna/ARNmessager-1.fasta" 8 false false true
gnuplot -e "set grid; plot 'data-plot/ARNmessager-1.data'; pause -1"

exit 0
