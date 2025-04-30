# Definición e iniciador de variables
opc: int = 0
intentos = 3  # Cantidad de números máximos que permite hacer el programa
i = 0  # Contador de intentos para finalizar el programa

comprobar: bool = True


print("-----------------------------------------------------------------")
print("- - - Actividad: Desafío de calificaciones y estadísticas - - -")
print("-----------------------------------------------------------------")
print("\n Bienvenido al programa de calificaciones y estadísticas \n")
print("-----------------------------------------------------------------")


while comprobar:
    print("\n Menú: \n")
    print("1. Determinar el estado de aprobación")
    print("2. Calcular el promedio")
    print("3. Contar calificaciones mayores que un valor específico")
    print("4. Verificar y contar calificaciones específicas")
    print("5. Salir")

    opc = input("\n Ingresa que función deseas realizar: (1-5) ")

    if opc == "1":
        cal_num = 0.0
        opc1: bool = True
        intentos_cal = 3

        print("\n************************************************************")
        print("  Elegiste la opción 1: Determinar el estado de aprobación ")
        print("************************************************************")

        while intentos_cal > 0 and opc1:
            cal_num = float(input("Ingresa una calificación númerica (0 al 100): "))

            if cal_num >= 0 or cal_num <= 100:
                if cal_num >= 60:
                    print("Aprobaste la materia, FELICIDADES")
                    break
                else:
                    print("Reprobaste la materia, debes RECUPERARLA")
                    break
                    opc1 = True
            else:
                intentos_cal -= 1

                if intentos_cal > 0:
                    print("\n Error, la calificación debe estar entre 0 y 100")
                    print(f"\n Tienes {intentos_cal} intentos")
                else:
                    print("\n**********************************************")
                    print("   ¡¡¡ Se agotó la cantidad de intentos !!!")
                    print("********************************************** \n")
                    
        comprobar = False
        break

    elif opc == "2":

        cal_pro: list[str] = []
        notas = 0.0
        i = 0
        sumar:float = 0.0
        concatenar = ""
        x = 0
        suma = 0
        promedio = 0

        print("\n************************************************************")
        print("  Elegiste la opción 2: Calcular el promedio")
        print("************************************************************\n")

        notas = str(input("Ingresa las calificaciones para calcular el promedio. Sepáralas por comas (ejemplo: 2, 25,57,80,5):\n "))
        print("")

        for i in notas:
            # print(f"{notas} ciclo {i}")
            if i  != ",":
                concatenar = concatenar.__add__(i)
                # print(concatenar)
                continue
            else:
                cal_pro.append(concatenar)
                concatenar = ""
        cal_pro.append(concatenar)

        prom_int: list[float] = [float(x) for x in cal_pro]

        print(f"Las notas que ingresaste son: {prom_int}")

        for i in prom_int:
            suma += i
        
        print(suma)
        promedio = suma/ len(prom_int)

        print(promedio)

        comprobar = False
        break
    
    elif opc == "3":

        val_num = 0.0
        cal_pro: list[float] = [12.4, 20.3, 45.3, 39, 58, 64.3, 99.99, 70.1, 80]
        contador: int = 0
        x = 0

        print("\n************************************************************")
        print("Elegiste la opción 3: Contar calificaciones mayores que un valor específico (0 al 100)")
        print("************************************************************\n")

        val_num = float(input("Ingresa una nota para comparar tú nota con las notas del sistema: "))

        for i in cal_pro:
            if i >= val_num:
                print(f"\n{contador + 1}. La nota {i} es mayor que -> {val_num}")
                contador += 1

        print("\n________________________________________________")
        print(f"\n     Hay {contador} notas mayores a la nota de: {val_num}")
        print("________________________________________________\n")

        comprobar = False
        break
    elif opc == "4":
        print(
            "\n Elegiste la opción 4: Verificar y contar calificaciones específicas \n"
        )
        comprobar = False
        break
    elif opc == "5":
        print("\n . . . Saliendo del programa . . . \n")
        comprobar = False
        break
    else:
        i += 1
        if i < 3:
            print("\n**********************************************")
            print(f"      Opción inválida, tienes {intentos - i} intentos")
            print("**********************************************")
        else:
            print("\n**********************************************")
            print("   ¡¡¡ Se agotó la cantidad de intentos !!! ")
            print("********************************************** \n")

            print("**************************************************")
            print(f"\n    S A L I E N D O   D E L   P R O G R A M A  \n")
            print("**************************************************")

            comprobar = False
            break