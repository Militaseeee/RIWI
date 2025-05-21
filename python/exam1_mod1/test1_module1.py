# Dictionary with burned data
product_inventory = {
    "yogurt": (25000, 6),
    "bread": (5300, 10),
    "cheese": (9500, 0),
    "ham": (12100, 7),
    "eggs": (855, 31),
    "soda": (2000, 0),
}

def evaluate_before_info_products(product_inventory):
    val: bool = False
    for name, (price, quantity) in product_inventory.items():
        if price <=0 or quantity < 0:
            print("\n__________ ¡¡¡ WARNING !!! __________")
            print(f"Product '{name}' has invalid data. Price and quantitymust be positive numbers greater than zero")
            val = True
    return val

# Function to evaluate if the product name is not empty
def evaluate_empty_product(value_input: str, type: type):

    validate_product = False # Initialize the control variable as False 

    while not validate_product:

        value = type(input(value_input)) # Show user input message
        value = value.strip().lower()  # Remove whitespace and convert to lowercase
        if value == "":
            print("\n__________ ¡¡¡ WARNING !!! __________")
            print(f"\n  The product name must not be empty {value}\n")
        else:
            return value # Return the value here if it is not empty
        
# Function to evaluate the price and quantity where the user cannot enter a letter or a negative number
def evaluate_numeric_type(input_prompt: str, data_type: type):

    validate_num = False 

    while not validate_num:

        try:
            value = data_type(input(input_prompt))  # Requests the input of a value of the specified type (float or int)
            if value < 0:
                print("\n      _____________ !!! WARNING !!! _____________")
                print(f"\nThe value must be greater than zero, but you entered: {value}\n")
            else:
                return value  # Return the value only if it is valid
        except ValueError:
            print("\n--------------------------------------------")
            print("     Please enter a valid numeric value")
            print("--------------------------------------------\n")

# Function to check if the product name exists
def check_product_exists(product_inventory, name):

    name_lower = name.lower()  # This part converts the product name that the user enters to lowercase
    for product in product_inventory.keys(): # Browse all products names (dictionary keys) in the inventary
        title_lower = product.lower()
        if name_lower == title_lower:  # It helps me compare the entered product name with each stored product name
            return True
    return False

# Function to validate only two options
def evaluate_option(number: str):
    
    evaluate_num = False 
    
    while not evaluate_num:
        
        option = input(number)
        
        if option == "1" or option == "2":
            return option
        else:
            print("\n---------------------------------------------------")
            print("    Invalid search option. Please select 1 or 2")
            print("---------------------------------------------------\n")

# Function to give a recommendation of which would be preferable to eliminate
def recommend_remove_product(product_inventory):

    recommended = False  # This part is to know if there are products with quantity 0

    print("\n------------------------------------------------------------------------------------")
    print("-> RECOMMENDATION:\n")
    
    # Cycle for, where I go through all the products in the inventory
    for product, (price, quantity) in product_inventory.items():

        # Conditional to say that if the quantity is equal to 0, recommend that it is better to eliminate them
        if quantity == 0:
            print(f"---> I recommend that you remove the product '{product}' because it has {quantity} quantity")
            recommended = True

    if not recommended:
        print("---> All products have stock. No recommendations for removal")
    print("------------------------------------------------------------------------------------")

# Function to display all the products entered in a list
def view_product_list(product_inventory):
    
    print("------------------------------------------------------------")
    print("-> PRODUCTS IN THE INVENTORY: \n")
    for name, (price, quantity) in product_inventory.items():
        price, quantity = product_inventory[name]  # Unpack the price and quantity of the product
        print(f" - {name} | Price = {price:,.2f} | Quantity = {quantity}")
    print("------------------------------------------------------------")

# Function to add products
def add_products(product_inventory, name, price, quantity):

    product_inventory[name] = (price, quantity)  # Adds the product to the inventory with name as the key
    print("\n------------------------------------------------------------------------------------")
    print("-> ADD: ")
    print(f"\nThe product '{name}' with price '{price:,.2f}' and a quantity of '{quantity}' has been successfully added.")
    print("------------------------------------------------------------------------------------")

# Function to query product information by its name
def search_products(product_inventory, name):

    product_found = False

    print("\n------------------------------------------------------------------------------------")
    print("-> SEARCHER: ")
    # If the product exists in the inventory, show the product details, otherwise show a message
    if name in product_inventory:
        print(f"\nThe product '{name}' has a price of '{product_inventory[name][0]:,.2f}' and a quantity of '{product_inventory[name][1]}'")
    else:
        print("\n    _____________ !!! WARNING !!! _____________")
        print(f"\nThe product '{name}' does not exist in the inventory\n")

        # If you don't find the name it gives you the opportunity to add the product
        if not product_found:
        
            register_new_product = int(input("Would you like to register a new product? | 1. Yes | or | 2. No |: "))
        
            if register_new_product == 1:

                #name = evaluate_empty_product("Enter the product name: ", str)
                price = evaluate_numeric_type("Enter the product price: ", float)
                quantity = evaluate_numeric_type("Enter how many products you want: ", int)

                add_products(product_inventory, name, price, quantity)

            else: 
                print("\nOk, you can continue browsing the menu")
    print("------------------------------------------------------------------------------------\n")

