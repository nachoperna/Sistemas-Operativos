read -p "Directorio: " directorio
espacio=""
cant_ant=$(echo "$directorio" | grep -o '/' | wc -l)
find "$directorio"/* | while IFS= read -r sub; do
	if [ $(echo "$sub" | grep -o "git" | wc -l) = 0 ]; then
		cant_act=$(echo "$sub" | grep -o '/' | wc -l)
		if [ $cant_act -gt $cant_ant ]; then
			for (( i = cant_ant; i < cant_act; i++ )); do
				espacio+=" "
			done
		elif [ $cant_act -lt $cant_ant ]; then
			for (( i = cant_act; i < cant_ant; i++ )); do
				espacio="${espacio% }"
			done			
		fi
		file=$(basename "$sub")
		echo "$espacio*$file"
		cant_ant=$cant_act
	fi
done