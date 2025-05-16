library= {
    "cien años de soledad": {
        "author": "gabriel garcia marquez",
        "genre": "realismo magico",
        "year": 1967,
        "quantity": 5,
        "replacement_price": 50000
    },
    "harry potter": {
        "author": "rowling",
        "genre": "fantasia",
        "year": 1997,
        "quantity": 40,
        "replacement_price": 140000
    },
    "el principito": {
        "author": "antoine de saint exupery",
        "genre": "literatura infantil",
        "year": 1943,
        "quantity": 12,
        "replacement_price": 27000
    },
    "el señor de los anillos": {
        "author": "tolkien",
        "genre": "literatura fantastica",
        "year": 1954,
        "quantity": 23,
        "replacement_price": 97000
    },
    "el codigo da vinci": {
        "author": "dan brown",
        "genre": "novela",
        "year": 2003,
        "quantity": 7,
        "replacement_price": 78000
    }
}

def evaluate_text_type(input_text: str, type: type):
    
    evaluate_text = False 

    while not evaluate_text:
        
        value = type(input(input_text))
        value = value.lower()
        
        if value == "":
            print("\n_____________ ¡¡¡ WARNING !!! _____________")
            print("\nIt cannot be left empty\n")
        else:
            return value


def evaluate_numeric_type(input_number: int, type: type):
    
    evaluate_num = False 

    while not evaluate_num:
        
        try: 
            value = type(input(input_number))
            
            if value <= 0:
                print("\n_____________ ¡¡¡ WARNING !!! _____________")
                print(f"\nThe value should be greater than zero and you put: {value}\n")
            else:
                return value
        except ValueError:
            print("\n--------------------------------------------")
            print("Please enter a valid numeric value")
            print("--------------------------------------------\n")

def evaluate_date_type(input_number: int, type: type):
    
    evaluate_date = False 

    while not evaluate_date:
        
        try: 
            value = type(input(input_number))
            
            if value < 1800 or value > 2024:
                print("\n_____________ ¡¡¡ WARNING !!! _____________")
                print("Invalid year. Enter a value between 1800 and 2024.")
                print("--------------------------------------------\n")
            else:
                return value
        except ValueError:
            print("\n--------------------------------------------")
            print("\nInvalid year format. Enter a numeric value.")          

def check_book_exists(library, book_name):
    
    book_name_lower = book_name.lower()  # Esta parte convierte el nombre del libro que el usuario ingresa a minúsculas

    for book in library.keys(): # Recorre todos los títulos de los libros (las claves del diccionario) en la biblioteca
        title_lower = book.lower()
        
        if book_name_lower == title_lower:  # Me ayuda a compara el nombre del libro ingresado con cada título de libro almacenado
            return True
    
    return False

def evaluate_search_option(number: str):
    
    evaluate_num = False 
    
    while not evaluate_num:
        
        option = input(number)
        
        if option == "1" or option == "2":
            return option
        else:
            print("\n---------------------------------------------------")
            print("Invalid search option. Please select 1 or 2")
            print("---------------------------------------------------\n")

def view_books_list(library):
    
    print("------------------------------------------------------------")
    print("-> BOOKS IN INVENTORY: ")
    print("------------------------------------------------------------\n")
    for name_book, values in library.items():
        print(f"{'Book name:':20} {name_book}")
        print(f"{'Author:':20} {values['author']}")
        print(f"{'Genre:':20} {values['genre']}")
        print(f"{'Year:':20} {values['year']}")
        print(f"{'Quantity Available:':20} {values['quantity']}")
        print(f"{'Replacement Price:':20} ${values['replacement_price']}")
        print("-" * 45) 
    

def register_book(library, name_book, author, genre, year, quantity, replacement_price):

    library[name_book] = {
        "author": author,
        "genre": genre,
        "year": year,
        "quantity": quantity,
        "replacement_price": replacement_price
    }
    
    print("\n------------------------------------------------------------------------------------")
    print("-> REGISTER: ")
    print(f"\nThe book '{name_book}' by author '{author}', genre '{genre}', published in the year '{year}', with a quantity of '{quantity}' units and a replacement price of '{replacement_price}', has been successfully added.")
    print("------------------------------------------------------------------------------------")

