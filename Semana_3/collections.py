"""
listVar:[str] = []

listVar.append("Holaa")
listVar.append("mundo")

print(listVar[0])
"""

# ----- TUPLASSSSS -----

# Definiendo una tupla con algunos datos
""" tupla = (1, 2, 3, 4, 5)

print(tupla[0])

# Creando una lista vacía
lista = []

# Agregando los datos de la tupla a la lista
lista.extend(tupla)

# Agregando un dato a la lista
lista.append(6)

# Mostrando la lista
print(lista)  # Salida: [1, 2, 3, 4, 5]
"""
# Explicación del profe
"""
tupla=(1,13)
list_var2  = [1,3,4,5]
list_var2.append(tupla)
posicion = list_var2[4]
print(posicion[1])
"""

# --- TALLER DE CLASE ---

# 1.  Sum of list elements
# Given a list of numbers, calculate the sum of all its elements.
# Example: [1, 2, 3, 4, 5] → 15
"""
def show_list():
    num:list[int] = [1,2,3,4,5]
    count:int = 0
    i:int = 0

    for i in num:
        count += i
    return count

def main():
    print(show_list())

main()
"""
# 2. Remove duplicates from a list
# Create a function that receives a list and returns a new list without repeated elements, keeping the original order.
# Example: [1, 2, 2, 3, 4, 4, 5] → [1, 2, 3, 4, 5]
"""
def show_list():
    num:list[int] = [1,2,2,3,4,4,5]
    new_num:list[int] = []
    i:int = 0
    
    # Opcion 1
    for i in num:
        if i == 0 or i == 1 or i == 2 or i == 3 or i == 4 or i == 5:
            if i not in new_num:
                new_num.append(i)
    
    return new_num 

    # Opcion 2
    for i in num:
        if i in num:
            if i not in new_num:
                new_num.append(i)
    return new_num

    # Opción 3
    for i in num:
        if i not in new_num:
            new_num.append(i)
    return new_num


def main():
    print(show_list())

main()
"""
# 3. Reverse a list (without built-in methods)
# Reverse the order of a list without using reverse(), reversed(), or slicing ([::-1]). Use a loop.
# Example: [10, 20, 30, 40] → [40, 30, 20, 10]
"""
def show_list():
    num:list[int] = [10,20,30,40]
    new_num:list[int] = []
    i:int = 0
    
    for i in range(len(num) - 1, -1, -1):
        new_num.append(num[i]) 

    return new_num

def main():
    print(show_list())
main()
"""
# 4. Word counter
# Given a list of words, count how many times each one appears and return a dictionary with the results.
# Example: [“hola”, “mundo”, “hola”, “python”] → {“hola”: 2, “mundo”: 1, “python”: 1}
"""
def show_list():
    text:list[str] = ["hello","world","hello","python","learn"]
    result = { }
    i:int = 0
    
    for i in text:
        if i in result:
            result[i] += 1
        else:
            result[i] = 1
                
    return result

def main():
    print(show_list())
main()
"""
# 5. Merge two sorted lists
# Given two lists sorted in ascending order, merge them into a single list while maintaining the order.
# Example: [1, 3, 5] and [2, 4, 6] → [1, 2, 3, 4, 5, 6]
"""
def show_list():
    list1:list[int] = [1,3,5]
    list2:list[int] = [2,4,6]
    invento = [1,2,3,4,5,6]
    new_list:list[int] = []
    i:int = 0

    for i in list1:
        new_list.append(i)
    
    for i in list2:
        new_list.append(i)

    n = len(new_list)

    # Opcion 1 -> The mosttt difficult
    for i in range(n):
        for j in range(0, n-i-1):  # Vamos hasta n-i-1 para no volver a comparar los últimos elementos
            if new_list[j] > new_list[j+1]:  # Si el elemento es mayor que el siguiente, los intercambiamos
                new_list[j], new_list[j+1] = new_list[j+1], new_list[j]
    return new_list

    # Opcion 2
    new_list.sort() 
def main():
    print(show_list())
main()
"""
# 6. Max and min from a tuple
# Given a tuple of numbers, return a new tuple with the maximum and minimum values.
# Example: (5, 2, 9, 1, 7) → (9, 1)
"""
def show_list():
    tupla_num = (5, 2, 9, 1, 7)
    i:int = 0
    max_num = tupla_num[0]
    min_num = tupla_num[0]
    new_tupla = ()
    
    # Opcion 1
    for i in tupla_num:
        if i > max_num:
            max_num = i
        elif i < min_num:
            min_num = i
    new_tupla = (max_num, min_num)
    return new_tupla

    # Opcion 2
    max_num = max(tupla_num)
    min_num = min(tupla_num)
    new_tupla = (max_num, min_num)
    return new_tupla
    
def main():
    print(show_list())
main()
"""
# 7. Tuple unpacking
# Given the tuple (3, 5, 7, 9), assign each value to variables a, b, c, d and print them.
# Example: Expected output: a=3, b=5, c=7, d=9
"""
def show_list():
    tupla_num = (3, 5, 7, 9)
    i:int = 0
    tupla_let = ("a","b","c","d")
    tupla_new = ()
    
    # Opcion 1
    for i in range(len(tupla_num)):
        tupla_new = (tupla_let[i], tupla_num[i])
        print(tupla_new)
    return tupla_new 

    # Opcion 2
    for i in range(len(tupla_num)):
        print(f"{tupla_let[i]} = {tupla_num[i]}")
    
def main():
    show_list()
    
main()

# Opcion 3
def show_list():
    tupla_num = (3, 5, 7, 9)
    
    # Tuple unpacking
    a, b, c, d = tupla_num
    
    print("a =", a, ", b =", b, ", c =", c, ", d =", d)

def main():
    show_list()

main()
"""
# 8. Convert tuple to list and vice versa
# Convert a tuple into a list, add the number 4, and then convert it back into a tuple.
# Example: (1, 2, 3) → (1, 2, 3, 4)
"""
def show_list():
    tupla_num = (1, 2, 3)
    conv_tupla:list[int] = []
    nueva_tupla = ( )
    
    # Opcion 1    
    for i in tupla_num:
        conv_tupla.append(i)
    conv_tupla.append(4)
    
    # Convertimos la lista de nuevo a tupla
    for i in conv_tupla:
        nueva_tupla += (i,)  # Ojo: siempre con la coma para que sea tupla
    print(nueva_tupla)
    
    # Opcion 2
    conv_tupla.extend(tupla_num)
    conv_tupla.append(4)
    print(conv_tupla)

def main():
    show_list()

main()
"""
# 9. Dictionary from list of tuples
# Convert a list of (key, value) tuples into a dictionary.
# Example: [(“a”, 1), (“b”, 2), (“c”, 3)] → {“a”: 1, “b”: 2, “c”: 3}