read -p "Proceso: " proceso

if [ -n "$proceso" ]; then
	ps -A | grep "$proceso" | tr -s ' ' >> temp.tmp # usa tr -s para juntar espacios duplicados
else
	echo "proceso nulo"
	ps -A | tr -s ' ' | tail -n +2 >> temp.tmp # usa tail para saltearse la cabecera del comando
fi

cat "$PWD/temp.tmp" | while IFS='\n' read -r linea; do
		horas=$(echo "$linea" | cut -d ' ' -f4 | cut -d ':' -f1)
		minutos=$(echo "$linea" | cut -d ' ' -f4 | cut -d ':' -f2)
		if [ $horas -ge 1 -o $minutos -gt 10 ]; then
			echo "$linea"
		fi
	done

rm temp.tmp