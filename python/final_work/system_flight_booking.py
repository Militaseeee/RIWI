import random
import string
import os # Provides functions to interact with the operating system, sush as file and directory management
from datetime import time # From datetime import time  # Imports the 'time' class to represent specific times of day (hours and minutes)
import re # Regular expressions -> This module allows working with text patterns to search, validate, or extract parts of strings

# Dictionary containing flight information
flight = {
    "AA-100": {
        "Descent": "lima",
        "Destination": "bogota",
        "Seat": {"A1", "A2", "B1", "B2", "C1", "C2"},
        "Occupaied" : set(),
        "Calendar": (15, 30)
    },
    
    "XY-123": {
        "Descent": "medellin",
        "Destination": "berlin",
        "Seat": {"A1", "A2", "B1", "B2", "C1", "C2"},
        "Occupaied" : set(),
        "Calendar": (9, 15)
    },

    "WO-820": {
        "Descent": "cartagena",
        "Destination": "roma",
        "Seat": {"A1", "A2", "B1", "B2", "C1", "C2"},
        "Occupaied" : set(),
        "Calendar": (18, 1)
    },

    "JK-954": {
        "Descent": "bogota",
        "Destination": "budapest",
        "Seat": {"A1", "A2", "B1", "B2", "C1", "C2"},
        "Occupaied" : set(),
        "Calendar": (23, 40)
    },

    "FG-354": {
        "Descent": "cali",
        "Destination": "tokio","Seat": {"A1", "A2", "B1", "B2", "C1", "C2"},
        "Occupaied" : set(),
        "Calendar": (8, 22)
    }
}

# Validates if a given hour and minute form a valid time
def is_valid_time(hours, minute):
    try:
        time(hours, minute)
        return True
    except ValueError:
        return False

# Check and print any invalid time in the initial data
for code_flight, values in flight.items():
    hours, minutes = values["Calendar"]
    if not is_valid_time(hours, minutes):
        print(f"Invalid time in flight {code_flight}: {hours}:{minutes}")
        
# Function to evaluate if the product name is not empty
def evaluate_empty_code_flight(value_input: str, type: type):

    validate_code_flight = False # Initialize the control variable as False 

    while not validate_code_flight:

        value = type(input(value_input)) # Show user input message
        value = value.strip().lower()  # Remove whitespace and convert to lowercase
        if value == "":
            print("\n__________ ¡¡¡ WARNING !!! __________")
            print(f"\nThe product name must not be empty {value}\n")
        else:
            return value # Return the value here if it is not empty

# Displays the list of flights with their details
def view_flight_list(flight):
    
    print("------------------------------------------------------------")
    print("-> FLIGHTS AVAILABLE: ")
    print("------------------------------------------------------------\n")
    for code_flight, values in flight.items():
        print(f"{'Code flight:':20} {code_flight}")
        print(f"{'Descent:':20} {values['Descent']}")
        print(f"{'Destination:':20} {values['Destination']}")
        print(f"{'Seat:':20} {sorted(values['Seat'])}") # to show in order
        print(f"{'Calendar:':20} {values['Calendar'][0]:02d}:{values['Calendar'][1]:02d}")
        print("-" * 45) 

# Validates flight code format (e.g. "AA-123")
def valid_flight_code(code_flight):
    pattern = r'^[A-Z]{2}-\d{3}$'
    return bool(re.match(pattern, code_flight))

# Validates seat format (e.g. "A1")
def valid_seat(seat):
    pattern = r'^[A-Z]\d+$'
    return bool(re.match(pattern, seat))

