# Comandos básicos de MongoDB en mongoShell

## 1. Conectarse a la base de datos
```bash
mongosh "mongodb+srv://<tu_cluster>.mongodb.net/" --username <usuario>
```
> Reemplaza `<tu_cluster>` y `<usuario>` con tus datos reales.  
> El sistema te pedirá la contraseña.

---

## 2. Mostrar bases de datos disponibles
```javascript
show dbs
```

---

## 3. Seleccionar o crear una base de datos
```javascript
use miBaseDeDatos
```
Si la base de datos no existe, se creará automáticamente cuando insertes datos.

---

## 4. Mostrar colecciones
```javascript
show collections
```

---

## 5. Crear una colección
```javascript
db.createCollection("clientes")
```

---

## 6. Insertar un registro
```javascript
db.clientes.insertOne({
    nombre: "Juan",
    edad: 30,
    ciudad: "Medellín"
})
```

---

## 7. Insertar múltiples registros
```javascript
db.clientes.insertMany([
    { nombre: "Ana", edad: 25, ciudad: "Bogotá" },
    { nombre: "Luis", edad: 35, ciudad: "Cali" },
    { nombre: "María", edad: 28, ciudad: "Barranquilla" }
])
```

---

## 8. Consultar todos los registros
```javascript
db.clientes.find()
```

---

## 9. Consultar con filtros
```javascript
db.clientes.find({ ciudad: "Bogotá" })
```

---

## 10. Consultar con formato legible
```javascript
db.clientes.find().pretty()
```

---

## 11. Contar documentos
```javascript
db.clientes.countDocuments()
```

---

## 12. Actualizar un registro
```javascript
db.clientes.updateOne(
    { nombre: "Ana" },
    { $set: { edad: 26 } }
)
```

---

## 13. Actualizar múltiples registros
```javascript
db.clientes.updateMany(
    { ciudad: "Bogotá" },
    { $set: { ciudad: "Bogotá D.C." } }
)
```

---

## 14. Eliminar un registro
```javascript
db.clientes.deleteOne({ nombre: "Luis" })
```

---

## 15. Eliminar múltiples registros
```javascript
db.clientes.deleteMany({ ciudad: "Bogotá D.C." })
```

---

## 16. Ordenar resultados
```javascript
db.clientes.find().sort({ edad: 1 })  // 1 = ascendente, -1 = descendente
```

---

## 17. Limitar resultados
```javascript
db.clientes.find().limit(5)
```

---

## 18. Proyección de campos (mostrar solo algunos)
```javascript
db.clientes.find({}, { nombre: 1, ciudad: 1, _id: 0 })
```

---

## 19. Borrar una colección completa
```javascript
db.clientes.drop()
```

---

## 20. Borrar una base de datos
```javascript
db.dropDatabase()
```

---

## 21. Salir de mongoShell
```bash
exit
```
