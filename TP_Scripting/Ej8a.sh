read -p "Directorio: " directorio
espacio=""
n_ant=$(echo "$directorio" | grep -o '/' | wc -l)
find "$directorio" -type d | while IFS= read -r sub; do
	if [ $(echo "$sub" | grep -o "git" | wc -l) = 0 ]; then
		n_act=$(echo "$sub" | grep -o '/' | wc -l)
		if [ $n_act -gt $n_ant ]; then
			for (( i = n_ant; i < n_act; i++ )); do
				espacio+=" "
			done
		elif [[ $n_act -lt $n_ant ]]; then
			for (( i = n_act; i < n_ant; i++ )); do
				espacio="${espacio% }"
			done
		fi
		carpeta=$(basename "$sub")
		echo "$espacio*$carpeta"
		n_ant=$n_act
	fi
done
echo "Busqueda finalizada"