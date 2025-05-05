"""
# Defininición de la primera función que concatena "Hello " con un nombre específico
def demo_methodo(name: str = "world") -> str:  
    result: str = "Hello " + name  # Concatenamos "Hello " con el nombre proporcionado
    return result  # Retornamos el resultado como un string

# Defininición de la primera función que suma un número con la longitud de un string
def demo_methodo_2(world_var: str = "world", number_var: int = 0) -> int:
    result_var: int = number_var + len(world_var)  # Sumamos la longitud de 'world_var' al número proporcionado
    return result_var  # Retornamos el resultado como un entero

# Definimos la función principal donde llamamos a las funciones anteriores y mostramos los resultados
def main():
    print(demo_methodo(name="señora"))  # Llamamos a 'demo_methodo' con "señora" como argumento
    print("")  # Imprimimos una línea vacía

    print(demo_methodo())  # Llamamos a 'demo_methodo' sin argumento, usa el valor por defecto "world"
    print(demo_methodo("c:"))  # Llamamos a 'demo_methodo' con "c:" como argumento
    print(f"-------------------\n")  # Imprimimos una línea divisoria

    print(demo_methodo_2("Miguel", 5))  # Llamamos a 'demo_methodo_2' con "Miguel" y 5 como argumentos
    print("")  # Imprimimos una línea vacía

    print(demo_methodo_2("Geo", 5))  # Llamamos a 'demo_methodo_2' con "Geo" y 5 como argumentos

    print(result_sum)
    print(result_sum_1)

# Ejecutamos la función principal para ver los resultados en consola
main()
"""
# ----------------------------------------------------------------------------------------------------------------

# Exercise with functions

# 1. Function for greet
# Create a function called greet that takes a name as a parameter and returns a greeting message (e.g., "Hello, John!").
"""
def greet(name:str = "") -> str:
    
    #name = input("Enter your name: ")
    result: str = "Hello, " + name +"!!!"
    return result

def main():
    print(greet(input("Enter your name: ")))
    
main()
"""
# 2. Function to add two numbers
# Define a function called sumar that receives two numbers and returns their sum.
"""
def add_numbers():
    num1 = 0
    num2 = 0
    add = 0

    num1 = int(input("Enter the first number: "))  
    num2 = int(input("Enter the second number: "))  
    add = num1 + num2  

    return add

def main():
    print(add_numbers())

main()
"""
# 3. Function to calculate the area of a circle
# Create a function called area_circulo that takes the radius as a parameter and calculates the area (use pi = 3.1416).
"""
def area_circle():
    pi:float = 3.1416
    area:float = 0.0
    radius = 0.0

    radius = float(input("Enter the radius to calculate the area of a circle: "))

    area = pi * (radius * radius)

    return area

def main():
    print(f"The area of a circle is: {area_circle()}")

main()
"""
# 4. Function to check if a number is even
# Define a function called es_par that returns True if a number is even, and False if it is not.
"""
def is_even():
    num = 0
    com = True

    num = int(input("Enter any number: "))

    if num % 2 == 0:
        print(f"{num} number is even")
        com = True
    else:
        print(f"{num} number is odd")
        com = False 
    
    return com

def main():
    print(is_even())

main()
"""
# 5. Function to find the largest of three numbers
# Create a function called mayor_de_tres that receives three numbers and returns the largest one.
"""
def greater_than_three():
    num1 = 0
    num2 = 0
    num3 = 0

    num1 = int(input("Enter the first number:"))
    num2 = int(input("Enter the second number: "))
    num3 = int(input("Enter the third number: "))

    if num1 >= num2 and num1 >= num3:
        print(f"Greater number is {num1}")
    elif num2 >= num1 and num2 >= num3:
        print(f"Greater number is {num2}") 
    elif num3 >= num1 and num3 >= num2:
        print(f"Greater number is {num3}")

    return num1, num2, num3

def main():
    #print(greater_than_three())
    greater_than_three()

main()
"""
# 6. Function to count vowels in a string
# Define a function called contar_vocales that counts how many vowels are in a string (ignoring uppercase and lowercase letters).
# Option 1
"""
def vowels():
    word:list[str] = ["E", "A", "O", "I", "U", "a", "e", "i", "o", "u", "b", "c", "d", "f", "g", "h", "a", "e", "U"]
    return word

def count_vowels():

    i:int = 0
    count:int = 0
    
    vowel_list = vowels()

    for i in vowel_list:
        if i == "a" or i == "A" or i == "e" or i == "E" or i == "i" or i == "I" or i == "o" or i == "O" or i == "u" or i == "U":
            
            count += 1

    return count

def main():
    print(count_vowels())

main()
"""
# Option 2
"""
def count_vowels(word):

    i:int = 0
    count:int = 0

    for i in word:
        if i == "a" or i == "A" or i == "e" or i == "E" or i == "i" or i == "I" or i == "o" or i == "O" or i == "u" or i == "U":
            
            count += 1 

    return count

def main():
    print(count_vowels(input("Enter a word: ")))

main()
"""
# 7. Function to calculate the factorial (recursion)
# Create a function called factorial that calculates the factorial of a number using recursion.
"""
def factorial_recursion(num):
    if num < 0:
        return -1  # El factorial no está definido para números negativos
    elif num == 0 or num == 1:
        return 1  # Caso base: factorial de 0 y 1 es 1
    else:
        return num * factorial_recursion(num- 1)  # Llamada recursiva


def main():
    num = int(input("Enter a number to search factorial number: "))  # Convert input to an integer
    print(factorial_recursion(num))
main()
"""
# 8. Function to check if a word is a palindrome
# Define a function called es_palindromo that determines if a word reads the same backward (e.g., “reconocer”).
# Opcion 1
"""
def is_palindrome(word):

    left = ""
    i:int = 0

    word = word.lower() # Convert to lowercase
    word = word.replace(" ", "") # Remove spaces
    
    for i in range(len(word) - 1, -1, -1):
        #left = left.__add__(word[i])
        left += word[i]
    if word == left :
        print(f"'{word}' is a palindrome!")
    else:
        print(f"'{word}' is not a palindrome!")
    
    return left


def main():
    word = str(input("Enter a word for searching if this  word is palindrome: "))  # Convert input to an integer
    print(is_palindrome(word)) 

main()
"""

