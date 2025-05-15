import random
import string
import os

flight = {
    "AA-100": {
    "Descent": "lima",
    "Destination": "bogota",
    "Seat": ["A1", "A2", "B1", "B2", "C1", "C2"],
    "Occupaied" : [],
    "Calendar": (15, 30)
    },
    
    "XY-123": {
    "Descent": "medellin",
    "Destination": "berlin",
    "Seat": ["A1", "A2", "B1", "B2", "C1", "C2"],
    "Occupaied" : [],
    "Calendar": (9, 15)
    },

    "WO-820": {
    "Descent": "cartagena",
    "Destination": "roma",
    "Seat": ["A1", "A2", "B1", "B2", "C1", "C2"],
    "Occupaied" : [],
    "Calendar": (18, 1)
    },

    "JK-954": {
    "Descent": "bogota",
    "Destination": "budapest",
    "Seat": ["A1", "A2", "B1", "B2", "C1", "C2"],
    "Occupaied" : [],
    "Calendar": (23, 40)
    },

    "FG-354": {
    "Descent": "cali",
    "Destination": "tokio",
    "Seat": ["A1", "A2", "B1", "B2", "C1", "C2"],
    "Occupaied" : [],
    "Calendar": (8, 22)
    }
}

def view_flight_list(flight):
    
    print("------------------------------------------------------------")
    print("-> FLIGHTS AVAILABLE: ")
    print("------------------------------------------------------------\n")
    for code_flight, values in flight.items():
        print(f"{'Code flight:':20} {code_flight}")
        print(f"{'Descent:':20} {values['Descent']}")
        print(f"{'Destination:':20} {values['Destination']}")
        print(f"{'Seat:':20} {values['Seat']}")
        print(f"{'Calendar:':20} {values['Calendar']}")
        print("-" * 45) 

def reserve_seat():

    view_flight_list(flight)

    code_flight = input("\nEnter the flight code you want to book: ").upper()
    seat = input("\nEnter the seat you want to reserve: ").upper()

    if code_flight in flight:
        info_flight = flight[code_flight]
        if seat in info_flight["Seat"]:
            info_flight["Seat"].remove(seat)
            info_flight["Occupaied"].append(seat)
            print(f"\nSeat {seat} successfully booked on flight {code_flight}.")
        else:
            print(f"\nSeat {seat} is not available on flight {code_flight}.")
    else:
        print(f"\nFlight {code_flight} does not exist.")


def calculate_percentage(code_flight):

    divider:int = 6
    multiply: int = 100 
    percent_occupaied:float = 0.0

    """ if code_flight in flight:
        occupaied = len(flight[code_flight]["Occupaied"])
        percent_occupaied = (occupaied * multiply)/divider
        print(percent_occupaied)
    return(percent_occupaied) """


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



def generate_reports(ruta_archivo, contenido):
    """
    Genera un reporte en un archivo de texto.

    Args:
        ruta_archivo: La ruta completa al archivo donde se guardará el reporte.
        contenido: El contenido del reporte (una cadena de texto).
    """
    try:
        # Crear la carpeta si no existe
        carpeta = os.path.dirname(ruta_archivo)
        if carpeta and not os.path.exists(carpeta):
            os.makedirs(carpeta)

        # Escribir el archivo
        with open(ruta_archivo, "w", encoding="utf-8") as archivo:
            archivo.write(contenido)
        print(f"Reporte generado exitosamente en: {ruta_archivo}")
    except Exception as e:
        print(f"Error al generar el reporte: {e}")

# Ejemplo de uso:
ruta_del_reporte = "../Documentos/training_exercise/report.txt"  # Asegúrate de poner una extensión como .txt
contenido_del_reporte = f"Este es el contenido del reporte.{flight}\nAquí puedes agregar más información."
generate_reports(ruta_del_reporte, contenido_del_reporte)

def menu():

    check:bool = True
    option:int = 0
    i:int = 0
    attempts: int = 3

    while check:

        print("------------------------------------------------------------")
        print("\n\nExercise 1: Flight Reservation System\n")
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
            generate_reports("../Documentos/training_exercise/report.txt", "Este es el contenido del reporte.")
            continue
        elif option == "4":
            print("\n  ... Exit of program ... ")
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
