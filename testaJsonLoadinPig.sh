#!/bin/bash
count=1
while [ $count -le 100 ]
do
	eval sed -n "$count,$count"p abc > newfile.json
	pig -x local sc.pig > output.txt
	if [[ -s output.txt ]]; then
		echo "Hurah"
		else
			line=`cat newfile.json`
			echo $line >> errors
			echo $count >> error_line
	fi
	count=`expr $count + 1`
done