# Opcion 2
""" def is_palindrome(word):
    # Convert to lowercase and remove spaces
    word = word.lower().replace(" ", "")
    
    # Compare the word with its reverse
    return word == word[::-1]

def main():
    word = input("Enter a word to check if it is a palindrome: ")
    if is_palindrome(word):
        print(f"'{word}' is a palindrome!")
    else:
        print(f"'{word}' is not a palindrome!")

main() """

# Opcion 3
""" def is_palindrome(word):
    word = word.lower().replace(" ", "")
    left = 0
    right = len(word) - 1
    
    while left < right:
        if word[left] != word[right]:
            return False
        left += 1
        right -= 1
    return True

def main():
    word = input("Enter a word to check if it is a palindrome: ")
    if is_palindrome(word):
        print(f"'{word}' is a palindrome!")
    else:
        print(f"'{word}' is not a palindrome!")
main() """
# 9. Function to generate the Fibonacci sequence
# Create a function called fibonacci that generates the first n terms of the Fibonacci sequence.
"""
def fibonacci():
    num:int = 0
    fibo: int = 0
    num2:int = 1

    for i in range(0, 10):
        print(num)

        num = num2 + fibo
        fibo = num2  # Actualizar fibo
        num2 = num   # Actualizar num2    

def main():
    fibonacci()
    
main()
"""
# 10. Function to convert Celsius to Fahrenheit
# Define a function called celsius_to_fahrenheit that converts degrees Celsius to Fahrenheit (formula: (C × 9/5) + 32).

def celsius_to_fahrenheit(celsius:float):
    
    formula:float = 0.0
    formula = (celsius * (9/5) + 32) 
    return formula
    
def main():
    celsius = float(input("Enter degrees Celsius for converts a Fahrenheit: "))
    print(celsius_to_fahrenheit(celsius))
    
main()
