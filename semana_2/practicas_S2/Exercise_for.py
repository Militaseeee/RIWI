# 1. Contar del 1 al 10: Escribe un programa que use un for para imprimir los números del 1 al 10.
"""
n:int = 0
for n in range(1,11,1):
    print(n) 
"""
# 2. Contar hacia atrás: Usa un for para imprimir los números del 10 al 1 en orden descendente.
"""
n:int = 10
for i in range(n,0,-1):
    print(i)
"""
# 3. Sumar los primeros 10 números: Usa un for para sumar los números del 1 al 10 e imprime el resultado.
"""
i:int = 0
add:int = 0
for i in range(1, 11):
    add += i
print(add)
"""
# 4. Números pares del 1 al 20: Usa un for y condicionales para imprimir solo los números pares del 1 al 20.
"""
even:int = 0
for even in range(1, 21):
    if even % 2 == 0:
        print(f"Even numbers are: {even}")
"""
# 5. Detectar múltiplos de 3: Escribe un programa que use un for y condicionales para imprimir los números del 1 al 30 que sean múltiplos de 3.
"""
i:int = 0
for i in range(1, 31):
    if i % 3 == 0:
        print(f"multiples of 3 are: {i}")
"""
# 6. Contar letras "a": Pide al usuario una palabra y usa un for con un condicional para contar cuántas veces aparece la letra "a".
"""
i:int = 0
word = ""
count_word:int = 0
word = str(input("Enter any word: "))
letter = "a"
word = word.lower()

for i in word:
    if i == letter:
        count_word += 1
    
print(count_word)
"""
# 7. Tabla de multiplicar del 5: Usa un for para imprimir la tabla de multiplicar del 5 (del 1 al 10).        
"""
n:int = 5
result:int = 0
for i in range(1, 11):
    result = n * i
    print(f"{n} x {i} = {result}")   
"""
# 8. Números positivos en una lista: Dada una lista de números (por ejemplo, [3, -1, 5, -2, 7, -8]), usa un for y condicionales para imprimir solo los positivos.
"""
num:list[int] = [3, -1, 5, -2, 7, -8, -3, -1, -2, 8]
i:int = 0
for i in num:
    if i > 0:
       print(i) 
"""
# 9. Mayúsculas y minúsculas: Pide al usuario una palabra y usa un for para contar cuántas letras son mayúsculas y cuántas son minúsculas.
"""
word = ""
word = str(input("Enter any word: "))
i:int = 0
uppercase_count = 0
lowercase_count = 0

for i in word:
    if i.isupper():  # Si es mayúscula
        uppercase_count += 1
    elif i.islower():  # Si es minúscula
        lowercase_count += 1

print(f"Uppercase letters: {uppercase_count}")
print(f"Lowercase letters: {lowercase_count}")
"""
# 10. Simulación de contraseña: Pide al usuario que ingrese una contraseña e imprime "Acceso permitido" solo si la contraseña es "python123", usando un for para simular tres intentos.

correct_pass:int = "python123"
password = ""
i:int = 0
acount:int = 3

for i in range(acount):
    password = str(input("Enter correct password: "))
    
    if password == correct_pass:
        print("Access granted!")
        break
    else:
        print(f"{i + 1} Wrong password. Try again")
else:
    print("Access denied. You have lost all your chances")    