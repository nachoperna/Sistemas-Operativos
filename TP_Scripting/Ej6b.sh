read -p "Ingrese la ruta del directorio Java: " directorio
total=0

# Iterar directamente con `for`
for archivo in "$directorio"/*.java; do
    if [ -f "$archivo" ]; then
        cant_imports=$(grep -c "^import" "$archivo")
        ((total += cant_imports))
    fi
done

echo "Cantidad de importaciones de clases: $total"