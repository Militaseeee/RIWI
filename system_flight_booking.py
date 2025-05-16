import random
import string
import os
from datetime import time
import re # Regular expressions -> Este módulo permite trabajar con patrones de texto para buscar, validar o extraer partes de cadenas (strings)

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

# Validación de horarios al inicio
def is_valid_time(hours, minute):
    try:
        time(hours, minute)
        return True
    except ValueError:
        return False

# OJO: Queda afuera porque quiero que se ejecute automáticamente
# Validar todos los horarios al inicio
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

def view_flight_list(flight):
    
    print("------------------------------------------------------------")
    print("-> FLIGHTS AVAILABLE: ")
    print("------------------------------------------------------------\n")
    for code_flight, values in flight.items():
        print(f"{'Code flight:':20} {code_flight}")
        print(f"{'Descent:':20} {values['Descent']}")
        print(f"{'Destination:':20} {values['Destination']}")
        print(f"{'Seat:':20} {sorted(values['Seat'])}") # para mostrar en orden
        print(f"{'Calendar:':20} {values['Calendar'][0]:02d}:{values['Calendar'][1]:02d}")
        print("-" * 45) 

# Validación código vuelo (patrón "XX-999")
def valid_flight_code(code_flight):
    pattern = r'^[A-Z]{2}-\d{3}$'
    return bool(re.match(pattern, code_flight))

# Validación formato asiento (letra + número)
def valid_seat(seat):
    pattern = r'^[A-Z]\d+$'
    return bool(re.match(pattern, seat))


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
        if seat in info_flight["Seat"]: # acceso O(1)
            info_flight["Seat"].remove(seat) # eliminar en set O(1)
            info_flight["Occupaied"].add(seat) # añadir en set O(1)
            print(f"\nSeat {seat} successfully booked on flight {code_flight}.")
        elif seat in info_flight["Occupaied"]:
            print(f"\nSeat {seat} is already reserved on flight {code_flight}.")
        else:
            print(f"\nSeat {seat} is not available on flight {code_flight}.")
    else:
        print(f"\nFlight {code_flight} does not exist.")


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



def generate_with_txt(file_path, content):
    """
    Genera un reporte en un archivo de texto.

    Args:
        ruta_archivo: La ruta completa al archivo donde se guardará el reporte.
        contenido: El contenido del reporte (una cadena de texto).
    """
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



def generate_reports():
    
    flight_list = []
    
    for code_flight, values in flight.items():
        hours, minutes = values["Calendar"]
        flight_time = time(hours, minutes)
        flight_list.append((flight_time, code_flight, values))

    # Ordena la lista de vuelos por su horario de salida
    flight_list.sort()

    # Empieza a crear el reporte para guararlo en el txt
    report_lines = []
    
    for time_dt, code_flight, values in flight_list:
        line = (
            f"{code_flight} - {values['Descent']} to {values['Destination']} at {time_dt.strftime('%H:%M')}, "
            f"Available: {sorted(values['Seat'])}, Occupied: {sorted(values['Occupaied'])}"
        )
        report_lines.append(line)

    # Une todos los elementos de la lista report_lines (que contiene las líneas del reporte de vuelos)
    content = "\n".join(report_lines)
    # Crea un archivo de texto en la ruta
    generate_with_txt("../training_exercise/flight_report.txt", content)


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
            #generate_reports("report.txt", "Este es el contenido del reporte.")
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
