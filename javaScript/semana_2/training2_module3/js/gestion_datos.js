// Inicialización del proyecto
console.log("¡Gestión de Datos con Objetos, Sets y Maps!");

// Definir el objeto productos
const productos = {
    1: { id: 1, nombre: "Laptop", precio: 1500 },
    2: { id: 2, nombre: "Mouse", precio: 25 },
    3: { id: 3, nombre: "Teclado", precio: 50 },
};

// Crear un Set con los nombres de los productos
const setProductos = new Set(
    Object.values(productos).map((producto) => producto.nombre)
);
// console.log("Set de productos únicos: ", setProductos);

// Crear un Map para agregar categorías a los productos
const mapProductos = new Map([
    ["Electrónica", ["Laptop"]],
    ["Accesorios", ["Mouse", "Teclado"]]
]);

// Pedir nueva categoría
let nuevaCategoria = prompt("Ingresa el nombre de una nueva categoría:");

// Validar si ya existe (has -> Método para verificar)
if (mapProductos.has(nuevaCategoria)) {
    alert("¡Esa categoría ya existe!");
} else {
    // Pedir productos separados por coma
    let productosIngresados = prompt("Ingresa productos para esa categoría, separados por coma (ej: Tablet, Monitor):");

    // Convertir a array eliminando espacios
    let listaProductos = productosIngresados.split(",").map(producto => producto.trim());

    // Agregar al Map
    mapProductos.set(nuevaCategoria, listaProductos);

    console.log(`Categoría '${nuevaCategoria}' añadida con los productos:`, listaProductos);
}

// console.log("Map de productos y categorías: ", mapProductos);

// Recorrer el objeto productos
for (const id in productos) {
    console.log(`Producto ID: ${id}, Detalles: `, productos[id]);
}

// Recorrer el Set de productos
for (const producto of setProductos) {
    console.log("Producto único: ", producto);
}

// Recorrer el Map de productos
mapProductos.forEach((productos, categoria) => {
    productos.forEach(producto => {
        console.log(`Categoría: ${categoria}, Producto: ${producto}`);
    });
});

console.log("Pruebas completas de gestión de datos:");
console.log("Listas de productos (Objeto): ", productos);
console.log("Lista de productos únicos (Set): ", setProductos);
console.log("Categorías y productos (Map): ", mapProductos);