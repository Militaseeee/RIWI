// json-server --watch db.json

const BASE_API_URL = "http://localhost:3000/products";

const modalCrud = document.getElementById('modalCrud');
const modalTitle = document.getElementById('modalTitle');
const modalContent = document.getElementById('modalContent');
const modalForm = document.getElementById('modalForm');

const modalOverlay = document.getElementById('modalOverlay');

let choseCrud = ""

const showProducts = async () => {
    try {
        // Fetch the list of products from the API
        const response = await fetch(`${BASE_API_URL}`);
        const data = await response.json();

        // Clear the current content of the modal
        modalContent.innerHTML = "";
        
        // Check if there are no products available
        if (data.length === 0) {
            const msg = document.createElement("p");
            msg.textContent = "There are no products available";  // Message when no products are found
            modalContent.appendChild(msg);
            return;
        }
    
        // Create the table to display products
        const table = document.createElement("table");
        table.classList.add("product-table");
    
        // Create the table header
        const thead = document.createElement("thead");
        const headerRow = document.createElement("tr");
    
        // Add columns for ID, Name, and Price
        ["ID", "NAME", "PRICE"].forEach(text => {
            const th = document.createElement("th");
            th.textContent = text;
            headerRow.appendChild(th);
        });
    
        // Append the header row to the table header
        thead.appendChild(headerRow);
        table.appendChild(thead);
    
        // Create the table body
        const tbody = document.createElement("tbody");
    
        // Iterate over each product and create a row for each
        data.forEach(product => {
            const row = document.createElement("tr");
            
            const cellId = document.createElement("td");
            cellId.textContent = product.id;  // Display product ID
            
            const cellName = document.createElement("td");
            // Format the product name before displaying it
            cellName.textContent = formatProductName(product.name);
            
            const cellPrice = document.createElement("td");
            cellPrice.textContent = `$ ${product.price}`;  // Display product price
            
            // Append each cell to the row
            row.appendChild(cellId);
            row.appendChild(cellName);
            row.appendChild(cellPrice);
            
            // Append the row to the table body
            tbody.appendChild(row);
        });
    
        // Append the table body to the table
        table.appendChild(tbody);
        // Append the table to the modal content
        modalContent.appendChild(table);

    } catch (error) {
        // Handle errors if fetching the products fails
        console.error("Error fetching products:", error);
        
        const errorMsg = document.createElement("p");
        errorMsg.textContent = "Error getting products";  // Message when there's an error
        errorMsg.classList.add("error-message");
        
        // Clear any previous content and display the error message
        modalContent.innerHTML = "";
        modalContent.appendChild(errorMsg);
    }
};

async function deleteProductLine() {
    try {
        const res = await fetch(BASE_API_URL);
        const products = await res.json();

        if (products.length === 0) {
            modalContent.innerHTML = "<p>There are no products to remove</p>";
            confirmButton.style.display = "none";
            return;
        }

        const list = document.createElement("ul");
        list.className = "delete-product-list"; // New class for style

        products.forEach(product => {
            const item = document.createElement("li");
            item.className = "delete-product-item"; // New class

            item.innerHTML = `
                <label class="delete-product-label">
                    <input type="checkbox" class="delete-checkbox" value="${product.id}">
                    <span>${product.name} - $ ${product.price}</span>
                </label>
            `;
            list.appendChild(item);
        });
        modalContent.innerHTML = "";
        modalContent.appendChild(list);
    } catch (error) {
        modalContent.innerHTML = "<p class='error-message'>Error loading products</p>";
        console.error("Error:", error);
    }
}

const confirmButton = document.getElementById('confirm-button')

