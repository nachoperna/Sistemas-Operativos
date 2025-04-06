read -p "Ruta del archivo de texto: " texto
read -p "Numero de impresora: " nro_imp
read -p "Tipo de pagina: " tipo_pag

impresoras=$"/etc/cups/printers.conf" # toda la lista de impresoras del sistema
impresora=$(cat "$impresoras" | grep "$nro_imp:$tipo_pag*")

if [[ -z "$impresora" ]]; then # pregunta si la longitud de la variable impresora es cero
	echo "No se encontro la impresora buscada en el sistema."
else
	filas=$(echo "$impresora" | cut -d ':' -f3) # nos quedamos con el tercer campo correspondiente a <filas>
	caracteres=$(echo "$impresora" | cut -d ':' -f4) # nos quedamos con el cuarto campo correspondiente a <cols>

	carac_texto=$(cat "$texto" | wc -c) # caracteres totales del texto
	carac_imp=$((filas*caracteres)) # caracteres totales por pagina de la impresora

	echo "El texto tiene $carac_texto caracteres y se pueden imprimir $carac_imp caracteres por pagina."
	paginas_totales=$(echo "scale=2;$carac_texto/$carac_imp" | bc) # calculo con 2 decimales

	echo "Se necesitan $paginas_totales paginas para imprimir el contenido del archivo"
fi