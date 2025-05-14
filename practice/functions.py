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

def get_average(): 

    average = 0.0

    for name, grade in grades.items():

        average = sum(grade) / len(grade)
        
        print(f"Student: {name} have a {average}")



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

def average_rating():

    calculate = 0.0
    for name_movie, value in movies.items():
        calculate = sum(value) / len(value)
        
        print(f"Movie: {name_movie} have a {calculate}")
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
def add_task(): pass
def complete_task(): pass
def filter_tasks(): pass

# 8. Digital Wallet
def add_expense(): pass
def total_spent(): pass
def expense_percentages(): pass

# 9. Pet Adoption Center
def add_pet(): pass
def find_by_species(): pass
def older_than(): pass

# 10. Gym Membership System
def register_member(): pass
def change_plan(): pass
def unpaid_members(): pass
