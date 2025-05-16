# EJERCICIO 1
# imprimir numeros pares e impares 
# - usando for imprime los primeros 20 numeros pares
# - usando while, imprime los primeros 15 numeros
"""
par:list[int] = []
impar:list[int] = []
i:int = 0
contador=0

for i in range(2, 41, 1):
    if i % 2 == 0: 
        par.append(i)

print(f"Los numeros pares son: {par}")

while contador < 30:
    contador += 1
    if contador % 2 != 0:
        impar.append(contador)
        
print(f"Los numeros impares son: {impar}")
"""

# EJERCICIO 2
# Pide al usuario un número n y cálcula la suma de los primeros n
# números naturales usando for y luego con while
"""
contador:int = 0 
n = 1
i:int = 0
con_while = 0

n = int(input("Ingresa un número:"))

if n >= 0:
    for i in range(0, n + 1):
        contador += i
else:
    print(f"{n} NO es un número natural")

print(f"La suma de los primeros {n} números naturales es: {contador}")

if n >= 0:
    i = 0
    while i <= n:
        con_while += i
        i += 1
else:
    print(f"{n} NO es un número natural")

print(f"La suma de los primeros {n} números naturales es: {con_while}")
"""

# EJERCICIO 3
# Escribe un programa que calcule el factorial de un número dado 
# usando ambos bucles
"""
resultado = 1
resultadoW = 1
incr = 1

n = int(input("Introduce un número para calcular su factorial: "))

for i in range(1, n + 1):
    resultado *= i
print(f"El factorial de {n} es: {resultado}")

while incr <= n:
    resultadoW *= incr
    incr += 1
print(f"El factorial de {n} es: {resultadoW}")
"""

# EJERCICIO 4
# vamos a imprimir el siguiente patrón usando bucles for y while
# *
# **
# ***
# ****
"""
contador = 0
sim = ""
i = 0
simW= ""

for i in range(0,4):
    sim += "*"
    print(sim)

print("")

while contador < 4:
    contador += 1
    simW += "*"
    print(simW)
"""

# EJERCICIO 5
# vamos a imprimir el siguiente patrón usando bucles for y while
# ****
# ***
# **
# *
"""
sim = "*"
simW = "*"
contador = 4

for i in range(4, 0, -1):
    print(sim * i)

print("")

while contador > 0:
    print(simW)
    simW += "*"
    contador -= 1
"""

# EJERCICIO 6
# Usando while, simule un cajero donde el usuario puede:
# - Ver su saldo
# - Depositar dinero
# - Retirar dinero (Validando que no exceda el saldo)
# - Salir del programa

comprobar:bool = True
opc = 0
saldo:int = 1000000
salir = 0
pregunta = 0
retirar= 0
intentos = 3 
i = 0

while comprobar:

    print("\nBienvenidos al cajero \n")

    print("1. Ver su saldo")
    print("2. Depositar dinero")
    print("3. Retirar dinero")
    print("4. Salir del programa")

    opc = int(input("\nSeleccione la opción que deseas realizar: "))

    if opc == 1:
        print("\nLa opción que seleccionaste fue: Ver su saldo")

        print(f"\nSu saldo actual es: {saldo}\n")

        salir = int(input("¿Deseas salir del sistema? (1-si / 2-no)\n"))

        if salir == 1:
            print("Saliendo del programa ...")
            comprobar = False
            break
        elif salir == 2:
            comprobar = True
        else:
            print("Opción invalida")
 
    elif opc == 2:
        print("\nLa opción que seleccionaste fue: Depositar dinero\n")

        pregunta = float(input("¿Cuánto deseas depositar? \n"))

        saldo = pregunta + saldo
        print("****************************")
        print(f"Ahora tienes: {saldo}")
        print("****************************")

    elif opc == 3:
        print("\nLa opción que seleccionaste fue: Retirar dinero\n")

        print(f"Actualmente tienes: {saldo}")

        retirar = float(input("\n¿Cuánto deseas retirar? \n"))

        if retirar <= saldo:
            saldo = saldo - retirar
            print("****************************")
            print(f"Ahora tu saldo es: {saldo}")
            print("****************************")

            salir = int(input("\n¿Deseas salir del sistema? (1-si / 2-no)\n"))

            if salir == 1:
                print("Saliendo del programa ...")
                comprobar = False
                break
            elif salir == 2:
                comprobar = True
            else:
                print("Opción invalida")
        else:
            print("\nNo puedes retirar, tienes menos dinero que el que colocaste\n")

            salir = int(input("¿Deseas salir del sistema? (1-si / 2-no)\n"))

            if salir == 1:
                print("Saliendo del programa ...")
                comprobar = False
                break
            elif salir == 2:
                comprobar = True
            else:
                print("Opción invalida")

    elif opc == 4:
        print("\nLa opción que seleccionaste fue: 4. Salir del programa")

        salir = int(input("¿Estas seguro en salir del sistema? (1-si / 2-no)\n"))

        if salir == 1:
            print("SALIENDO ....")
            comprobar = False
            break
        elif salir == 2:
            comprobar = True
        else:
            print("Opción invalida")
    else:
        print("Opción INVALIDA")
        i += 1

        if i < 3:
            print("\n**********************************************")
            print(f"      Opción inválida, tienes {intentos - i} intentos")
            print("**********************************************")
        else:
            print("\n**********************************************")
            print("   ¡¡¡ Se ha bloqueado la cuenta !!! ")
            print("********************************************** \n")

            print("*********************")
            print("SALIENDO DEL PROGRAMA")
            print("*********************")

            comprobar = False
            break

comprobar = False
