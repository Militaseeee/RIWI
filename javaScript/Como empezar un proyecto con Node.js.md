# 🚀 Cómo Iniciar un Proyecto con Node.js

Este documento te guiará paso a paso para crear un proyecto básico con **Node.js**, usando `npm`, `axios` y `Vite`.

---

## ✅ 1. Verificar si Node.js está instalado

Abre tu terminal y escribe:

```bash
node -v
```

También verifica `npm` (Node Package Manager):

```bash
npm -v
```

### 🔧 Si no tienes Node.js:
Descárgalo desde la página oficial:  
👉 https://nodejs.org/es/download

---

## 📦 3. Inicializar el proyecto con npm

Esto creará un archivo `package.json` básico automáticamente:

```bash
npm init -y
```

---

## 🌐 4. Instalar Axios

Axios es una librería para hacer peticiones HTTP. Para instalarla:

```bash
npm install axios
```

---

## ⚡ 5. Instalar y configurar Vite

[Vite](https://vitejs.dev/) es un "bundler" moderno para aplicaciones web rápidas.

### Paso 1: Instalar Vite como dependencia de desarrollo

```bash
npm install -D vite
```

### Paso 2: Agregar scripts al `package.json`

Abre tu `package.json` y modifica la sección `"scripts"` así:

```json
"scripts": {
  "dev": "vite"
}
```

### Paso 3: Consideraciones

Escribe en la etiqueta script `type="module"`:

```html
<script type="module" src="./script.js"></script>
```

Importa primero el ```axious``` en tu archivo ```.js```
```js
import axios from 'axios';
```

---

## ▶️ 4. Ejecutar el servidor de desarrollo

Inicia Vite con:

```bash
npm run dev
```

Abre el navegador y ve a `http://localhost:5173` para ver tu proyecto en acción.
