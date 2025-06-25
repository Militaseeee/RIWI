// Project initialization
console.log("Data Management with Objects, Sets, and Maps!");

// Define the object products
const products = {
    1: { id: 1, nameProduct: "laptop", price: 1500 },
    2: { id: 2, nameProduct: "mouse", price: 25 },
    3: { id: 3, nameProduct: "keyboard", price: 50 },
};

// Create a Set with the names of the products
const setProducts = new Set(
    Object.values(products).map((product) => product.nameProduct)
);

// Create a Map to add categories to products
const mapProducts = new Map([
    ["electronics", ["laptop"]],
    ["accessories", ["mouse", "keyboard"]]
]);

// Get next available product ID
let nextId = Math.max(...Object.keys(products).map(Number)) + 1;

// Ask the user if they want to add a new category
let userChoice 

while (userChoice !== 1 && userChoice !== 2) {
    userChoice = prompt("Do you want to add a new category? Enter 1 for YES or 2 for NO:");
    if (userChoice === "1") {
        // Request new category
        let newCategory = prompt("Enter the name of a new category:");
        console.log("New category entered:", newCategory);
    
        // Validate if it already exists
        if (mapProducts.has(newCategory)) {
            alert("That category already exists!");
            console.log("Category already exists in Map.");
        } else {
            let enteredProducts = prompt("Enter products for that category, separated by commas (e.g.: Tablet, Monitor):");
            let productList = enteredProducts.split(",").map(p => p.trim());

            // Add to map
            mapProducts.set(newCategory, productList);
            console.log(`Category ${newCategory} added with products:, productList`);

            // Add each product to the 'products' object
            productList.forEach(productName => {
                products[nextId] = {
                    id: nextId,
                    nameProduct: productName,
                    price: Math.floor(Math.random() * 500 + 50) // Fake random price
                };
                setProducts.add(productName); // Add to set
                nextId++;
            });

            break;
        }
    } else if (userChoice === "2") {
        console.log("No new category was added");
        break;
    } else {
        alert("Invalid option. Please enter 1 (YES) or 2 (NO). The program will now stop");
    }
}


// Traverse the products object
console.log("\n--- Object: products ---");
for (const id in products) {
    console.log(`Product ID: ${id}, Details:, products[id]`);
}

// Traverse the Set of products
console.log("\n--- Set: unique product names ---");
for (const product of setProducts) {
    console.log("Unique product:", product);
}
// Traverse the product Map
console.log("\n--- Map: categories and products ---");
mapProducts.forEach((products, category) => {
    products.forEach(product => {
        console.log(`Category: ${category}, Product: ${product}`);
    });
});

// Final summary
console.log("\n Complete data management test:");
console.log("Product list (Object):", products);
console.table(products)
console.log("Unique product names (Set):", setProducts);
console.log("Categories and products (Map):", mapProducts);
console.table(Array.from(mapProducts.entries()));