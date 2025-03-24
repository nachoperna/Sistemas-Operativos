read -p "Ingrese los nombres de los archivos a procesar (separados por espacios): " -a archivos

for archivo in "${archivos[@]}"; do
    if [ -f "$archivo" ]; then
        echo "Procesando archivo: $archivo"
        ext=$(echo "$archivo" | cut -d '.' -f 2-)
        case "$ext" in
            gz) 
                echo "Descomprimiendo .gz"
                gz "$archivo";;
            bz)
                echo "Descomprimiendo .bz"
                bz "$archivo";;
            zip)
                echo "Descomprimiendo .zip"
                unzip "$archivo";;
            tar)
                echo "Descomprimiendo .tar"
                tar -xvf "$archivo";;
            *)
                echo "Extension desconocida. No se descomprime el archivo";;
        esac
    else
        echo "El archivo '$archivo' no existe."
    fi
done

echo "Fin."