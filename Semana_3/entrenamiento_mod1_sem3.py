def agregar_productos(nombre, precio, cantidad):
    
    inventario_productos = []
    
    if nombre == "":
        print("\n__________ ¡¡¡ ADVERTENCIA !!! __________")
        print(f"\nEl nombre del producto no debe quedar vacio {nombre}\n")
        return
    
    if precio < 0:
        print("\n__________ ¡¡¡ ADVERTENCIA !!! __________")
        print(f"\nEl número de precio debe ser mayor que {precio}\n")
        return # Detiene la función aquí
    
    if cantidad < 0:
        print("\n__________ ¡¡¡ ADVERTENCIA !!! __________")
        print(f"\nEl número de cantidad debe ser mayor que {cantidad}\n")
        return
    
    productos = {
        "nombre": nombre,
        "precio": precio,
        "cantidad": cantidad
    }
    inventario_productos.append(productos)
    
    print(inventario_productos)
    return inventario_productos

def menu():
    
    opc: int = 0
    intentos = 3  # Cantidad de números máximos que permite hacer el programa
    i = 0         # Contador de intentos para finalizar el programa
    comprobar: bool = True
        
    print("-----------------------------------------------------------------")
    print("- - - Actividad: Gestión de inventarios con funciones y colecciones - - -")
    print("-----------------------------------------------------------------")
    print("\n Bienvenido al programa de la tienda RIWI EXPRESS \n")
    print("-----------------------------------------------------------------")

    while comprobar:
        print("\n Menú: \n")
        print("1. Añadir productos:")
        print("2. Consultar productos")
        print("3. Actualizar precios")
        print("4. Eliminar productos")
        print("5. Calcular el valor total del inventario")
        print("6. Salir")
        
        opc = input("\nIngresa que función deseas realizar: (1-6) ")
        
        if opc == "1":
            
            print("\n*****************************************")
            print("  Elegiste la opción 1: Añadir productos ")
            print("*****************************************\n")
            
            
            
            nombre = str(input("Ingresa el nombre del producto: "))
            precio = float(input("Ingresa el precio del producto: "))
            cantidad = int(input("Ingresa cuantos productos quiere llevar:"))
            agregar_productos(nombre, precio, cantidad)
                

            # comprobar = False
            continue
                    
        elif opc == "2":
            print("2")
            comprobar = False
            break
        elif opc == "3":
            print("3")
            comprobar = False
            break
        elif opc == "4":
            print("4")
            comprobar = False
            break
        elif opc == "5":
            print("5")
            comprobar = False
            break
        elif opc == "6":
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

menu()