# Lista que contiene diccionarios (cada diccionario es un estudiante)
estudiantes = [
    {"nombre": "Ana", "edad": 20, "carrera": "Ingeniería"},
    {"nombre": "Luis", "edad": 22, "carrera": "Medicina"},
    {"nombre": "Sofía", "edad": 19, "carrera": "Arquitectura"}
]

# Función para agregar estudiante
def agregar_estudiante():
    nombre = input("Ingrese el nombre del estudiante: ")
    edad = int(input("Ingrese la edad: "))
    carrera = input("Ingrese la carrera: ")
    nuevo = {"nombre": nombre, "edad": edad, "carrera": carrera}
    estudiantes.append(nuevo)
    print("Estudiante agregado.\n")

# Función para eliminar estudiante
def eliminar_estudiante():
    nombre = input("Ingrese el nombre del estudiante a eliminar: ")
    encontrado = False
    for est in estudiantes:
        if est["nombre"] == nombre:
            estudiantes.remove(est)
            print("Estudiante eliminado.\n")
            encontrado = True
            break
    if not encontrado:
        print("Estudiante no encontrado.\n")

# Función para contar estudiantes
def contar_estudiantes():
    total = len(estudiantes)
    print("Total de estudiantes:", total, "\n")

# Función para modificar carrera
def modificar_carrera():
    nombre = input("Ingrese el nombre del estudiante a modificar: ")
    nueva_carrera = input("Ingrese la nueva carrera: ")
    modificado = False
    for est in estudiantes:
        if est["nombre"] == nombre:
            est["carrera"] = nueva_carrera
            print("Carrera modificada.\n")
            modificado = True
            break
    if not modificado:
        print("Estudiante no encontrado.\n")

# Función para mostrar todos
def mostrar_estudiantes():
    print("Lista de estudiantes:")
    for est in estudiantes:
        print("Nombre:", est["nombre"])
        print("Edad:", est["edad"])
        print("Carrera:", est["carrera"])
        print("-------------------")
    print()

# Menú principal
def menu():
    while True:
        print("===== MENÚ =====")
        print("1. Agregar estudiante")
        print("2. Eliminar estudiante")
        print("3. Contar estudiantes")
        print("4. Modificar carrera")
        print("5. Mostrar estudiantes")
        print("6. Salir")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            agregar_estudiante()
        elif opcion == "2":
            eliminar_estudiante()
        elif opcion == "3":
            contar_estudiantes()
        elif opcion == "4":
            modificar_carrera()
        elif opcion == "5":
            mostrar_estudiantes()
        elif opcion == "6":
            print("Saliendo del programa...")
            break
        else:
            print("Opción no válida. Intente de nuevo.\n")

# Llamamos al menú
menu()
