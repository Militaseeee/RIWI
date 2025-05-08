# Función para evaluar si el nompre del producto no esté vacío
def evaluar_producto_vacio(valor_ingresado: str, tipo: type):

    validar_producto = False 

    while not validar_producto:

        valor = tipo(input(valor_ingresado)) # Mostrar mensaje del userio que ingresó
        valor = valor.strip().lower()
        if valor == "":
            print("\n__________ ¡¡¡ ADVERTENCIA !!! __________")
            print(f"\nEl nombre del producto no debe quedar vacío {valor}\n")
        else:
            return valor # Retorna la función aquí si esta vacio


# Función para evaluar el precio y la cantidad donde el usuario no puede ingresar una letra
def evaluar_tipo_numerico(numero_ingresado: str, tipo: type):

    validar_num = False 

    while not validar_num:

        try:
            valor = tipo(input(numero_ingresado)) # Convertir la entrada al tipo que se necesite
            
            if valor < 0:
                print("\n_____________ ¡¡¡ ADVERTENCIA !!! _____________")
                print(f"\nEl valor debe ser mayor que cero y pusiste: {valor}\n")
            else:
                return valor  # Retorna el valor solo si es válido
        except ValueError:
            print("\n--------------------------------------------")
            print("Por favor, ingresa un valor numérico válido")
            print("--------------------------------------------\n")


# Función para agregar los productos
def agregar_productos(inventario_productos, nombre, precio, cantidad):

    productos = {
        "nombre": nombre,
        "precio": precio,
        "cantidad": cantidad
    }
    inv = {"nombre":(precio, cantidad)}
    inventario_productos.append(productos)
    print(f"\nEl producto '{nombre}' ha sido agregado con éxito.\n")
    


def menu():
    
    opc: int = 0
    intentos = 3  # Cantidad de números máximos que permite hacer el programa
    i = 0         # Contador de intentos para finalizar el programa
    comprobar: bool = True
    inventario_productos = [] # Los poductos que tendre en el inventario
        
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
            
            nombre = evaluar_producto_vacio("Ingresa el nombre del producto: ", str)
            precio = evaluar_tipo_numerico("Ingresa el precio del producto: ", float)
            cantidad = evaluar_tipo_numerico("Ingresa cuantos productos quiere llevar: ", int)
            agregar_productos(inventario_productos,nombre, precio, cantidad)
                
            print(inventario_productos)
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
                print("\n    S A L I E N D O   D E L   P R O G R A M A  \n")
                print("**************************************************")

                comprobar = False
                break

menu()
