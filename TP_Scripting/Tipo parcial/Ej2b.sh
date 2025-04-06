archivo="/home/nachoperna/Documents/Sistemas-Operativos/TP_Scripting/Tipo parcial/computed.txt"

echo "fib(0)=1" >> "$archivo"
echo "fib(1)=1" >> "$archivo" # valores necesarios para los calculos

read -p "Calcular Fibonacci de numero: " -a lista # creacion de array

mayor=0
for num in "${lista[@]}"; do # se obtiene el mayor numero para iterar hasta ese maximo
	if [[ $num -gt $mayor ]]; then
		mayor=$num
	fi
done

lista=($(printf "%s\n" "${lista[@]}" | sort -n)) # se ordena la lista de menor a mayor
echo "${lista[@]}"
echo "mayor: $mayor"

j=0
for (( i = 3; i <= $((mayor+2)); i++ )); do
	if [[ $((i-2)) -eq ${lista[j]} ]]; then # si llegamos a un objetivo, se imprime su fib
		echo $(awk "NR==$((i-1))" "$archivo") # se usa i-1 porque en la linea 1 esta el fib(0)
		((j++)) # siguiente objetivo
	fi
	valor1=$(awk "NR==$((i-1))" "$archivo" | cut -d '=' -f2)
	valor2=$(awk "NR==$((i-2))" "$archivo" | cut -d '=' -f2)
	nuevo=$((valor1+valor2))
	echo "fib($((i-1)))=$nuevo" >> "$archivo"
done

rm "$archivo"