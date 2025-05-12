# 🧠 TEXT: A system that allows you to dynamically manage a store's inventory.

## 🔍 how does it work?

When you run the program you will find a dynamic menu where you will put the option you want to enter to perform the different processes.

![Captura desde 2025-05-12 10-42-15](https://github.com/user-attachments/assets/fba448ac-4bd7-4cae-910c-ffaa9c60273d)


---

## 💾 Register product

When registering a new product, it will ask you for 3 pieces of information

- 🛒 Enter the product name.
- 💲 Enter the product price.
- 🛍️ Enter how many products you want.

![Captura desde 2025-05-12 10-45-45](https://github.com/user-attachments/assets/c6df25d9-5d5f-4961-8a2d-0bebfe5b7ae1)

### ⚠️ problems that the program generates:
- Repeated product name.
- Empty data.
- Negative numbers.
- Letters in the price or quantity.

---

## 🔍 Search products

You can search for the product ONLY with the product name.

![Captura desde 2025-05-12 10-56-27](https://github.com/user-attachments/assets/6a1a4d68-d787-4a01-af52-3d7f2d21a3bf)

### ⚠️ problems that the program generates:
- Empty data.
- Products that do not exist.

If the product name doesn't exist in our database, you'll be given the option to add it. You decide if you want to.

- If you select "yes" it will ask for all the information.
- If you select "no" it will return you to the menu.

---

## 🔄 Update prices
For this option you can change the price of one of the products, for this you must enter the name of the product and then continue with the questions that the system asks you.

![Captura desde 2025-05-12 10-59-37](https://github.com/user-attachments/assets/59b0429a-2a8e-4217-a04a-0cf2ee49c41b)


### ⚠️ problems that the program generates:
- Empty data.
- Product name that does not exist.
- Negative numbers.
- Letters in the price.

---

## ⛔ Remove products
In this option you can delete a product.

![Captura desde 2025-05-12 11-06-12](https://github.com/user-attachments/assets/1d22fa6e-1754-4097-8b82-8452627b060e)


### ⚠️ problems that the program generates:
- Empty data.
- Product name that does not exist.

When deleting a product, you are given a recommendation in case you want to delete products that do not have a quantity in the inventory.

You will also be able to see messages where you confirm to delete that product.

---

## 📈 Calculate total inventory value
To show this action, the system counts all the inventory in the store and shows a report of everything it has plus the total price.

![Captura desde 2025-05-12 11-09-36](https://github.com/user-attachments/assets/e0cc4ad2-0a26-4803-8b87-960452005194)

---

##  📌 To run this program you need:
- Python 3 or higher
