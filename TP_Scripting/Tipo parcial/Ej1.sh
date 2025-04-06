read -p "Ruta del archivo de texto: " ruta
archivo="$ruta/AuxiliarEj1.txt"
> "$archivo" # vacia archivo auxiliar para nuevas ejecuciones

masUsada=''
mayor_oc=0
for palabra in $(cat "$ruta/TextoEj1.txt" | tr A-Z a-z | tr -d '.,():'); do # iteramos por cada palabra dentro del contenido del texto sin tener en cuenta caracteres especiales
	if [ $(grep -o "$palabra" "$archivo" | wc -l) = 0 ]; then # si es una palabra nueva en nuestro archivo auxiliar la registramos
		ocurrencias=$(grep -o "$palabra" "$ruta/TextoEj1.txt" | wc -l) # contamos cantidad de veces que aparece la palabra en nuestro texto
		echo "$palabra-$ocurrencias" >> "$archivo" # nueva linea en auxiliar donde tenemos: palabra-ocurrencias
		if [ $ocurrencias -gt $mayor_oc ]; then # nos quedamos con la palabra mas usada y sus ocurrencias para usar luego en TF
			mayor_oc=$ocurrencias
			masUsada=$palabra
		fi
	fi
done
echo "La palabra con mayor ocurrencias es '${masUsada}' con $mayor_oc ocurrencias."
linea=1 # variable para poder modificar luego solo la linea que estamos leyendo en ese momento
for palabra in $(cat "$archivo"); do
	pal=$(echo "$palabra" | cut -d '-' -f1)
	valor=$(echo "$palabra" | cut -d '-' -f2)
	TF=$(echo "scale=2;$valor/$mayor_oc" | bc) # usamos funcion de calculadora en bash para obtener decimales
	sed -i "${linea}c\TF($pal) = $TF" "$archivo" # sobreescribimos la misma linea donde estamos
	((linea++))
done

cat "$archivo" # mostramos resultado por consola
rm "$archivo" # borramos archivo temporal