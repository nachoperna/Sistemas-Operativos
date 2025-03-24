contador=0
for archivo in *; do
	if [ -f "$archivo" ]; then
		nuevo_nombre=$(echo "$archivo" | tr A-Z a-z | tr ' ' '_')
		if [ "$archivo" != "$nuevo_nombre" ]; then
			mv "$archivo" "$nuevo_nombre"
			((contador++))
		fi
	fi
done

echo "Se renombraron $contador archivos."