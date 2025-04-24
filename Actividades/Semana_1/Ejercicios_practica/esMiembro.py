"""
Una tienda ofrece un descuento del 15% bajo estas condiciones:

    El cliente debe gastar más de $100 en su compra.

    Además, debe cumplir al menos una de estas opciones:

        Ser miembro del programa de fidelidad o

        No gastar más de $200 (para evitar combinar descuentos)

Si no se cumplen estas condiciones, no se aplicará ningún descuento.
"""

# Variables
es_miembro = True
total_compra = 240


# Operadores lógicos y not
if total_compra > 100 and (es_miembro or not total_compra > 200):
    print("Descuento del 15% aplicado 🎉")
else:
    print("Sin descuento 😢")