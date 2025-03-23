read -p "ingrese caracter para reemplazar en texto: " caracter
tr a-z $caracter < "Ej 1b" > temp.tmp && mv temp.tmp "Ej 1b"