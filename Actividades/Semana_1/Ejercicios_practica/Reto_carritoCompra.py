print("- - - SHOPPING CART WITH CONDIONALS - - -")

prod = str(input("Type product name: "))
price = float(input("Type product price: "))

if price < 50000:
    discount = price * 0.05
    subtraction = price - discount

    print(f"The amount of ${price} of the {prod} is recommended to pay by CASH, the total is {subtraction}")

elif price >= 50000 and price < 100000:
    print(f"The amount of ${price} of the {prod} is recommended to pay by CARD, the total is {price}")
else:
    cashback = price * 0.02
    print(f"The amount of ${price} of the {prod} is recommended to pay by TRANSFER, you have a 2% of cashback and is: {cashback}")