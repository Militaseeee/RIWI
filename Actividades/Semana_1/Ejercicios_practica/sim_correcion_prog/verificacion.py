'''
    Verificar si una persona puede acceder a un evento exclusivo. Para ello, debe ser mayor de edad (18 años o más) y contar con un permiso especial. 
    Si cumple ambas condiciones, se le concederá el acceso; de lo contrario, se le denegará."
'''
# Variables
edad = 17
tiene_permiso = True

# Operadores lógicos
if edad>=18 and tiene_permiso:
    print("Acceso concedido ✅")
else:
    print("Acceso denegado ❌")