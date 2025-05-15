"""
n1 = 0
n2 = 0
n3 = 0
prom = 0

n1 = float(input("Ingrese la primera nota: "))
n2 = float(input("Ingrese la primera nota: "))
n3 = float(input("Ingrese la primera nota: "))

prom = (n1 + n2 + n3)/3

if prom >= 7:
    print("PROMOCIONADO")
elif prom >= 4 and prom < 7:
    print("REGULAR")
elif prom <4:
    print("REPROBADO")
"""

"""
for i in range(0,10,2):
    print(i)
"""

"""
# CICLO PARA
num = 0
for i in range(0,10,1):
    num = int(input(f"Ingresa el valor # {i + 1} : "))
    suma = num + i

prom = suma/10
print(prom)
# ------------------------------------------------------

# CICLO PARA CON UN ARRAY -> LISTA

numLista = [] # Lista vacía para almacenar los valores

for i in range(10):
    num = int(input(f"Ingresa el valor # {i + 1} : "))
    numLista.append(num)  # Guardamos cada número en la lista
                          # .append() -> agregar elementos a una lista  
suma = sum(numLista)
prom = suma/ len(numLista)# Calculamos el promedio correctamente
                          # len() -> función que devuelve la cantidad de elementos que hay en una estructura de datos, como listas, tuplas, cadenas de texto, diccionarios, entre otros.

print(f"El promedio de los valores ingresados es: {prom}")

# ------------------------------------------------------
suma = 0
prom = 0
i = 0
# CICLO MIENTRAS
while i < 10:
    num = int(input(f"Ingresa el valor # {i + 1} : "))
    suma += num
    i += 1

prom = suma/10
print(prom)
"""

"""
num = int(input("Ingresa un número entero: "))
while num > 0:
    print(num)
    num = num - 1
else:
    print("El ciclo ha terminado correctamente")
"""

"""
# ----------------------------------------------------------------------------------------------------------
# CICLO WHILE CON ELSE

# Escribe un programa en Python que solicite al usuario un número entero positivo. Luego, usa un ciclo while para contar regresivamente 
# desde ese número hasta 1. Si la cuenta regresiva se completa sin interrupciones, muestra un mensaje indicando que el ciclo terminó correctamente. 
# Pero si el usuario ingresa un número negativo, detén el ciclo inmediatamente con break y muestra un mensaje indicando que se detuvo prematuramente.

num = int(input("Ingresa un número entero: "))

if num < 0:
    print("Número inválido. El ciclo se detuvo prematuramente.")
else:
    while num > 0:
        print(num)
        num -= 1
    else:
        print("El ciclo ha terminado correctamente")
"""

"""
# FOR CON UNA LISTA 
frutas = ["pera", "fresas", "manzanas"]
#     i
for fruta in frutas:
    #       i
    print(fruta)
print(fruta[0]) #busca un dato de esa lista 
frutas.extend(["papaya", "cereza"]) #.extend -> Agrega varios elementos al final de la lista
print(frutas)
"""

# tupla -> se utiliza para visualizar los datos (NUNCA se modifican)
tupla = (1,2,3, "cuatro", 5.6)
print(tupla[0])

# -> https://www.pontia.tech/python-listas-tuplas-diccionario/