def search_books(library, search_value, opc_search):
    
    search_value = search_value.lower()
    book_found = False

    for name_book, values in library.items():
    
        if opc_search == "1":
            if search_value == name_book.lower(): # Me ayuda a buscar el libro
                print("\n------------------------------------------------------------------------------------")
                print("-> BOOK FOUND: \n")
                print(f"Book name: {name_book}")
                print(f"Author: {values['author']}")
                print(f"Genre: {values['genre']}")
                print(f"Year: {values['year']}")
                print(f"Quantity Available: {values['quantity']}")
                print(f"Replacement Price: {values['replacement_price']}")
                print("------------------------------------------------------------------------------------\n")
                
                book_found = True
                break
               
        elif opc_search == "2":
            if search_value == values['author'].lower():
                print("\n------------------------------------------------------------------------------------")
                print("-> BOOK FOUND: \n")
                print(f"Book name: {name_book}")
                print(f"Author: {values['author']}")
                print(f"Genre: {values['genre']}")
                print(f"Year: {values['year']}")
                print(f"Quantity Available: {values['quantity']}")
                print(f"Replacement Price: {values['replacement_price']}")
                print("------------------------------------------------------------------------------------\n")
                
                book_found = True
                break
                
    if not book_found:
        
        print("\n__________________ ¡¡¡ WARNING !!! __________________\n")
        print("That item is not available in our inventory\n")
        
        register_new_book = int(input("Would you like to register a new book? | 1. Yes | or | 2. No |: "))
        
        if register_new_book == 1:
            
            name_book = evaluate_text_type("\nEnter the name of the book: ", str)
            author = evaluate_text_type("Enter the author of the book: ", str)
            genre = evaluate_text_type("Enter the genre that belongs to the book: ", str)
            year = evaluate_date_type("Enter the year of publication of the book: ", int)
            quantity = evaluate_numeric_type("Enter the quantity of the book: ", int)
            replacement_price = evaluate_numeric_type("Enter price of book: ", float)
            
            register_book(library, name_book, author, genre, year, quantity, replacement_price)

        else: 
            print("\nOk, bye c:")

            
def update_book(library, name_book):
            
    if check_book_exists(library, name_book):
        
        print("\n*****************************************************")
        print("     Book found! Enter what you want to update: ")
        print("*****************************************************\n")

        update_option = evaluate_search_option("Select which one you want to modify: | 1. Quantity | or | 2. Replacement Price |: ")
    
        if update_option == "1":
            
            new_quantity = evaluate_numeric_type("Enter the new quantity: ", int)
            library[name_book]["quantity"] ==new_quantity
            
            print("\nQuantity updated successfully!")
            print("\n---------------------------------------------------")
            print("-> UPDATE: ")
            print(f"New quantity for '{name_book}' is {new_quantity}.")
            print("---------------------------------------------------\n")
           
        elif update_option == "2":
            
            new_replacement_price = evaluate_numeric_type("Enter the new price: ", float)
            library[name_book]["replacement_price"] = new_replacement_price
            
            print("\nPrice updated successfully!")
            print("\n---------------------------------------------------")
            print("-> UPDATE: ")
            print(f"New price for '{name_book}' is {new_replacement_price}.")
            print("---------------------------------------------------\n")
        
    else:
        print("\n__________________ ¡¡¡ WARNING !!! __________________\n")
        print("      That book is not available in our inventory\n") 

def delete_book(library, name_book):
    
    if name_book in library:
        del library[name_book]
        print("\nThe book was successfully deleted!")
    else:
        print(f"\nThe book '{name_book}' does not exist in the inventory \n")
        
def generate_reports(library):
    
    total_inventory:float = 0.0
    
    chose_option = evaluate_search_option("Select which report you want to generate: | 1. Total Inventory | or | 2. Show Oldest Book |: ")
    
    if chose_option == "1":

        for book_name, value in library.items():

            quantity = value['quantity']
            replacement_price = value['replacement_price']

            multiply = quantity * replacement_price

            total_inventory += multiply

            print(f"Book: {book_name}")
            print(f"  Quantity: {quantity}")
            print(f"  Replacement Price: ${replacement_price:,.2f}")
            print(f"  Subtotal: ${multiply:,.2f}")
            print("-" * 45)

        print(f"\nThe total replacement value of the entire inventory is: $ {total_inventory:.2f}\n")
        
    elif chose_option == "2":
        
        genre_books = {}
        
        # Recorro cada libro en la biblioteca y saco género y año de publicación
        for book_name, value in library.items():
            genre = value['genre']
            year = value['year']
            
            # Si ese género aún no existe en genre_books, lo creas como una lista vacía
            # Así aseguras que cada género tendrá su propia lista donde irás metiendo libros
            if genre not in genre_books:
                genre_books[genre] = []
        
            genre_books[genre].append({'name': book_name, 'year': year})

        # Por cada género, encontrar el más antiguo y el más reciente
        for genre, books in genre_books.items():
            oldest_book = books[0]
            newest_book = books[0]

            # Comparar cada libro en la lista
            for book in books:
                if book['year'] < oldest_book['year']:
                    oldest_book = book
                if book['year'] > newest_book['year']:
                    newest_book = book

            print(f"\nGenre: {genre}")
            print(f"  Oldest Book: {oldest_book['name']} ({oldest_book['year']})")
            print(f"  Newest Book: {newest_book['name']} ({newest_book['year']})")


     
        
