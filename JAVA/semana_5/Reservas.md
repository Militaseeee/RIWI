# Actividad: **Gestor de Reservas de Salas – Manejo de Excepciones (Individual)**

## Contexto

Una organización administra la **reserva de salas** para reuniones. Debes permitir **crear, consultar, cancelar y listar** reservas con validaciones de negocio (choques de horario, sala no disponible, datos incompletos) y **mapear errores a “códigos de respuesta”** claros para el usuario.

> **Opcional:** si lo deseas, persiste las reservas en **MySQL o PostgreSQL** usando **JDBC**. Recuerda **descargar el driver JDBC** correspondiente (MySQL: `mysql-connector-j`; PostgreSQL: `postgresql`) y configurarlo.

---

## Objetivo

Practicar en un caso realista:

* `try/catch/finally`
* **try-with-resources**
* **excepciones por firma** (`throws`)
* **multi-catch**
* **re-lanzar/encapsular** excepciones técnicas (wrapping)
* **excepciones personalizadas** que simulen **códigos de estado** (400/404/409/500)

---

## Alcance funcional (mínimo y alcanzable)

1. **Crear reserva** con: `idReserva`, `idSala`, `fecha`, `horaInicio`, `horaFin`, `organizador`.
2. **Consultar reserva por ID**.
3. **Cancelar reserva** por ID.
4. **Listar reservas** por **rango de fecha** y/o por **sala** (filtros combinables).

### Reglas de negocio

* **Obligatorios**: sala, fecha, rango horario válido (`horaInicio < horaFin`).
* **Choque de horario**: no se permiten reservas solapadas en la misma sala.
* **Sala fuera de servicio**: no se puede reservar.
* **ID único**: no se puede repetir `idReserva`.
* **No encontrado**: operar sobre una reserva inexistente debe fallar claramente.

---

## Excepciones personalizadas (requeridas)

Crea una jerarquía (por ejemplo, paquete `errors`):

* `BadRequestException` (**400**) – datos inválidos o faltantes.
* `NotFoundException` (**404**) – recurso inexistente.
* `ConflictException` (**409**) – choque de horario, sala fuera de servicio o id duplicado.
* `ServiceException` (**500**) – error interno / técnico envuelto.
* `DataAccessException` (**checked**) – operaciones de acceso a datos (archivo o BD) para forzar `throws`.

**Mapa de estado para la salida (simulación tipo HTTP):**

* `BadRequestException` → 400
* `NotFoundException` → 404
* `ConflictException` → 409
* `ServiceException` → 500
* Cualquier otra no mapeada → 500

---

## Requisitos técnicos de la actividad

* **≥1** bloque `try { … } catch (…) { … } finally { … }` donde el `finally` haga algo **visible** (limpieza, mensaje “fin de operación”, etc.).
* **≥1** **try-with-resources** (ej.: leer `salas.csv` o `application.properties`).
* **≥2** métodos con **excepciones por firma** (`throws DataAccessException` u otra *checked*).
* **≥1** **multi-catch** (p. ej., `NumberFormatException | IllegalArgumentException`).
* **≥1** **wrapping**: convertir una excepción técnica (I/O, SQL si usas BD) a `ServiceException` **preservando la causa**.

---

## Casos de uso a implementar

1. **Crear reserva**

   * Valida obligatorios; si falla → `BadRequestException`.
   * Verifica **ID único**; duplicado → `ConflictException`.
   * Verifica existencia y **disponibilidad** de la sala; si no, `NotFoundException` o `ConflictException`.
   * Valida **solapamiento** horario; choque → `ConflictException`.
   * Guarda usando tu capa de acceso a datos (**`throws DataAccessException`**); si falla I/O/SQL, envuelve en `ServiceException`.

2. **Consultar por ID**

   * Si no existe → `NotFoundException`.
   * Fallo técnico de acceso → `ServiceException` (wrapping).

3. **Cancelar por ID**

   * Si no existe → `NotFoundException`.
   * Fallo técnico → `ServiceException`.

4. **Listar con filtros**

   * Valida formato de parámetros; errores → **multi-catch** y lanza `BadRequestException`.
   * Devuelve lista (vacía si no hay coincidencias).

---

## Criterios de aceptación (Given/When/Then)

* **Manejo seguro de recursos**
  **Dado** un archivo de configuración o catálogo de salas
  **Cuando** lo leo con **try-with-resources**
  **Entonces** el recurso se cierra automáticamente y no hay fugas.

* **Excepciones por firma**
  **Dado** métodos que acceden a almacenamiento (archivo o BD opcional)
  **Cuando** declaran `throws DataAccessException`
  **Entonces** el compilador obliga a manejar/propagar y el flujo funciona.

* **Validaciones de negocio**
  **Dado** una creación con datos faltantes o rango inválido
  **Cuando** intento crear la reserva
  **Entonces** obtengo **400** con mensaje claro.

* **Conflictos de horario**
  **Dado** una reserva existente en la misma sala y horario solapado
  **Cuando** intento crear otra
  **Entonces** obtengo **409**.

* **Recurso inexistente**
  **Dado** un `idReserva` inexistente
  **Cuando** consulto o cancelo
  **Entonces** obtengo **404**.

* **Errores técnicos envueltos**
  **Dado** un fallo de acceso (archivo inaccesible o conexión BD inválida si usas BD)
  **Cuando** ejecuto la operación
  **Entonces** recibo **500 (ServiceException)** con la causa original preservada.

* **Bloque `finally` visible**
  **Dado** cualquier flujo exitoso o con error
  **Cuando** finaliza
  **Entonces** se ejecuta una acción observable (mensaje/limpieza).

---

## UML y documentación requerida

* **Diagrama de caso de uso:** crear, consultar, cancelar, listar con filtros.
* **Diagrama de clases:** entidades (`Sala`, `Reserva`), capa de negocio, acceso a datos y excepciones; relaciones y dependencias.
* **Diagrama de datos** (solo si usas BD): tablas mínimas (salas, reservas) y relaciones.
* **README:** cómo ejecutar; qué valida cada caso; mapeo de excepciones→códigos; si usas BD, **recordatorio del driver JDBC** y parámetros de conexión; ejemplos de entrada/salida.

---

## Pruebas manuales sugeridas

1. Crear reserva válida → 200/201.
2. Crear con datos faltantes → 400.
3. Crear con choque horario → 409.
4. Consultar/cancelar ID inexistente → 404.
5. Forzar fallo técnico (archivo bloqueado o URL BD inválida) → 500 con causa.
6. Listar con filtros mal formateados → 400 (multi-catch).
7. Verificar que **`finally`** se ejecuta siempre (mensaje de cierre).
