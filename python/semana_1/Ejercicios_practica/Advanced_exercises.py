# Advanced Python Exercises (Pro Level)

# 1. GPA Calculator with Validation
# Asks the user for 3 notes (between 0 and 10).

# If any note is out of range, it displays an error message.
# If all are valid, it calculates the average and shows whether the student passes (≥ 6) or fails.
"""
n1 = float(input("Type the first note (0 and 10): "))
n2 = float(input("Type the second note (0 and 10):"))
n3 = float(input("Type the third note (0 and 10):"))

if (n1>=0 and n1<=10) and (n2>=0 and n2<=10) and (n3>=0 and n3<=10): 
    average = (n1 + n2 + n3) / 3
    print(f"Average of the three notes are: {average:.2f}")
    if average >= 6:
        print("You passed!")
    else:
        print("You failed.")  
else:   
    print("Error: One or more notes are out of range.") 
"""
# 2. Years to retirement
# It asks for the user's age and gender ("M" for female, "H" for male).

# A woman retires at 60
# A man retires at 65
# If eligible for retirement, it displays a celebratory message.
# If not, it indicates how many years until retirement.
"""
age = int(input("Enter your age: "))
gender = str(input("Enter your gender (M for woman, H for man): "))

if (gender == "M" or gender == "m"):
    if age >= 60:
        print("Congratulations! You can retire.")
    else:
        print("You can't retire yet.")
        print(f"You have {60 - age} years left to retire.")
elif gender == "H" or gender == "h":
    if age >= 65:
        print("Congratulations! You can retire.")
    else:
        print("You can't retire yet.")
        print(f"You have {65 - age} years left to retire.")
"""
# 3. Net Salary Calculator

# Ask for:
# - Gross salary / Sueldo bruto
# - Discount percentage (for example: 13)

# Calculate net salary using the formula:
# - net_salary = gross_salary - (gross_salary * discount / 100)
# 
# Example output:
# - Gross salary: 1000 Discount: 13 Net salary: 870.0
"""
gross_salary = float(input("Enter your gross salary: "))
discount = int(input("Enter the discount percentage: (0 to 100) "))

if discount <= 0 and discount >= 100:
    print("Error: Discount percentage should be between 0 and 100.")
else:
    net_salary = gross_salary - (gross_salary * discount / 100)

    print(f"Gross salary: {gross_salary}, Discount: {discount}%, Net salary: {net_salary:.2f}")
"""
# 4. Compatible?

# Ask for:
# - Names and ages of two people
# 
# Check if:
# - Both are at least 18 years old
# - Have different names
# - The age difference is less than 10 years
# 
# If they meet all requirements, print a funny message saying they're compatible 💞
# If not, explain why they're not.
"""
name = str(input("Enter your name: "))
age = int(input("Enter your age: "))
name2 = str(input("Enter the second name: "))
age2 = int(input("Enter the second age: "))

if (age >= 18 and age2 >= 18) and (name != name2):
    # --------------- opcion 1 ---------------
    # ABSOLUTE VALUE -> Devuelve el valor absoluto de un número
    if abs(age - age2) < 10:
        print("You are compatible!")
    else:
        print("You are not compatible because the age difference is greater than 10 years.")
    # --------------- opcion 2 ---------------
    if age > age2:
        age_difference = age - age2
    else:
        age_difference = age2 - age
    if age_difference < 10:
        print("You are compatible!")
    else:
        print("You are not compatible because the age difference is greater than 10 years.")

else:
    print("You are not compatible because one of you is under 18 or they have the same name.")
"""
# 5. Multiples Calculator
#Ask for two numbers and check if the first is a multiple of the second using %.