def menu():
    
    check:bool = True
    opc:int = 0
    i:int = 0
    attempts:int = 3
    
    while check:
        
        print("\n************************************")
        print("Book Management System for a Library")
        print("************************************\n")
        
        print("\n ****** MENU: ****** \n")
        print("1. Register new books: ")
        print("2. Search for books in the catalog: ")
        print("3. Update information: ")
        print("4. Delete obsolete books: ")
        print("5. Generate reports: ")
        print("6. Log off: ")
        
        opc = input("\nEnter the operation you want to perform (1-6): ")
        
        if opc == "1":
            
            print("\n*****************************************")
            print("  You chose option 1: Register new books ")
            print("*****************************************\n")
            
            name_book = evaluate_text_type("Enter the name of the book: ", str)

            if check_book_exists(library, name_book):
                
                print("\n__________________ ¡¡¡ WARNING !!! __________________")
                print(f"\nThe book '{name_book}' is already registered in the catalog")
                print("\n---------------------------------------------------")
                print("      You can't register the same book twice.")
                print("---------------------------------------------------\n")
                continue  
            
            author = evaluate_text_type("Enter the author of the book: ", str)
            genre = evaluate_text_type("Enter the genre that belongs to the book: ", str)
            year = evaluate_date_type("Enter the year of publication of the book: ", int)
            quantity = evaluate_numeric_type("Enter the quantity of the book: ", int)
            replacement_price = evaluate_numeric_type("Enter price of book: ", float)
            
            register_book(library, name_book, author, genre, year, quantity, replacement_price)
            continue
            
        elif opc == "2":
            
            print("\n****************************************************")
            print("  You chose option 2: Search for books in the catalog ")
            print("****************************************************\n")

            opc_search = evaluate_search_option("To search for a book, select: | 1. book name | or | 2. author |: ")
            
            if opc_search == "1":
                search_term = evaluate_text_type("\nEnter the name of the book you want to search: ", str)
                search_books(library, search_term, opc_search)

            elif opc_search == "2":
                search_term = evaluate_text_type("\nEnter the author you want to search: ", str)
                search_books(library, search_term, opc_search)

            continue
        
        elif opc == "3":
            
            print("\n*****************************************")
            print("  You chose option 3: Update information ")
            print("*****************************************\n")
            
            print("\nFirst, I will show you the inventory of all the books in the library: \n")
            view_books_list(library)
            
            name_book = evaluate_text_type("\nEnter the name of the book you want to update: ", str)
            update_book(library, name_book)
            
            continue
        
        elif opc == "4":
            
            print("\n********************************************")
            print("  You chose option 4: Delete obsolete books ")
            print("********************************************\n")
            
            print("\nFirst, I will show you the inventory of all the books in the library: \n")
            view_books_list(library)
            
            name_book = evaluate_text_type("\nEnter the name of the book you want to delete: ", str)
            delete_book(library, name_book)
            
            continue
        
        elif opc == "5":
            
            print("\n*****************************************")
            print("  You chose option 5: Generate reports ")
            print("*****************************************\n")
            
            generate_reports(library)
            
            continue
            
        elif opc == "6":
            
            print("\n*****************************************")
            print("  ... Exit of program ... ")
            print("*****************************************\n")
            
            check = False
            break   
        
        else: 
            i += 1
            if i < 3: 
                print("\n*****************************************")
                print(f"   Invalid option, you have {attempts - i} attempts")
                print("*****************************************\n")
            else:
                print("\n*************************************************")
                print("  The number of attempts has been exhausted !!! ")
                print("************************************************* \n")

                print("**************************************************")
                print("\n           GETTING OUT OF THE PROGRAM  \n")
                print("**************************************************")

                check = False
                break

menu()
            
            