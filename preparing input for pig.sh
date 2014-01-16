c="{"
for folder1 in `hadoop fs -ls dir | awk '{print $8}'`  #only gets the folder name inside the folder dir
do
for folder2 in `hadoop fs -ls $folder1 | awk '{print $8}'` #only gets the folder name inside the folder folder1
do
for file in `hadoop fs -ls $folder2 | grep part | awk '{print $8}'` #only gets the file names matching 'part' in their prefix. 
do
file=${file/#\/user\/msa\/}  #adding /user/msa to the prefix of each file
c=$c$file, #appending the modified file variable (containing file path) to c 
done
done
done
c=${c/%,}  #replacing suffix , with nothing
c="$c}"
echo $c
pig -f dir.pig -param "input=$c"
