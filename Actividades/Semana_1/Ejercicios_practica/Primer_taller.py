# Exercise 1: Create a program that asks the user for their name and age, 
# and then prints a message saying "Hello [name], you are [age] years old."
"""
name = str(input("What is your name? "))
age = int(input("What is your age? "))
print(f"Hello {name}, you are {age} years old.")
"""
# Exercise 2: Create a program that asks the user for two numbers and it shows the sum of them.
#from operator import add #opcion 1
"""
n1 = int(input("Enter the first number: "))
n2 = int(input("Enter the second number: "))
#print(add(n1, n2)) #opcion 1
print(n1 + n2) #opcion 2
"""
# Exercise 3: Create a program that asks the user for a number and then prints the multiplication table of that number.
#from operator import mul
"""
n1 = float(input("Enter the first number: "))
n2 = float(input("Enter the second number: "))
#print(mul(n1, n2)) #opcion 1
print(n1 * n2) #opcion 2
"""
# Exercise 4: Create a program that asks for an integer and displays double and triple of that number, separated by commas.
"""
n1 = int(input("Enter the first number: "))
print (f"{n1 * 1}, {n1 * 2}, {n1 * 3}")
"""
#Exercise 5: Create a program that asks for a number and displays the square and cube of that number, separated by commas.
""" 
word = str(input("Enter a word: "))
n = int(input("Enter a number: "))
for i in range(n):
    print(f"{word}")
"""
# Exercise 6: Create a program that asks the user for two numbers and displays the result of dividing the first by the second.
"""
n1=float(input("Enter the first number: "))
n2=float(input("Enter the second number: "))
if n2 != 0:
    print(f"{n1/n2}")
else:
    print("No se puede dividir entre cero.")
"""
# Exercise 7: Create a program that asks the user for their name and show how many letters their name.
"""
name = str(input("Enter your name: "))
count = len(name)
print(f"Your name has {count} letters.")
"""
# Exercise 8: Create a program that asks the user for two numbers and displays the integer division (//) and the modulus (%) between them.
"""
n1=int(input("Enter the first number: "))
n2=int(input("Enter the second number: "))
if n2 != 0:
    print(f"Integer division: {n1 // n2}")
    print(f"Remainder (modulo): {n1 % n2}")
else:
    print("You can't divide by zero!")
"""
# Exercise 9: Create a program that asks the user for two numbers and display the addition, subtraction, multiplication, and division (separated by commas).
"""
n1=int(input("Enter the first number: "))
n2=int(input("Enter the second number: "))
print(f"{n1 + n2}, {n1 - n2}, {n1 * n2}, {n1 / n2}")
"""
# Exercise 10: Create a program that asks for an integer and displays the number raised to the power of 2 (squared) and 3 (cubed), using f-strings.
"""
n=int(input("Enter a integer: "))
print(f"{n**2}, {n**3}") # This forms is "f-strings"
"""
# Exercise 11: Create a program that asks the user for a decimal number and displays only the integer part of that number.
"""
n=float(input("Enter a number: "))
num_int=int(n) #--> This is second form
print(num_int) #--> This is second form
print(f"{n:.0f}") #--> This is first form
"""
# Exercise 12: Create a program that asks the user for their age and display a message if they are older than 18 (use comparison operators, no conditionals).
"""
#Multiplicacion Booleana
age = int(input("Enter your age: \n"))
#print("You are an adult" * (age > 18) + "You are a minor" * (age <= 18)) #opcion 1
print("You are an adult" * (age > 18)) #opcion 2
print("You are a minor" * (age <= 18)) #opcion 2

#Diccionarios
age = int(input("Enter your age: \n"))
mensajes = {True: "You are an adult", False: "You are a minor"}
print(mensajes[age > 18])
"""
# Exercise 13: Create a program that asks the user for two integers and display whether they are equal (use comparison operators, no conditionals).
"""
n1=int(input("Enter the first number: "))
n2=int(input("Enter the second number: "))
print("The numbers are equal" * (n1 == n2) + "The numbers are different" * (n1 != n2))
"""
# Exercise 14: Create a program that ask for two numbers and show if the first is greater than the second (use the comparison operator).
"""
n1=int(input("Enter the first number: "))
n2=int(input("Enter the second number: "))
print("The first number is major" * (n1 > n2))
print("The second number is major" * (n2 > n1))
"""
# Exercise 15: Create a program that ask for two numbers and show whether the first is less than or equal to the second (use the comparison operator).
n1=int(input("Enter the first number: "))
n2=int(input("Enter the second number: "))
print("The first number is less than or equal to the second" * (n1 <= n2))
print("The second number is less than or equal to the second" * (n2 > n1))
