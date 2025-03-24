# Evitar borrado de archivo luego de trabajar sobre el mismo
	tac file > temp.tmp && mv temp.tmp file
	
# Comandos simples
	ls # lista nombres de archivos en directorio actual
	who # lista los usuarios actualmente conectados
	date # muestra el tiempo y hora actual
	pwd # muestra la ruta del directorio actual
	df # informacion de uso de disco
	ifconfig # interfaces de conexion
	dmesg # mensajes desde dispositivos
	lspci # dispositivos conectados

# Recorrido de archivos
	cd # cambiar de directorio (default HOME)
	cd . # se mantiene en el directorio actual
	cd .. # directorio padre
	ls . # lista archivos en el directorio actual
	ls .. # lista archivos en el directorio padre
	ls -l # formato largo, incluye fecha, dueño y permisos
	ls -a # muestra todos los archivos incluido ocultos
	ls -A # omite las entradas . y ..
	ls -F # incluye caracter especial para indicar el tipo de archivo
	ls -1 # lista un archivo por linea
	ls -R # lista todos los archivos a los que pueda alcanzar de forma RECURSIVA.
	ls [a-z]* # lista todos los archivos cuyo nombre coincida con el rango especificado
	ls [!0-9]* # lista todos los archivos cuyo nombre NO coincide con el rango
	basename source # imprime la parte final de la ruta de un archivo
	dirname source # imprime la ruta sin la parte final
	du # tamañó de directorio
	mkdir # crear directorio
	stat # muestra el estado actual del archivo
	touch # cambia la fecha de un archivo o crea un archivo en blanco
	cat # concatenar archivos e imprimirlos por terminal

# Copiar archivos
	cp [options] source dest # copia un archivo a una direccion especifica
	cp [options] source... dest # copia varios archivos a una direccion especifica

# Borrar archivos
	rm [options] names... # se pueden borrar permanentemente varios archivos separados por espacio
	rmdir source # borra una carpeta solo si esta vacia 
	rm -r source # borra una carpeta y todo su contenido

# Mover archivos
	mv source1, source2 dest
	mv file.txt file1.txt # sirve para renombrar archivos

# Permisos de archivos
	# salida: [permisos_dueño|permisos_grupo|permisos_otros] [dueño] [grupo] [tamaño] [ultima modificacion] [nombre de archivo]
	- # archivo
	d # directorio
	r # permiso de lectura
	w # permiso de escritura
	x # permiso de ejecucion
	chmod # cambio de permisos de archivos
	chmod [ugoa][+-=][rwx]
	u #user
	g #group
	o #other
	a #all
	+ # agregar permiso
	- # quitar permiso
	= # setear permiso
	chmod a+x ej.sh # agrega permiso de ejecucion al archivo ej a todos los usuarios

# Busqueda de texto
	grep [options] regexp [files] # busca un patron en textos. Si no se indican archivos se toma la entrada estandar
		-c # muestra solo la cantidad de lineas encontradas con ese patron
		-h # no muestra nombre de archivos
		-l # muestra nombres de archivos donde encontro el patron pero no la linea
		-n # muestra los numeros de linea donde encontro el patron
		-v # muestra todas las lineas que no coinciden con el patron
	
	find -name "*.txt" # encontrar todos los archivos que tengan extension .txt
	find source -size +1M # encuentra todos los archivos que pesen mas de 1 megabyte.
	
	# Atajos regexp
		. # coincide todo
		* # permite cualquier cadena de caracteres
		? # permite cualquier caracter en ese espacio
		+ # coincide si el caracter a su izquierda aparece mas de una vez en el patron
		{n} # coincide si el patron aparece n veces en el texto
		{n,m} # coincide si el patron aparece entre n y m veces en el texto
		{n,} # conicide si el patron aparece por lo menos n veces
		[] # coincide si se encuentra alguna combinacion de caracteres entre los []
		[A-Z] # siguiente caracter debe estar en mayuscula
		[a-z] # siguiente caracter debe estar en minuscula
		[0-9] # siguiente caracter debe ser un numero en ese rango
		| # elegir entre patrones para buscar 
		^ # coincide si el texto comienza con el patron
		$ # coincide si el texto termina con el patron

