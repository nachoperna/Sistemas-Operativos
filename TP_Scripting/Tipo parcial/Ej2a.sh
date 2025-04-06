archivo="/home/nachoperna/Documents/Sistemas-Operativos/TP_Scripting/Tipo parcial/computed.txt"

echo "fib(0)=1" >> "$archivo"
echo "fib(1)=1" >> "$archivo" # valores necesarios para los calculos

read -p "Calcular Fibonacci de numero: " numero

for (( i = 3; i <= $((numero+1)); i++ )); do # comienza desde 3 porque la primer linea de los archivos es la 1, por lo tanto tambien se debe iterar hasta numeros+1 para llegar al objetivo
	n=$((i-1))
	valor1=$(awk "NR==$n" "$archivo" | cut -d "=" -f2) # se usa awk para obtener el contenido de la linea n del archivo y quedarnos con su valor fib. NR=NumberRegister
	n=$((i-2))
	valor2=$(awk "NR==$n" "$archivo" | cut -d "=" -f2)
	nuevo=$((valor1 + valor2))
	echo "fib($((i-1)))=$nuevo" >> "$archivo"
done
cat "$archivo"
rm "$archivo"