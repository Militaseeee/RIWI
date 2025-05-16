# EXPLICACIÓN DE DICCIONARIOS:

# Ejemplo 1
"""
teamLeader_1 = {
    "nombre": "sebastian",
    "apellido": "puente",
    "edad": 37,
    9:98
}

teamLeader_2 = {
    "nombre": "Jhoan",
    "apellido": "Blanco",
    "edad": 25
}

print(teamLeader_1)
print(teamLeader_2)
"""
# Ejemplo 2
"""
alumno = {
    "nombre": "Karlos",
    "notas": [8, 7, 9],
    "aprobado": False
}

print(alumno["nombre"])
print(alumno.get("notas")) # -> se tiene una lista, se puede acceder con ese get

# Cuando no conocemos las claves
claves = alumno.keys()
print(claves)
"""
# Ejemplo 3
"""
empresa = {
    "empleado": {
        "nombre": "Karlos",
        "departamento": "TI"
    }
}
# Acceder al nombre del empleado
print(empresa["empleado"]["nombre"])
print(empresa["empleado"]["departamento"])
"""
# Ejemplo 4
"""
persona = {
    "nombre": "Ana",
    "notas": 25,
    "aprobado": "Madrid"
}

claves = persona.keys()
print(claves)

valores = persona.values()
print(valores)

for clave in persona:
    print(clave)

for valor in persona.values():
    print(valor)
"""
# Ejemplo 5
"""
import math
print(math.sqrt(16))
"""


# EXERCISES:

# 1. Contar frecuencia de palabras​
# Dada una lista de palabras, crea un diccionario que muestre cuántas veces aparece cada palabra.​
# Ejemplo: ["hola", "mundo", "hola"] → {"hola": 2, "mundo": 1}.​
"""
def show_disctionary():
    dictionary_word = ["hola","mundo","hola"]
    key:int = 0
    count = {}

    for i in dictionary_word:
        if i in count:
            count[i] += 1
        else:
            count[i] = 1
    print(count)

def main():
    show_disctionary()

main()
"""
# 2. Combinar diccionarios sumando valores​
# Dados dos diccionarios, combínalos sumando los valores de las claves comunes.​
# Ejemplo: {"a": 10, "b": 20} y {"a": 5, "c": 30} → {"a": 15, "b": 20, "c": 30}.​
"""
def show_disctionary():
    dic1 = {"a": 10, "b": 20}
    dic2 = {"b": 50, "c": 30}
    fusion = { }

    for key, value in dic1.items():
        fusion[key] = value

    for key, value in dic2.items():
        if key in fusion:
            fusion[key] += value
        else:
            fusion[key] = value
    
    return fusion

def main():
    print(show_disctionary())

main()
"""
# 3.  Clave con el valor máximo​
# Encuentra la clave que tenga el valor más alto en un diccionario.​
# Ejemplo: {"Juan": 85, "María": 92} → "María".​
"""
def show_disctionary():
    info = {"Juan": 85, "María": 92}
    
    # Opcion 1
    if "Juan" in info and "María" in info:
        if info["Juan"] > info["María"]:
            print(f"Juan is minor {info['Juan']}")
        else:
            print(f"María is major {info['María']}")

    # Opcion 2
    key_major = max(info, key=info.get)
    print(f"La clave con el valor más grande es: {key_major}")

def main():
    show_disctionary()

main()
"""
# 4. Filtrar por valor mínimo​
# Crea un nuevo diccionario solo con las claves cuyos valores sean mayores que un umbral dado.​
# Ejemplo: {"manzana": 50, "banana": 20}, umbral=30 → {"manzana": 50}.​
"""
def show_disctionary():
    info = {"manzana": 50, "banana": 20}
    umbral:int = 30
    show = {}
    
    for key, values in info.items():
        if values > umbral:
            show[key] = values

    return show

def main():
    print(show_disctionary())
main()
"""
# 5. Invertir diccionario​
# Intercambia claves y valores (asume que los valores son únicos).​
# Ejemplo: {"a": 1, "b": 2} → {1: "a", 2: "b"}.​
"""
def show_disctionary():
    dic1 = {"a": 1, "b": 2}
    inverted = { }
    
    # Opcion 1
    for key, value in dic1.items():
        inverted[value] = key
    
    return inverted

def main():
    print(show_disctionary())
main()
# Opcion 2
original_dict = {"a": 1, "b": 2, "c": 3}
inverted_dict = {value: key for key, value in original_dict.items()}
print(inverted_dict)  # Output: {1: 'a', 2: 'b', 3: 'c'}
"""
# 6. Diccionario de listas a lista de diccionarios​
# Transforma un diccionario de listas en una lista de diccionarios (como una tabla).​
# Ejemplo: {"nombre": ["Ana", "Juan"], "edad": [25, 30]} → [{"nombre": "Ana", "edad": 25}, {...}].​
"""
def show_disctionary():
    dic1 = {
        "nombre": ["Ana", "Juan"], 
        "edad": [25, 30]
    }

    list_dictionary = []
    review_dictionary = { }
    
    for i in range(len(dic1["nombre"])):
        review_dictionary = {
            "nombre": dic1["nombre"][i],
            "edad": dic1["edad"][i]
        }

        list_dictionary.append(review_dictionary)
    
    return list_dictionary

def main():
    print(show_disctionary())
main()
"""
# 7. Eliminar claves con valores nulos​
# Elimina las claves que tengan valores None, "" o 0.​
# Ejemplo: {"nombre": "Carlos", "edad": 0} → {"nombre": "Carlos"}.​
"""
def show_disctionary():
    dic1 = {
        "nombre": "Carlos", 
        "edad": 0,
        "altura": "",
        "ancho": 13.3,
        "carro": None
    }

    key_for_delete = []

    
    for key, value in dic1.items():
        if value == 0 or value == "" or value is None:
             key_for_delete.append(key)
    print(key_for_delete)
            
    for key in key_for_delete:
        del dic1[key]

    return dic1

def main():
    print(show_disctionary())
main()
"""
# 8. Aplanar diccionario anidado​
# Convierte un diccionario anidado en uno plano con claves compuestas.​
# Ejemplo: {"persona": {"nombre": "Ana"}} → {"persona_nombre": "Ana"}.​
"""
def show_disctionary():

    dic1 = {"persona": {"nombre": "Ana"}}
    concatenate = { }

    for key1 in dic1:
        value1 = dic1[key1]
        for key2 in value1: # Aquí asumimos que valor1 es un diccionario
            value2 = value1[key2]
            new_class = key1 + "_" + key2
            concatenate[new_class] = value2 
    return concatenate
        
def main():
    print(show_disctionary())
main()
"""
# 9. Ordenar diccionario por valor​
# Ordena las claves de un diccionario por sus valores (ascendente o descendente).​
# Ejemplo: {"A": 150, "B": 80} → {"B": 80, "A": 150} (ascendente).​
"""
# Opcion 1
def show_disctionary():
    dic1 = {"A": 150, "B": 80, "C": 200, "D": 500}

    # Convertimos a lista de tuplas (clave, valor)
    items = list(dic1.items())

    # Ordenamiento tipo burbuja
    for i in range(len(items)):
        for j in range(0, len(items) - i - 1):
            if items[j][1] > items[j + 1][1]:
                # Intercambiamos
                items[j], items[j + 1] = items[j + 1], items[j]

    # Volvemos a convertir a diccionario
    finish = {}
    for key, value in items:
        finish[key] = value
    
    return finish

def main():
    print(show_disctionary())
main()
"""
# Opcion 2
"""
dic1 = {"A": 150, "B": 80, 'C':180, 'E':500, 'D' : 100}
dic_line = {}
save_list = []
for i in dic1.values():
    save_list.append(i)
longitud = len(save_list)
for i in range(longitud):
    numeroMayor = max(save_list)
    for key,value in dic1.items():
        if value == numeroMayor:
            dic_line[key] = value
            save_list.remove(numeroMayor)
            break
print(dic_line)
"""
# Opcion 3
"""
dic1 = {"A": 150, "B": 80, 'C':180, 'E':500, 'D' : 100}
dic_line = {}
save_list = list(dic1.values())
longitud = len(save_list)
for i in range(longitud):
    numeroMayor = max(save_list)
    for key,value in dic1.items():
        if value == numeroMayor:
            dic_line[key] = value
            save_list.remove(numeroMayor)
            break
print(dic_line)
"""
# 10. Validar claves comunes entre diccionarios​
# Verifica si dos diccionarios tienen al menos una clave en común.​
# Ejemplo: {"a": 1} y {"b": 2} → False; {"a": 1} y {"a": 2} → True.

def show_disctionary():
    dic1 = {"a": 1}
    dic2 = {"b": 2}
    
    for key1 in dic1:
        if key1 in dic2:
            return True 
    
    return False


def main():
    print(show_disctionary())
main()
