# 1. Contar del 1 al 10: Usa un while para imprimir los números del 1 al 10.
"""
num:int = 1
while num <= 10:
    print(num)
    num += 1
"""
# 2. Contar hacia atrás: Usa un while para imprimir los números del 10 al 1 en orden descendente.
"""
num:int = 10
while num >= 1:
    print(num)
    num -= 1
"""
# 3. Suma de los primeros 10 números: Usa un while para sumar los números del 1 al 10 e imprimir el resultado.
"""
num:int = 0
suma:int = 0
while num <= 10:
    print(suma)
    num += 1
    suma += num
"""
# 4. Solicitar número positivo: Pide al usuario un número y usa un while para seguir pidiendo hasta que ingrese un número positivo.
"""
num = 0

num = int(input("Enter positive or negative number: "))

while num <= 0:

    num = int(input("Enter positive or negative number: "))
    num = int(input("Please enter a positive number: "))

print("Thank you! You entered:", num)
"""
# 5. Menú interactivo: Crea un menú con while que permita al usuario elegir entre opciones (por ejemplo, "1. Saludar", "2. Despedirse", "3. Salir") y solo termine cuando elija la opción de salir.
"""
opc = 0
print("chose one number for navegate in this menu: ")
while opc != 3:
    print("1. Greet")
    print("2. Say goodbye")
    print("3. Exit")
    opc = int(input("Enter a number for navegate in this menu: (1 - 3)"))
    
    if opc == 1:
        print("Hello\n")
    elif opc == 2:
        print("Good bye\n")
    elif opc == 3:
        print("Exit\n")
    else:
        print("Invalid option. Please choose 1, 2, or 3. \n")
"""
# 6. Adivina el número: Genera un número secreto (puedes usar una variable fija) y usa un while para pedirle al usuario que lo adivine. Da pistas si el número es mayor o menor.    
"""
secret_number:int = 8
num = 0
print("You should guess a secret number")

while num != secret_number:
    num = int(input("Enter the number: (0-10)"))
    if num > secret_number and num <= 10:
        print(f"{num} number is major than the number secret")
    elif num >= 0 and num < secret_number:
        print(f"{num} number is minor than the number secret")
    elif num <= 0:
        print("Negative number is prohibited")
    elif num == secret_number:
        print("Congratulation!!!!!!")
    else:
        print("This number is invalid")
"""
# 7. Contar vocales en una palabra: Pide al usuario una palabra y usa un while para contar cuántas vocales tiene.     
"""
i:int = 0
word = ""
count_word:int = 0
word = str(input("Enter any word: "))

word = word.lower()

while i < len(word):
    if word[i] == "a" or word[i] == "e" or word[i] =="i" or word[i] == "o" or word[i] == "u":
        count_word += 1
    i += 1        
print(count_word)
"""
# 8. Validar contraseña: Pide al usuario una contraseña y usa un while para seguir pidiendo hasta que ingrese "python123".
"""
correct_pass:int = "python123"
password = ""

while password != correct_pass:
    password = str(input("Enter correct password: "))
    if password == correct_pass:
        print("The password is correct")
    else:
        print("The password is incorrect")
"""
# 9. Sumar hasta detenerse: Pide números al usuario y súmalos hasta que ingrese "0", momento en el que se imprimirá el total.  
"""
num = 1
add:int = 0
i:int = 0

while num != 0:
    num = int(input("Enter differents numbers: "))
    add += num    
print(f"The total sum is: {add}")  
"""        
# 10.Número de intentos: Pide al usuario que ingrese un número entre 1 y 10. Si no lo hace, sigue pidiéndolo hasta que lo haga correctamente.
""" 
num = 0

while num < 1 or num > 10:
    num = int(input("Enter any number, between 1-10: \n"))  
print("Bye!!!!")    
"""            