#Example:
#- 12 is a multiple of 4 → True 15 is a multiple of 6 → False
"""
n1 = int(input("Type the first number: "))
n2 = int(input("Type the second number: "))

if n1 % n2 == 0:
    print(f"{n1} is a multiple of {n2}.")
else:
    print(f"{n1} is not a multiple of {n2}.")
"""
# 6. Magic Number
# Prompts the user for a number.
# If it's divisible by both 3 and 5, prints: "FizzBuzz"
# If it's only divisible by 3, prints: "Fizz"
# If it's only divisible by 5, prints: "Buzz"
# If it's not divisible by either, prints: "Nothing magical"
"""
n1 = int(input("Type the number: "))
if n1 % 3 == 0 and n1 % 5 == 0:
    print("FizzBuzz")   
elif n1 % 3 == 0:
    print("Fizz")
elif n1 % 5 == 0:
    print("Buzz")
else:
    print("Nothing magical")
"""
# Advanced Unit Converter
# Request a quantity in kilometers and convert:
# To meters (km × 1000)
# To centimeters (km × 100000)
# To millimeters (km × 1_000_000)
# Display everything on a single line using print() and concatenation.
# Example:
# 2 km = 2000 meters, 200000 cm, 2000000 mm
"""
kilometers = float(input("Enter the distance in kilometers: "))
meters = kilometers * 1000
centimeters = kilometers * 100000
millimeters = kilometers * 1000000

print(f"{kilometers} km = {meters} meters, {centimeters} cm, {millimeters} mm")
"""
# Exercise ANDREA
# parte 3 - python semana 1
# 1
"""
age=int(input("Enter your age: "))
inv=int(input("Enter your invitation number: "))
if age > 21 and inv == 777:
    print("You can enter")
else:
    print("You can't enter")
"""    
# 2
"""
buy=float(input("Enter the amount of your purchase: "))
age=int(input("Enter your age: "))
if buy > 100 or age > 60:
    print("You have a discount")
else:
    print("You don't have a discount")
"""    
# 3
"""  
age=int(input("Enter your age: "))
country=str(input("Enter your country: "))
id=int(input("Enter your ID: "))
if age >= 18 and age <= 30 and country.lower() != "Antártida" and id != 0:
    print("You can enter")
else:
    print("You can't enter")
"""  
# 4
""" 
note1 =int(input("Enter the first note: "))
note2 =int(input("Enter the second note: "))
if note1 >= 6 and note2 >= 6:
    print("You are approved")
elif note1 < 4 and note2 < 4:
    print("You are disapproved")
else:
    print("You are in recovery") 
""" 
# 5
""" 
service=str(input("Enter the service: (http o https) "))
port=int(input("Enter the port: (80 o 443)")) 

if (service == "http" or service == "https") and (port == 80 or port == 443):
    print("The service is correct")
else:
    print("The service is incorrect")
    
if service == "https" and port == 443:
    print("The connection is secure")
else:
    print("The connection is not secure")
""" 
# 6
""" 
prom=int(input("Enter the average: "))
amount =int(input("Enter the amount of subjects: "))
ing =int(input("Enter the income: "))
if prom>=8 and amount<3 and ing<=1500:
    print("You can enter")
else:
    print("You can't enter")
""" 
# 7
""" 
est=float(input("Enter the height: "))
age=int(input("Enter the age: "))
if est >= 140 and (age >= 10 or age <= 60):
    print("You can enter")
else:
    print("You can't enter")
""" 
# 8
"""
pss=str(input("Enter the password: "))
#Option 1
if len(pss) >= 8 and "123" not in pss: 
    print("The password is secure")
else:
    print("The password is not secure")
#Option 2
if len(pss) >= 8:
    if "123" not in pss: #not in comprueba que no lo contenga
        print("The password is secure")
    else:
        print("The password is correct but it is not secure (contains '123')")
else:
    print("The password is too short")
"""
# 9
"""
income=int(input("Enter your monthly income: "))
active=str(input("Do you have active debts? (Si or No): "))

if income > 2500 or(income > 1500 and active.lower() == "no"):
    print("You can have a credit card")
else:
    print("You can't have a credit card")
"""
# 10
"""
hours=int(input("Enter the day hours: (since 0 to 23) "))
if hours >= 0 and hours <= 23:
    if hours >= 8 and hours <= 18 and(hours != 13):
        print("Calendar allowed")
    else:
        print("Calendar not allowed")
else:
    print("ERRORRRR: The hours are incorrect")
"""
# parte 4 - python semana 1
# 10
"""
word = str(input("Enter a word: "))
if len(word) <= 5:
    print("The word is short")
else:
    print("The word is long")
"""
# 12
"""
age = int(input("Enter your age: "))
if 17 < age < 66:
    print("The age is valid")
else:
    print("The age is not valid")
"""
# 18
# Opción 1
"""
letter = input("Enter a letter: ").lower()  # Convertir la letra a minúscula
if letter != 'a' and letter != 'e' and letter != 'i' and letter != 'o' and letter != 'u': 
    print("The letter is not a vowel")
else:
    print("The letter is a vowel")
# Opción 2
letter = input("Enter a letter: ").lower()  
if letter not in ['a', 'e', 'i', 'o', 'u']: 
    print("The letter is not a vowel")
else:
    print("The letter is a vowel")
"""
# 19
"""
n1=int(input("Enter the first number: "))
n2=int(input("Enter the second number: "))
if n1 % n2 != 0:
    print(f"{n1} is not a multiple of {n2}")
else:
    print(f"{n1} is a multiple of {n2}")
"""
# 20
"""
n1=int(input("Enter the first number: "))
if n1 == 0 or (n1 > 10 and n1 < 20):
    print("The number is this range")
else:
    print("The number is not this range")
"""