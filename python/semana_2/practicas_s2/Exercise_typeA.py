# 10 Projects to Practice Conditionals in Python

# 🔹 1️⃣ Sistema de recomendaciones de películas 🎬
# Crea un programa que recomiende una película basada en la edad del usuario y sus preferencias (acción, comedia, terror, etc.).
# 👉 Si el usuario es menor de 13 años, evita películas con clasificación para adultos.
"""   
age = int(input("Enter your age: "))
genre = input("Enter your preferred genre (action, comedy, horror): ").lower()
if age <= 13:
    print("You can't watch any scary movie.")
    print("")
    chose_genre = input("Choose a genre (action, comedy): ").lower()
    if chose_genre == "action":
        print("We recommend 'The Incredibles'")
    elif chose_genre == "comedy":
        print("We recommend 'Toy Story'")  
    else:
        print("Invalid genre")
elif 14 <= age <= 100:
    chose_genre = input("Choose a genre (action, comedy, horror): ").lower()
    if chose_genre == "action":
        print("We recommend 'Spider-Man'")
    elif chose_genre == "comedy":
        print("We recommend 'The Hangover'")
    elif chose_genre == "horror":
        print("We recommend 'The Conjuring'")
    else:
        print("Invalid genre")
else:
    print("Invalid age")
""" 
# Opc 2
""" 
movies = {
    "action": ["Avengers", "Spider-Man", "Black Panther", "Deadpool (adults only)"],
    "comedy": ["Minions", "Shrek", "Superbad (adults only)", "The Mask"],
    "horror": ["The Nun (adults only)", "Coraline", "The Ring (adults only)"],
    "adventure": ["Harry Potter", "Narnia", "Pirates of the Caribbean"]
}
age = int(input("Enter your age: "))
print("\nAvailable genres: action, comedy, horror or adventure")
genre = input("What type of movie do you prefer? ").lower()
if genre in movies:
    print("\nWe recommend these movies:")    
    for i in movies[genre]:
        if age < 18 and "(adults only)" in i:
            continue
        print(i)
else:
    print("Invalid genre")
""" 
# 🔹 2️⃣ Asistente de productividad ⏳
# Diseña un asistente que, según la hora actual y si es un día laboral o fin de semana, sugiera actividades como:
# ✅ "trabajar"
# ✅ "descansar"
# ✅ "hacer ejercicio"
# Asegúrate de que los datos sean flexibles para futuras mejoras.
""" 
productivity = {
    "week": ["monday", "tuesday", "wednesday", "thursday", "friday"],
    "weekend": ["saturday", "sunday"]
}
time = int(input("Enter the current hour (0-23): "))
if time >= 0 and time <= 23:
    day = str(input("Enter the current day: ")).lower()
    if day in productivity["week"] or day in productivity["weekend"]:
        if time >= 0 and time <= 5:
            print("It's too early. You're resting")
        elif time >= 6 and time <= 14 and (day in productivity["week"]):
            print("It's time to work")
        elif time >= 6 and time <= 14 and (day in productivity["weekend"]):
            print("It's time to exercise")
        elif time >= 15 and time <= 17 and (day in productivity["week"]):
            print("It's time to exercise")
        elif time >= 15 and time <= 17 and (day in productivity["weekend"]):
            print("It's time to rest")
        elif time >= 18 and time <= 23 and (day in productivity["week"]):
            print("It's time to rest")
        elif time >= 18 and time <= 23 and (day in productivity["weekend"]):
            print("It's time to rest")
    else:
        print("Invalid day")
else:
    print("Invalid hour")    
""" 
# 🔹 3️⃣ Control de acceso con doble autenticación 🔐
# Crea un sistema de inicio de sesión que solicite usuario y contraseña.
# ✔️ Si la contraseña es correcta, debe solicitar un código de verificación (simulado).
# ✔️ El acceso solo se concede si ambas verificaciones son correctas.
""" 
password:int = 1234
user:str = "camila"
code:int = 1111
user_name = str(input("Enter your username: "))
if user_name == user:
    print("Username is correct")
    password_input = int(input("Enter your password: "))
    if password_input == password:
        print("Password is correct\n")
        print("A verification code has been sent to your email\n")
        code_user = int(input("Enter the verification code: "))
        if code_user == code:
            print("Access granted")
        else:
            print("Invalid verification code")
    else:
        print("Invalid password")
else: 
    print("Invalid username")
""" 
# 🔹 4️⃣ Calculadora de presupuesto mensual 💰
# Desarrolla un programa que pida ingresos y gastos fijos del usuario.
# 🔎 Luego, evalúa si puede ahorrar dinero y cuánto, o si necesita reducir gastos.
# ✅ Aplica buenas prácticas como modularidad y validaciones.
""" 
income = float(input("Please enter your monthly income: $"))
fixed_expenses = float(input("Please enter your monthly fixed expenses: $"))
savings = income - fixed_expenses
if savings > 0:
    print(f"Congratulations! You can save ${savings:.2f} this month.")
elif savings == 0:
    print("Your income and expenses are equal. You can't save or spend more.")
else:
    print(f"You need to reduce your expenses. You are in a deficit of ${abs(savings):.2f}.")
"""
# 🔹 5️⃣ Asistente de clima y vestimenta 🌦️
# Crea un programa que pida la temperatura y si está lloviendo o no.
# 🧥 Según los datos, sugiere qué ropa usar: abrigo, paraguas, ropa ligera, etc.
# Hazlo reutilizable para futuras ampliaciones.
"""
temperature = float(input("Enter the temperature: "))
raining = int(input("Is it raining? (yes = 1/ no = 2): "))
if temperature < 0:
    if raining >= 1:
        print("It's freezing! Wear a heavy coat and gloves and carry an umbrella")
    else:
        print("It's freezing! Wear a heavy coat and gloves")
elif temperature < 0 and temperature > 10:
    if raining >= 1 and raining <= 2:
        if raining == 1:
            print("It's cold and raining! Wear a coat, hat and carry an umbrella")
        else:
            print("It's cold! Only wear a coat and a hat")
elif temperature >= 10 and temperature < 20:
    if raining >= 1 and raining <= 2:
        if raining == 1:
            print("It's cool and raining! Wear a light jacket and carry an umbrella")
        else:
            print("The weather is cool. Wear a light jacket or sweater")
elif temperature >= 20:
    if raining >= 1 and raining <= 2:
        if raining == 1:
            print("It's warm and raining! Wear a t-shirt and carry an umbrella")
        else:
            print("The weather is warm. Wear a t-shirt or light clothing")
else:
    print("Invalid temperature input.")
"""
# 🔹 6️⃣ Sistema de registro de eventos con validación 🎟️
# Construye un programa que permita registrar asistentes a un evento.
# 👤 El sistema debe verificar que:
# - La edad del usuario sea adecuada
# - No se haya superado el límite de cupos disponibles
"""
people:list[int] = []
while len(people) < 3:

    age = int(input("Enter your age: "))
    if age >= 18:
        people.append(age)
        print("You have been registered successfully")
        print(f"Spots remaining: {3 - len(people)}\n")
    else:
        print("You must be at least 18 years old to register")            
print("\n The event is fully booked \n")
print("Registered attendees:")     
print(people)
"""
# 🔹 7️⃣ Detector de spam en correos electrónicos 📧
# Simula un sistema que detecte si un mensaje es spam.
# 🔍 Usa condiciones para verificar si el mensaje contiene palabras como:
# 
# "gratis",
# "gana dinero",
# "descuento exclusivo", etc.
# Si hay coincidencias, márcalo como spam.
"""
spam:list[str] = ["free","earn money","exclusive discount"]

mail = input("Enter the message: ").lower()

message_spam = False
    
for i in spam:
    if i in mail:
        message_spam = True
        break
    
if message_spam:
    print("You have emails in spam")
else:
    print("you don't have emails in spam")
"""
# Option 2
"""
spam = ["free", "earn money", "exclusive discount"]
# Simulated emails
messagee = {
    "alkomprar": "You should to pay a tv",
    "teacher": "Remember: today you have to send a homework",
    "you're winner": "You are the winner, free a computer and exclusive discount in a phone",
    "exitoio": "free a car"
}
mail = input("Enter the sender (alkomprar / teacher / you're winner / exitoio): ").lower()
# Get the message
if mail in messagee:
    content = messagee[mail].lower()
    # Check if any spam word is inside the content
    is_spam = False
    for word in spam:
        if word in content:
            is_spam = True
            break
    if is_spam:
        print("You have emails in spam")
    else:
        print("You don't have emails in spam")
else:
    print("Sender not found")
"""
# 🔹 8️⃣ Sistema de préstamos de biblioteca 📚
#Desarrolla un programa que permita a un usuario solicitar un libro.
#📚 Solo puede hacerlo si:
# - Tiene menos de 3 libros prestados
# - No tiene sanciones pendientes
"""
borrowed = int(input("Enter how many books you have borrowed: "))
penalty = input("Do you have pending sanctions? (yes/no): ").lower()
if borrowed >= 3:
    print("You can't borrow more books. You already have 3 or more")
elif penalty == "yes":
    print("You can't borrow books until you resolve your sanctions")
else:
    cal = 3-borrowed
    print(f"You can borrow {cal} more book(s)")
    book = int(input("Enter the book you want to borrow: "))
    if book > cal:
        print(f"You can't borrow {book} books. You can only borrow {cal} more.")
    else:
        borrowed += book
        print(f"You have successfully borrowed {book} book(s). Now you have {borrowed} books.")
"""        
# 🔹 9️⃣ Asistente de compras inteligentes 🛍️
# Crea un sistema que ayude al usuario a decidir si comprar un producto.
# Pide:
# - Precio del producto
# - Si hay descuentos
# - Presupuesto del usuario
#  💡 Luego indica si la compra es recomendable o no.
"""   
# Opcion 1
print("Welcome, this is a system where I recommend a product for you to buy \n")
product = str(input("Which product would you like to buy?: "))
price = float(input("What is you price?: "))
discount = int(input("How much is the discount?: "))
budget = float(input("Please enter your budget: "))

final_price = price - (price * discount / 100)

if final_price >= 200000 and discount >= 30:
    print(f"{product} is a good deal \n")
    if budget >= final_price:
        print(f"You can buy {product}!")
    else:
        print("You can't buy this product, your budget is too low.")
else:
    print("This product is not a good deal.")
# Opcion 2
print("Welcome! This system helps you decide if you should buy a product.\n")
product = input("Which product would you like to buy?: ")
price = float(input("What is the price?: "))
discount = int(input("How much is the discount (in %)? : "))
budget = float(input("Please enter your budget: "))

final_price = price - (price * discount / 100)
print(f"\nThe final price of {product} after a {discount}% discount is: {final_price}")
if final_price <= budget:
    print(f"Good news! You can buy the {product}.")
    if discount >= 20:
        print("This is a good deal!")
    else:
        print("But the discount is small.")
else:
    print(f"Sorry, you can't buy the {product}. Your budget is too low.")
"""   
# 🔹 🔟 Evaluador de contraseñas seguras 🔑
# Diseña un programa que verifique si una contraseña es segura.
# Debe cumplir con:
# 
# Al menos 8 caracteres
# Incluir números y letras
# No contener espacios
# 🛡️ Dale retroalimentación al usuario sobre cómo mejorar su contraseña.
"""   
password = input("Input a password: ")
# Verificar si la contraseña tiene al menos 8 caracteres
if len(password) >= 8:
    letter = False  # Variable para verificar si tiene letras
    number = False  # Variable para verificar si tiene números
    spaces = False  # Variable para verificar si tiene espacios

    # Recorrer cada carácter de la contraseña
    for char in password:
        if char.isalpha():  # Si el carácter es una letra
            letter = True
        if char.isdigit():  # Si el carácter es un número
            number = True
        if char == " ":  # Si el carácter es un espacio
            spaces = True
    # Verificar las condiciones
    if letter and number and not spaces:
        print("Password is secure!")
    else:
        # Dar retroalimentación sobre lo que falta
        if not letter:
            print("The password must contain at least one letter")
        if not number:
            print("The password must contain at least one number")
        if spaces:
            print("The password must not contain spaces")
else:
    print("The password must be at least 8 characters long")
"""   