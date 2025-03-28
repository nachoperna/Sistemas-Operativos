read -p "Ingrese la ruta del directorio Java: " directorio # usuario ingresa ruta por input
cantidad=$(find "$directorio" -type f -name "*.java" | wc -l) # encuentra todos los archivos de la ruta cuyo nombre termine en ".java" y contabiliza todas las lineas con wc
echo "Cantidad de archivos .java encontrados: $cantidad"
