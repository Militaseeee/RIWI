# SUGGESTED EXERCISES:

# 1. Numbers greater than or equal to 1000
# Write a program that reads integers and determines how many of them are greater than or equal to 1000.
"""
n = int(input("Type the number of numbers you want to consult: "))

cont = 0
i = 0
show_number =  []

for i in range(n):
    num = int(input(f"Type {i+1} number: "))
    if num >= 1000:
        cont += 1
        show_number.append(num)

print(f"The numbers elder than 1000 are: {cont} and numbers are: {show_number}")
"""
# 2. Calculating the Areas of Triangles
# Create a program that reads n pairs of data (base and height) corresponding to triangles. It should display:
# The base, height, and area of ​​each triangle.
# The number of triangles with an area greater than 12.
"""
cont = 0

n = int(input("Type the number of numbers you want to consult: "))

for i in range(n):
    bas = float(input(f"{i + 1} Type the number for the base: "))
    alt = float(input(f"  Type the number for the height: "))

    area = (bas * alt) / 2

    print(f"The base is: {bas}, the height is: {alt} and the surface of triangle is: {area:.2f}")

    if area > 12:
        cont += 1
print(f"The amount of area of triangle is greater than 12 is: {cont}")
"""

# 3. Points on the Cartesian Plane
# Write a program that prompts you for coordinates (x, y) and determines how many points are in each quadrant (I, II, III, IV).
# Note: At startup, the program should prompt you for the number of points to process.
