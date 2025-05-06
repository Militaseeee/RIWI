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
