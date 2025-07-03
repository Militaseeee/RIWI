# Guía rápida: Configuración de Vite y variables de entorno

> **Objetivo**: Reemplazar Live Server por Vite, configurar el script de desarrollo y manejar variables de entorno sin `dotenv`.

---

## 1. Instalar Vite en modo desarrollo

```bash
npm install -D vite
```

Esto añade Vite como dependencia de desarrollo en tu proyecto.

---

## 2. Añadir el script de arranque en `package.json`

```jsonc
{
  // ...
  "scripts": {
    "dev": "vite"
  }
}
```

Con esto ya no necesitas abrir el proyecto con Live Server.  
Vite levanta su propio servidor de desarrollo con soporte nativo para módulos ES y recarga en caliente (HMR).

---

## 3. Levantar el servidor

```bash
npm run dev
```

El comando abre el proyecto normalmente en `http://localhost:5173` (o el puerto que indique la consola).  
Live Server queda obsoleto aquí porque **no entiende importaciones ES Modules (`import … from ...`)**, mientras que Vite sí.

---

## 4. Crear y usar un archivo `.env`

1. En la raíz del proyecto, crea un archivo llamado **`.env`**.  
2. Declara tus variables **siempre** con el prefijo **`VITE_`**:

```env
VITE_WEATHER_API=tu_api_key_aquí
```

---

## 5. Acceder a la variable en tu código

```js
// src/algúnArchivo.js
const API_KEY = import.meta.env.VITE_WEATHER_API;
console.log(API_KEY);
```

- **No** necesitas instalar ni importar `dotenv`; Vite lo hace por ti en tiempo de desarrollo y de compilación.  
- Cada vez que cambies el `.env`, reinicia `npm run dev` para que Vite recargue los valores.

---

