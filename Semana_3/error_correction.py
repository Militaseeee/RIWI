# 1. Exercise 1
# El siguiente código intenta sumar los precios de un diccionario, pero no funciona. Encuentra el error y corrígelo para que calcule correctamente el total en python
"""
precios = {"manzana": 1.5, "banana": 0.8, "pera": 1.2}
total:int = 0
fruta:str = ""

# Se utiliza acá el .items() pera obtener la clave-valor del diccionario
for fruta, precio in precios.items():
    total += precio
print(total)
"""
# 2. Exercise 2
"""
usuario = {"nombre": "Ana", "edad": 30}
usuario.update({"ciudad":"Medellin"})
print(usuario["ciudad"])
"""
# 3. Exercise 3
"""
dic1 = {"a": 1, "b": 2}
dic2 = {"b": 3, "c": 4}
fusion = { }

# i -> key
for i, value in dic1.items():
    fusion[i] = value

# Concatenar cuando la clave es igual en ambos diccionarios
for i, value in dic2.items():
    if i not in fusion:
        fusion[i] = value
    else:
        fusion[i] += value

print(fusion)
"""
# 4. Exercise 4
"""
inventario = {"laptop": 5, "mouse": 10}
inventario.update({"laptop":3})  
print(inventario)
"""
# 5. Exercise 5

# Enunciado:
# El siguiente código debería contar cuántas veces aparece cada palabra en una 
# lista, pero no funciona correctamente. ¿Puedes encontrar el error y arreglarlo? en python
palabras = ["manzana", "banana", "manzana", "pera", "banana", "manzana"]
contador = {}

for palabra in palabras:
    if palabra not in contador:
        contador[palabra] = 1
    else:
        contador[palabra] += 1

print(contador)