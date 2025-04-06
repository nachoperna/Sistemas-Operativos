read -p "Palabra clave para retornar procesos de CPU mayor a media hora: " palabra

if [[ -z "$palabra" ]]; then # si el parametro es vacio (longitud zero)
	ps -A | while IFS= read -r linea; do
		tiempo=$(echo "$linea" | cut -d " " -f3)
		horas=$(echo "$tiempo" | cut -d ":" -f1)
		minutos=$(echo "$tiempo" | cut -d ":" -f2)
		if [[ $minutos -gt 30 || $horas -gt 1 ]]; then
			echo "$linea"
		fi
	done
else
	ps -A | grep -i "$palabra" | while IFS= read -r linea; do # encuentra todos los procesos que incluyan al parametro sin distincion de mayusculas
		tiempo=$(echo "$linea" | cut -d " " -f3)
		horas=$(echo "$tiempo" | cut -d ":" -f1)
		minutos=$(echo "$tiempo" | cut -d ":" -f2)
		if [[ $minutos -gt 30 || $horas -gt 1 ]]; then
			echo "$linea"
		fi
	done
fi

