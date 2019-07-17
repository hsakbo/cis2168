#! /bin/bash
for ((i=0;i<10;i++))
do
java -cp ../code sortings.main > tmp
cat tmp && cat tmp >> csvAll.csv
done
