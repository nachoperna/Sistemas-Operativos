read -p "Texto: " texto
read -p "Numero de impresora: " nro_imp
read -p "Tipo de pagina: " tipo_pag

chmod +r $"/etc/cups/printers.conf" # permiso de lectura
impresoras=$"/etc/cups/printers.conf" # toda la lista de impresoras del sistema
impresora=$(cat "$impresoras" | grep "$nro_imp:$tipo_pag*")
filas=$(echo "$impresora" | cut -d ':' -f2,3) # nos quedamos con el tercer campo correspondiente a <filas>
caracteres=$(echo "$impresora" | cut -d ':' -f2,4) # nos quedamos con el cuatro campo correspondiente a <cols>

carac_texto=$(cat "$texto" | wc -c) # caracteres totales del texto
carac_imp=$((filas*caracteres)) # caracteres totales por pagina de la impresora

paginas_totales=$(echo "scale=2;$carac_texto/$carac_imp" | bc)

echo "Se necesitan $paginas_totales para imprimir el contenido del archivo"