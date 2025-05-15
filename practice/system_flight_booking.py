import random
import string

flight = {
    "AA-100": {
    "origen": ["lima", "medellin", "cartagena", "guajira", "cali", "bogota"],
    "destino": ["manizales", "berlin", "cartagena", "roma", "tokio", "budapest"],
    "seat": ["A1", "A2", "B1", "B2", "C1", "C2"],
    "horario": (15, 30)
    }
}

def generate_system_flight():
    doble_letter:str = ""
    numbers:str = ""
    code_flight:str = ""


    doble_letter = random.choice(string.ascii_uppercase)+(random.choice(string.ascii_uppercase))
    numbers = str(random.randint(100,999))
    code_flight = doble_letter+"-"+numbers
    return code_flight



def reserve_seat(flight, seat):

    i:int = 0
    info_flight = []

    code_flight = generate_system_flight()

    for i in range(0,5):
        
        print(generate_system_flight())
        flight[info_flight] = 

    if code_flight in flight:
        flight = flight[code_flight]
        if seat in flight["seats"]:
            flight["seats"].remove(seat)
            print(f"Seat {seat} successfully booked on flight {code_flight}.")
        else:
            print(f"Seat {seat} is not available on flight {code_flight}.")
    else:
        print(f"Flight {code_flight} does not exist.")



reserve_seat("AA-100", "B1")



    

""" def menu():

    check:bool = True
    option:int = 0
    i:int = 0
    attempts: int = 3

    while check:

        print("\nExercise 1: Flight Reservation System\n")

        print("----------------------------")
        print("Menu\n")

        print("1. Seat reservation with availability validation:")
        print("2. Calculation of the occupancy percentage per flight")
        print("3. Generation of a report in a text file with flights sorted by schedule")
        print("4. Log off")

        option = print("\nEnter the operation you want to perform (1-4): ")

        if option == "1":
            print("You chose option 1: Seat reservation with availability validation")
        
        elif option == "2":
            print("You chose option 2: Calculation of the occupancy percentage per flight")
            break
        elif option == "3":
            print("You chose option 3: Generation of a report in a text file with flights sorted by schedule")
            break
        elif option == "4":
            print("  ... Exit of program ... ")
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
 """