function selectCrud(accion) {

    choseCrud = accion;
    switch (accion) {
        case "show":
            modalTitle.textContent = "Show products";
            modalContent.innerHTML = "<p>Loading products...</p>";
            confirmButton.style.display = "none";   
            showProducts();
            break;
        case "new":
            confirmButton.style.display = "inline-block";
            modalTitle.textContent = "New product";
            modalContent.innerHTML = `
            <input type="text" id="newName" placeholder="Name of product" class="style-form" required>
            <input type="number" id="newPrice" placeholder="Price of product" class="style-form" required>
            `;
            break;
            
        case "update":
            confirmButton.style.display = "inline-block";
            modalTitle.textContent = "Update product";
            modalContent.innerHTML = `
            <input type="text" id="updateName" placeholder="Name of product" class="style-form" required>
            <input type="number" id="updatePrice" placeholder="New price" class="style-form">
            `;
            break;
            
        case "delete":
            confirmButton.style.display = "inline-block";
            modalTitle.textContent = "Delete products";
            modalContent.innerHTML = "<p>Loading products...</p>";
            deleteProductLine();
            break;
    } 
    modalCrud.showModal();
}

function openModal() {
    modalOverlay.style.display = 'block';  // Muestra el overlay
    modalCrud.showModal();                  // Muestra el modal
}

function closeModal() {
    modalCrud.close();                      // Cierra el modal
    modalOverlay.style.display = 'none';   // Oculta el overlay
}

function formatProductName(name) {
    // Converts the entire name to lowercase, then capitalizes the first letter of each word
    return name
        .toLowerCase() // Convert everything to lowercase first
        .replace(/\b\w/g, char => char.toUpperCase()) // Capitalize the first letter of each word
        .trim(); // Remove any extra spaces from the beginning and end
}

modalForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    if (choseCrud === 'new') {
    try {
        // Get existing products to calculate the new ID
        const res = await fetch(BASE_API_URL);
        const products = await res.json();
    
        // Create a copy and find the highest ID
        const lastId = products.length > 0
            ? Math.max(...products.map(p => Number(p.id)))
            : 0;
    
        const newProduct = {
            id: lastId + 1, // Automatically generated ID
            name: formatProductName(document.getElementById('newName').value), // Apply formatting to the name
            price: Number(document.getElementById('newPrice').value)
        };
    
        // Save the new product to the database
        await fetch(BASE_API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newProduct),
        });
    
        console.log("Product added:", newProduct);

        // Clear input fields after submission
        document.getElementById('newName').value = "";
        document.getElementById('newPrice').value = "";

        modalCrud.close();

    } catch (error) {
        console.error("Error adding product:", error);
    }
}

    if (choseCrud === 'update') {
        // Get the name of the product the user wants to update and format it
        const updatedName = formatProductName(document.getElementById('updateName').value); // Apply formatting
        const updatedPrice = Number(document.getElementById('updatePrice').value);

        try {
            // Fetch all products and search by name
            const res = await fetch(BASE_API_URL);
            const products = await res.json();

            // Find product by name
            const productToUpdate = products.find(product => product.name.toLowerCase() === updatedName.toLowerCase());

            if (!productToUpdate) {
                alert(`Product with the name "${updatedName}" not found.`);
                return;
            }

            // Create the object with new values for the product
            const updateProduct = {
                id: productToUpdate.id, // ID of the found product
                name: updatedName,  // Use the formatted name
                price: updatedPrice || productToUpdate.price // If no new price, keep the current one
            };

            // Update the product in the database
            const response = await fetch(`${BASE_API_URL}/${updateProduct.id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updateProduct),
            });

            const data = await response.json();
            console.log("Product updated:", data);
            modalCrud.close();
        } catch (error) {
            console.error("Error updating:", error);
        }
    }


    if (choseCrud === 'delete') {
        const checkedBoxes = document.querySelectorAll(".delete-checkbox:checked");

        if (checkedBoxes.length === 0) {
            alert("Please select at least one product to remove");
            return;
        }

        try {
            for (const checkbox of checkedBoxes) {
                const id = checkbox.value;
                const res = await fetch(`${BASE_API_URL}/${id}`, {
                    method: "DELETE"
                });

                if (!res.ok) {
                    console.warn(`Could not delete product with ID ${id}`);
                }
            }

            console.log("Products successfully removed");
        } catch (error) {
            console.error("Error deleting products:", error);
        }

        modalCrud.close();
    }
});