# Mostrar contenido
	head source # muestra las primeras 10 lineas por default
	tail source # muestra las ultimas 10 lineas por default
	head tail source # muestra las primeras y ultimas 10 lineas
		-n # muestra n lineas
		-nc # muestra n caracteres
		+n # muestra empezando por la linea n
		+nc # muestra empezando por el caracter n
		-r # muestras las lineas en orden inverso
		-f # no cierra la ejecucion del comando (se puede actualizar la informacion si se actualiza el archivo)
	cut options [files] # corta una parte de la entrada que recibe
	cut -c1-10 # muestra los primeros 10 caracteres
	cut -d: -f1 # corta la entrada hasta la aparicion del primer : y se queda con la primer parte
	cut -f1 # corta la entrada en la aparicion del primer tab y se queda con la primer parte
	cut -d: -f2,3 # corta la entrada cuando aparece un : y se queda con el segundo campo tercera columna
	paste [options] files # une archivos horizontalmente e imprime el resultado
	sort [options] [files] # ordena la salida en orden alfabetico por default
	sort '0'<'9'<'A'<'Z'<'a'<'z' file # ordena primero simbolos, numeros, mayusculas y minusculas
	uniq # remueve duplicados
	tr A-Z a-z # no recibe archivos como parametros, usado en pipe, traduce caracteres
	tr -d a # elimina todas las ocurrencias de la letra a en el texto del pipe
	tr -d '\n' # remueve saltos de linea
	wc -l file.txt # contabiliza las lineas del archivo
	wc -w file.txt # contabiliza las palabras
	wc -c file.txt # contabiliza los caracteres
	tac file # invierte las lineas de un archivo 

# Redireccion de salidas/entradas
	ls > lsout.txt # imprime la salia en el archivo indicado (si no existe lo crea)
	sort < nums.txt # toma como entrada el contenido del archivo y la salida es por terminal
	tr a-z A-Z < letter > rudeletter # toma como entrada el contenido de letter, lo traduce y redirecciona la salida a rudeletter
	ls >> lsout.txt # concatena el contenido al final del archivo
	ls | sort # ejecuta comandos de izquierda a derecha
	tee file # permite redirigir entrada hacia uno o mas archivos

# Variables de Shell
	$PWD # directorio actual
	$PATH # lista de lugares para mirar comandos
	$HOME 
	$IFS # separador interno de campos
	$TERM # tipo de terminal usada
	$HISTFILE # historial de comandos usados

# Variables de usuario
	# Globales
		var1="abc" # la asignacion no debe contener espacios o no funciona
		var2=1
		concat="$var1 $var2 cba"
		echo $concat # el resultado sera: abc1cba
	# Locales
		local var1="abc" # usadas en funciones

# Funciones
	var1="abc"
	function fun{
		local var1="cba"
		echo $var1 # imprime cba
	}
	echo $var1 # imprime abc

# Condiciones
	# Brackets
		if [ $x = 3 ]; then # siempre se debe mantener el espacio entre corchetes
			echo "iguales" 
		fi
		-a, && # AND
		-o, || # OR
	# Alternativa
		if test $x = 3; then
	# Numeros
		-eq # evalua igualdad
		-gt # mayor que
		-ge # mayor o igual que
		-lt # menor que
		-le # menor o igual que
		-ne # no igual que
	# Palabras
		== # igualdad
		!= # desigualdad
	# Archivos
		-a file # evalua si existe el archivo
		-d dir # evalua si existe el directorio
		-s file # evalua si existe el archivo y su tamaño es mayor a 0
	# Variables
		-n "$var" # evalua si la variable no esta vacia
		-z "$var" # evalua si la variable esta vacia

# Loops
	# For each
		for i in $ls; do
			echo item: $i
		done
	# For
		for ((i=0 ; i<=10 ; i++)); do
			echo repeat
		done
	# While / Until
		counter=0
		while [ $counter -lt 10 ]; do
			echo contador es $counter
			counter--
		done

# Secuencias
	for i in {0..9} do # las llaves expanden el contenido hasta cumplir con el ranog
		echo iteracion $i
	done

# Ecuaciones matematicas
	$((expresion))
	$[expresion]
	x=3;y=4;echo $((x+y));echo $[x+y]
	x=2**3 # imprime la potencia 2³
	let i++ = let i=i+1 = i=$((i+1))

