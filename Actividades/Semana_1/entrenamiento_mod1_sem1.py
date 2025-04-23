# DO-WHILE loop that repeats until the user decides to enter or exit.
while True:

    print(" - - - | RIWI SUPERMARKET | - - -")
    print ("")

    # Line of code where the user is asked if they want to buy something.
    condition = int(input("Will you buy something? | (Type 1 to buy or 2 to exit)" ))

    # Condition used to navigate the menu (according to what the user enters).
    if condition == 1:

        # Data type where information about the product name is stored.
        name = str(input("Type the name of the product: "))

        price = float(input("Type the price of the product: "))

        # WHILE loop where evaluates that the value is not less than 0.
        while price < 0:
            print(f"{price} ERROR!!! It is a negative number, You should type a positive number")
            # repeat the message until people get a positive number.
            price = float(input("Type the price of the product: "))

        amount = float(input("Type the amount of prices purchases: ")) 

        while amount < 0:
            print(f"{amount} ERROR!!! It is a negative number, You should type a positive number")
            amount = float(input("Type the amount of prices purchases: "))

        # Data type where the percentage of the product applies.
        percent = int(input("Type the discount percentage about the products: "))

        # WHILE loop where the condition is set that it can't be a number less than 0 and more than 100.
        while percent < 0 or percent > 100:
            print(f"{percent}% ERROR!!! It is not in range, type (between 0 - 100) without %")
            percent = int(input("Type the discount percentage about the products: "))

        # A data type is created to store the multiplication of the price and the amount of the product.
        multi = float(price * amount) 
        # A data type is created to store the division of the price by applying the discount 
        discount = float(multi * percent / 100)
        # A data type is created to perform a subtraction and show the total amount that the person should pay.
        total = float(multi - discount)
        # Python statement to display a number with 2 decimal places.
        numRed = round(total, 2)

        # Message to show the total result of the product with its name.
        print(f"The discount of {percent}% on <{name}> is: {discount} and total price is: {numRed}")
        print("Thanks for you purcharse!!!")

        break
    
    elif condition == 2:
        print("See you later!!!")
        break
    else:
        print("Error!! Type 1 or 2")
        print(" ")