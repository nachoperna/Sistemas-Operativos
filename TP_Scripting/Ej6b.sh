read -p "Ingrese la ruta del directorio Java: " directorio
cantidad=0
cantidad=$(find "$directorio" -name "*.java" | grep -c "import*")
echo "Cantidad de importaciones de clases: " $cantidad