# Function to update the price of a product that already exists in the inventory
def update_price(product_inventory, name):  
    
    new_price: float = 0
    
    if name in product_inventory:
        new_price = evaluate_numeric_type("Enter the new price you want to change for the product: ", float)  # Requests a new price
        existing_quantity = product_inventory[name][1]  # Gets the existing quantity of the product
        product_inventory[name] = (new_price, existing_quantity)  # Updates the product price
        print("\n------------------------------------------------------------------------------------")
        print("-> UPDATE: ")
        print(f"\nThe product '{name}' now has a new price of '{new_price:,.2f}' and a quantity of '{existing_quantity}'")  # Shows the update message
        print("------------------------------------------------------------------------------------")
    else:
        print("\n   _____________ !!! WARNING !!! _____________")
        print(f"\nThe product '{name}' does not exist in the inventory")

# Function to remove a product from the inventory
def remove_product(product_inventory, name):

    opc_delete:int = 0
    
    if name in product_inventory:

        opc_delete = evaluate_option(f"\nAre you sure you want to delete the product '{name}'? Select: | 1. Yes | or | 2. No |: ")
        if opc_delete == "1":
            del product_inventory[name]  # Removes the product from the inventory
            print("\n--------------------------------------------------------------------")
            print(f"           The product '{name}' was successfully removed!")
            print("--------------------------------------------------------------------\n")
        elif opc_delete == "2":
            print(f"\nOk, the product '{name}' remains in inventory\n")
    else:
        print(f"\nThe product '{name}' does not exist in the inventory\n")

# Function to calculate the total value of the entire inventory
def calculate_total_value(product_inventory):

    total_inventory: float = 0.0
    
    if not product_inventory:

        print("\n__________ !!! WARNING !!! __________\n")
        print("The inventory is empty :c")

    else:

        for name, value in product_inventory.items():

            price = value[0]
            quantity = value[1]

            multiply = quantity * price

            total_inventory += multiply

            print(f"  Product: {name}")
            print(f"  Quantity: {quantity}")
            print(f"  Price: ${price:,.2f}")
            print(f"  Subtotal: ${multiply:,.2f}")
            print("-" * 45)

        print(f"\nThe total replacement value of the entire inventory is: $ {total_inventory:,.2f}\n")


# Function that shows the menu and allows the user to interact with the program
def menu():
    
    opc: int = 0
    attempts = 3  # Maximum number of attempts allowed by the program
    i = 0         # Counter for attempts to exit the program
    check: bool = True # Control variable for the loop
        
    print("-----------------------------------------------------------------------")
    print("- - - Exam 1: Inventory Management with functions and collections - - -")
    print("-----------------------------------------------------------------------")
    print("\n         Welcome to the RIWI EXPRESS store program \n")
    print("-----------------------------------------------------------------")

    while check:
        print("\n ****** MENU: ****** \n")
        print("1. Add products")
        print("2. Search products")
        print("3. Update prices")
        print("4. Remove products")
        print("5. Calculate total inventory value")
        print("6. Exit")
        
        opc = input("\nEnter the function you wish to perform: (1-6) ")
        
        if opc == "1":
            
            print("\n*****************************************")
            print("  You chose option 1: Add products ")
            print("*****************************************\n")
            
            # Calls functions to ensure no issues when entering values
            name = evaluate_empty_product("Enter the product name: ", str)

            # Function to check if the product is already in inventory
            if check_product_exists(product_inventory, name):
                
                print("\n __________________ ¡¡¡ WARNING !!! __________________")
                print(f"\nThe product '{name}' is already registered in the inventary")
                print("\n---------------------------------------------------")
                print("     You can't register the same product twice.")
                print("---------------------------------------------------\n")
                continue  

            price = evaluate_numeric_type("Enter the product price: ", float)
            quantity = evaluate_numeric_type("Enter how many products you want: ", int)

            # Calls the function to add products
            add_products(product_inventory, name, price, quantity)
                
            continue
                    
        elif opc == "2":
            
            print("\n*****************************************")
            print("  You chose option 2: Search products ")
            print("*****************************************\n")
            
            name = evaluate_empty_product("Enter the name of the product you want to search for: ", str)
            search_products(product_inventory, name)
            
            continue
        
        elif opc == "3":
            
            print("\n*****************************************")
            print("  You chose option 3: Update prices ")
            print("*****************************************\n")
            
            # Calls the function to display the inventory list
            view_product_list(product_inventory)
            
            name = evaluate_empty_product("\nEnter the name of the product you want to update: ", str)
            update_price(product_inventory, name)
            
            continue
        
        elif opc == "4":
            
            print("\n*****************************************")
            print("  You chose option 4: Remove products ")
            print("*****************************************\n")
            
            view_product_list(product_inventory)

            recommend_remove_product(product_inventory)

            opc_question = evaluate_option("\nDo you want to delete a product? Select: | 1. Yes | or | 2. No |: ")
            
            if opc_question == "1":
                name = evaluate_empty_product("\nEnter the name of the product you want to remove: ", str)
                remove_product(product_inventory, name)

            elif opc_question == "2":
                print("\nOk, you can continue browsing the menu")
                continue

            continue
        
        elif opc == "5":

            print("\n*****************************************")
            print("  You chose option 5: Calculate total inventory value ")
            print("*****************************************\n")

            calculate_total_value(product_inventory)
            
            continue

        elif opc == "6":
            print("\n . . . Exiting the program . . . \n")
            check = False
            break
        else:
            i += 1
            if i < 3:
                print("\n**********************************************")
                print(f"      Invalid option, you have {attempts - i} attempts left")
                print("**********************************************")
            else:
                print("\n**********************************************")
                print("   !!! You have run out of attempts !!! ")
                print("********************************************** \n")

                print("**************************************************")
                print("\n    E X I T I N G   T H E   P R O G R A M  \n")
                print("**************************************************")

                check = False
                break

if evaluate_before_info_products(product_inventory):
    print('Bye bye, xoxo')
else:
    menu()
