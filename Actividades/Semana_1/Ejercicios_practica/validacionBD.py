# --------------------------------
# SYSTEM DATA
puerto = int(3080)
n_bd = str("prueba1")
n_user = str("cacosta")
passw = str("Ingreso20*")
server = str("www.riwi.com/bd")
# --------------------------------
urlS = (f"{server}:{puerto}/{n_bd}/nombre_usuario={n_user}&contraseña={passw}")

puertoU = int(input("Type the PORT NUMBER: "))
n_bdU = str(input("Type the DATABASE NAME: "))
n_userU = str(input("Type the USER NAME: "))
passU = str(input("Type the PASSWORD: "))

urlU = (f"{server}:{puertoU}/{n_bdU}/nombre_usuario={n_userU}&contraseña={passU}")

if urlS == urlU:
    print("ACCESS GRANTED")
else:
    print("ACCESS DENIED")