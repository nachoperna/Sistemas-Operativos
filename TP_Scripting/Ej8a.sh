read -p "Directorio: " directorio
espacio="" # delimitador de directorios
n_ant=$(echo "$directorio" | grep -o '/' | wc -l) # contabiliza profundidad
find "$directorio" -type d | while IFS= read -r sub; do # recorre subdirectorios
	if [ $(echo "$sub" | grep -o "git" | wc -l) = 0 ]; then # evita todas las carpetas .git
		n_act=$(echo "$sub" | grep -o '/' | wc -l) # contabiliza profundidad actual
		if [ $n_act -gt $n_ant ]; then # profundidad actual > profundidad anterior => tab
			for (( i = n_ant; i < n_act; i++ )); do
				espacio+=" "
			done
		elif [[ $n_act -lt $n_ant ]]; then # profundidad actual < profundidad anterior => tab menos
			for (( i = n_act; i < n_ant; i++ )); do
				espacio="${espacio% }"
			done
		fi
		carpeta=$(basename "$sub") # nos quedamos solo con el nombre de la carpeta
		echo "$espacio*$carpeta"
		n_ant=$n_act
	fi
done
echo "Busqueda finalizada"