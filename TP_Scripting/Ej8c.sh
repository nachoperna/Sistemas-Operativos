read -p "Directorio: " directorio
read -p "Profundidad: " profundidad
espacio=""
cant_ant=$(echo "$directorio" | grep -o '/' | wc -l)
find "$directorio" -maxdepth $profundidad | while IFS= read -r sub; do
	if [ $(echo "$sub" | grep -o "git" | wc -l) = 0 ]; then
		cant_act=$(echo "$sub" | grep -o '/' | wc -l)
		if [ $cant_act -gt $cant_ant ]; then
			for (( i = $cant_ant; i < $cant_act; i++ )); do
				espacio+=" "
			done
		elif [ $cant_act -lt $cant_ant ]; then
			espacio=${espacio% }
		fi
		carpeta=$(basename "$sub")
		echo "$espacio*$sub"
		cant_ant=$cant_act
	fi
done