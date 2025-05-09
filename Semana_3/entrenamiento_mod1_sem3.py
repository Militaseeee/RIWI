# Función para evaluar si el nompre del producto no esté vacío
def evaluar_producto_vacio(valor_ingresado: str, tipo: type):

    validar_producto = False 

    while not validar_producto:

        valor = tipo(input(valor_ingresado)) # Mostrar mensaje del usuario que ingresó
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


def ver_lista_productos(inventario_productos):
    
    print("------------------------------------------------------------")
    print("-> PRODUCTOS EN EL INVENTARIO: \n")
    for nombre, (precio, cantidad) in inventario_productos.items():
        precio, cantidad = inventario_productos[nombre]
        print(f" - {nombre} | Precio = {precio} | Cantidad = {cantidad}")
    print("------------------------------------------------------------")
    
    
# Función para agregar los productos
def agregar_productos(inventario_productos, nombre, precio, cantidad):

    inventario_productos[nombre] = (precio, cantidad)
    print("\n------------------------------------------------------------------------------------")
    print("-> AGREGAR: ")
    print(f"\nEl producto '{nombre}' con precio '{precio}' y con una cantidad de '{cantidad}' ha sido agregado con éxito")
    print("------------------------------------------------------------------------------------")
     
     
def consultar_productos(inventario_productos, nombre):
    
    print("\n------------------------------------------------------------------------------------")
    print("-> BUSCADOR: ")
    if nombre in inventario_productos:
        print(f"\nEl nombre del producto '{nombre}', tiene un precio de '{inventario_productos[nombre][0]}' y con una cantidad de '{inventario_productos[nombre][1]}'")
    else:
        print(f"\nEl producto '{nombre}' no existe en el inventario")   
    print("------------------------------------------------------------------------------------")
    # return precio, cantidad
    
    
def actualizar_precio(inventario_productos, nombre):  
    
    nuevo_precio:float = 0
    operacion = 0
    
    
    if nombre in inventario_productos.items():
        nuevo_precio = evaluar_tipo_numerico("Ingresa el nuevo precio que desees cambiar para el producto: ", float)
        operacion = inventario_productos[nombre][nuevo_precio][1]
        nuevo_precio = operacion
        print("\n------------------------------------------------------------------------------------")
        print("-> ACTUALIZAR: ")
        print(f"\nEl producto '{nombre}' ahora tiene un nuevo precio de '{nuevo_precio}'") #y con una cantidad de '{cantidad}'
        print("------------------------------------------------------------------------------------")
    else:
        print(f"\nEl producto '{nombre}' no existe en el inventario")


def eliminar_producto(inventario_productos, nombre):
    
    if nombre in inventario_productos:
        del inventario_productos[nombre]
        print("\nEl producto fue eliminado correctamente!")
    else:
        print(f"\nEl producto '{nombre}', no existe en el inventario")

def menu():
    
    opc: int = 0
    intentos = 3  # Cantidad de números máximos que permite hacer el programa
    i = 0         # Contador de intentos para finalizar el programa
    comprobar: bool = True
    inventario_productos = {} # Los poductos que tendre en el inventario
        
    print("-----------------------------------------------------------------")
    print("- - - Actividad: Gestión de inventarios con funciones y colecciones - - -")
    print("-----------------------------------------------------------------")
    print("\n Bienvenido al programa de la tienda RIWI EXPRESS \n")
    print("-----------------------------------------------------------------")

    while comprobar:
        print("\n ****** MENÚ: ****** \n")
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
                
            continue
                    
        elif opc == "2":
            
            print("\n*****************************************")
            print("  Elegiste la opción 2: Consultar productos ")
            print("*****************************************\n")
            
            nombre = evaluar_producto_vacio("Ingresa el nombre del producto que desees buscar: ", str)
            consultar_productos(inventario_productos, nombre)
            
            continue
        
        elif opc == "3":
            
            print("\n*****************************************")
            print("  Elegiste la opción 3: Actualizar precios ")
            print("*****************************************\n")
            
            ver_lista_productos(inventario_productos)
            
            nombre = evaluar_producto_vacio("\nIngresa el nombre del producto que deseas actualizar: ", str)
            actualizar_precio(inventario_productos, nombre)
            
            continue
        
        elif opc == "4":
            
            print("\n*****************************************")
            print("  Elegiste la opción 4: Eliminar productos ")
            print("*****************************************\n")
            
            ver_lista_productos(inventario_productos)
            
            nombre = evaluar_producto_vacio("\nIngresa el nombre del producto que deseas eliminar: ", str)
            eliminar_producto(inventario_productos, nombre)
            
            continue
        
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
