eliminados="$HOME/nacho/Documents/Sistemas-Operativos/TP_Scripting/'Ej 3.log'"
find "$HOME" -type f -size +100k | while read -r archivo; do
	echo "Archivo: $archivo"
	echo "Opciones: Eliminar (e), Comprimir (c), Saltar (s)"
	read -p "Opcion: " op

	case $op in
		e)
			rm "$archivo"
			echo "($(date)) $archivo" >> $eliminados
			echo "Archivo eliminado"	;;
		c)
			gzip "$archivo"
			echo "Archivo comprimido" ;;
		s)
			echo "Archivo saltado" ;;
		*)
			echo "Opcion invalida" ;;
	esac
done

echo "Todos los archivos eliminados han sido guardados en $eliminados"