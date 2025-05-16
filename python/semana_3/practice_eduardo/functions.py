# 1. Library Book Tracker
library = {}
def add_book(title, author, pages): 
    
    library[title] = [author, pages]

def find_book(title): 

    if title in library:
        print(f'Title: {title}\nAuthor: {library[title][0]}\nPages: {library[title][1]}')
    elif title == "Unknown":
        return "Book not found."

def show_books():

    for key, values in library.items():
        print(f"Book:{key}, author:{values[0]}, pages:{values[1]}")

# 2. Student Grade Manager
grades = {}
def add_student(name):
    grades[name] = []

def add_grade(name, grade): 

    grades[name].append(grade)

def get_average(name): 

    average = 0.0

    for name, grade in grades.items():

        average = sum(grade) / len(grade) 
    return average

# 3. Restaurant Menu Editor
menu = {}
def add_dish(name_food, price, availability):
    menu[name_food] = [price, availability]

def change_availability(name_food, availability): 

    price = menu[name_food][0]
    menu[name_food] = [price, availability]

def total_available_price(): 

    calculate = 0.0
    for name_food, values in menu.items():
        if values[1] == True:
            calculate += menu[name_food][0]
    return calculate

# 4. Warehouse Box Counter
warehouse = {}
def add_box(box, amount): 

    warehouse[box] = [amount]

def update_quantity(box, amount): 

    add = warehouse[box][0]
    add += amount
    warehouse[box] = [add]

def has_enough(box, amount): 

    if warehouse[box][0] >= amount:
        return True
    else:
        return False

# 5. Movie Rating System
movies = {}
def add_movie(name):

    movies[name] = []

def rate_movie(name, grade): 

    if grade >= 0 and grade <= 5:
        movies[name].append(grade)
    else:
        print("That should be between 0 to 5")

def average_rating(name_movie):

    calculate = 0.0
    for name_movie, value in movies.items():
        calculate = sum(value) / len(value)    
    return calculate

# 6. Online Course Tracker
courses = {}
def add_course(title, duration, register): 

    courses[title] = [duration, register]

def update_enrollment(title, register): 

    update = courses[title][1]
    update += register
    courses[title] = [update]

def filter_by_hours(hours): 
    courses2 = []
    for title, values in courses.items():
        if values[0] >= hours:
            courses2.append(title)
    return courses2

# 7. To-Do List Organizer
todos = {}

def add_task(homeworks, priority): 
    
    todos[homeworks] = [priority, "done"]
    
def complete_task(homeworks): 
    
    todos[homeworks][1] = "completed"
    
def filter_tasks(priority, status):
    filtered = []

    for key_tasks, value in todos.items():
        if value[0] == priority and value[1] == status:
            filtered.append(key_tasks)
    return filtered

# 8. Digital Wallet
wallet = {}
def add_expense(name_spend, spend):
    
    wallet[name_spend] = [spend]
    
def total_spent(): 
    
    add = 0.0
    for key, count in wallet.items():
        add += count[0]
    return add
 
def expense_percentages():
    thotal_spend = total_spent()
    percentages = {}
    for name, value in wallet.items():
        percentages[name] = (value[0] * 100)/thotal_spend
    return percentages    

# 9. Pet Adoption Center
pets = {}
def add_pet(name, specie, age):
    
    pets[name] = [specie, age]
    
def find_by_species(specie): 
    #p -> search
    p = {}
    for key, values in pets.items():
        if values[0] == specie:
            p["name"] = key
    return p
    
def older_than(age): 
    
    older = []
    for name, values in pets.items():
        if values[1] >= age:
            older.append({"name": name})
    return older        

# 10. Gym Membership System
members = {}
def register_member(name, member, pay): 
    
    members[name] = [member, pay]
    
def change_plan(name, new_member): 
  
    if name in members:
        members[name][0] = new_member
        
def unpaid_members(): 
    unpaid = []
    for name, values in members.items():
        if values[1] == "late":
            unpaid.append(name)
    return unpaid