# Handles seat reservation with all necessary validations
def reserve_seat():

    view_flight_list(flight)

    code_flight = evaluate_empty_code_flight("\nEnter the flight code you want to book: ", str).upper()
    
    if not valid_flight_code(code_flight):
        print("\n       __________ ¡¡¡ WARNING !!! __________")
        print("\nInvalid flight code format. It should be like (XX-999)")
        return

    seat = evaluate_empty_code_flight("\nEnter the seat you want to reserve: ", str).upper()
    if not valid_seat(seat): 
        print("\n              __________ ¡¡¡ WARNING !!! __________")
        print("\nInvalid seat format. It should be a letter followed by a number (A1)")
        return
    
    if code_flight in flight:
        info_flight = flight[code_flight]
        if seat in info_flight["Seat"]: # access O(1)
            info_flight["Seat"].remove(seat) # delete in set O(1)
            info_flight["Occupaied"].add(seat) # add in set O(1)
            print(f"\nSeat {seat} successfully booked on flight {code_flight}.")
        elif seat in info_flight["Occupaied"]:
            print(f"\nSeat {seat} is already reserved on flight {code_flight}.")
        else:
            print(f"\nSeat {seat} is not available on flight {code_flight}.")
    else:
        print(f"\nFlight {code_flight} does not exist.")

# Calculates and displays the occupancy percentage for each flight
def calculate_percentage(code_flight):

    divider:int = 6
    multiply: int = 100 
    percent_occupaied:float = 0.0

    print("\nOCCUPANCY PERCENTAGE PER FLIGHT")
    
    print("-" * 40)
    for code_flight, values in flight.items():
        occupaied = len(flight[code_flight]["Occupaied"])
        if occupaied == 0:
            percent_occupaied = 0
        else:
            percent_occupaied = (occupaied * multiply)/divider
        print(f"Flight {code_flight}: {percent_occupaied:.2f}% occupied")
    print("-" * 40)

# Generates a text file with the provided content
def generate_with_txt(file_path, content):

    try:
        # Crear la carpeta si no existe
        folder  = os.path.dirname(file_path)
        if folder  and not os.path.exists(folder ):
            os.makedirs(folder )

        # Escribir el archivo
        with open(file_path, "w", encoding="utf-8") as file:
            file.write(content)
        print(f"Report successfully generated at: {file_path}")
    except Exception as e:
        print(f"Error generating the report: {e}")

# Generates and writes a sorted report of flights
def generate_reports():
    
    flight_list = []
    
    for code_flight, values in flight.items():
        hours, minutes = values["Calendar"]
        flight_time = time(hours, minutes)
        flight_list.append((flight_time, code_flight, values))

    # Sort the list of flights by their departure time
    flight_list.sort()

    # Start creating the report to save it in the txt
    report_lines = []
    
    for time_dt, code_flight, values in flight_list:
        line = (
            f"{code_flight} - {values['Descent']} to {values['Destination']} at {time_dt.strftime('%H:%M')}, "
            f"Available: {sorted(values['Seat'])}, Occupied: {sorted(values['Occupaied'])}"
        )
        report_lines.append(line)

    # Join all the elements of the list report_lines (which contains the lines of the flight report)
    content = "\n".join(report_lines)
    # Create a text file at the path
    generate_with_txt("../training_exercise/flight_report.txt", content)

# Displays the main menu and handles user input
def menu():

    check:bool = True
    option:int = 0
    i:int = 0
    attempts: int = 3

    while check:

        print("\n\n------------------------------------------------------------")
        print("Exercise 1: Flight Reservation System")
        print("------------------------------------------------------------")

        print("\nMenu\n")

        print("1. Seat reservation with availability validation:")
        print("2. Calculation of the occupancy percentage per flight")
        print("3. Generation of a report in a text file with flights sorted by schedule")
        print("4. Log off")

        option = input("\nEnter the operation you want to perform (1-4): ")

        if option == "1":

            print("\nYou chose option 1: Seat reservation with availability validation\n")
            reserve_seat()
            continue
        elif option == "2":
            print("\nYou chose option 2: Calculation of the occupancy percentage per flight\n")
            calculate_percentage("AA-100")
            continue
        elif option == "3":
            print("\nYou chose option 3: Generation of a report in a text file with flights sorted by schedule\n")
            generate_reports()
            continue
        elif option == "4":
            print("\n  ... Exit of program ... \n")
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
