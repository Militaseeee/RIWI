"""
Un sistema de seguridad para el hogar necesita monitorear posibles intrusiones bajo estas condiciones:

    Se detecta movimiento en el interior de la propiedad (activado por sensores).

    Además, debe cumplirse al menos una de estas situaciones:

        Alguna ventana está abierta o

        Es horario nocturno (entre las 10 PM y 6 AM)

Cuando ambas condiciones principales se cumplen, se activa la alarma; en cualquier otro caso, el sistema permanece en estado seguro.
"""

# Variables
movimiento = True
ventana_abierta = False
hora_noche = True

# Operadores lógicos
if movimiento and (ventana_abierta or hora_noche):
    print("¡ALARMA! 🚨")
else:
    print("Todo seguro 🔒")