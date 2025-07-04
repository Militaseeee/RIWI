// json-server --watch db.json

// Base URL for accessing the products endpoint
const BASE_API_URL = "http://localhost:3000/products";

// DOM elements
const modalCrud = document.getElementById('modalCrud');
const modalTitle = document.getElementById('modalTitle');
const modalContent = document.getElementById('modalContent');
const modalForm = document.getElementById('modalForm');
const modalOverlay = document.getElementById('modalOverlay');

let choseCrud = ""; // Stores the current action (show, new, update, delete)

// Function to fetch and display all products in a table
const showProducts = async () => {
    try {
        const response = await fetch(`${BASE_API_URL}`);
        const data = await response.json();

        modalContent.innerHTML = ""; // Clear existing content

        // Display message if no products exist
        if (data.length === 0) {
            const msg = document.createElement("p");
            msg.textContent = "There are no products available";
            modalContent.appendChild(msg);
            return;
        }

        // Create table and its header
        const table = document.createElement("table");
        table.classList.add("product-table");

        const thead = document.createElement("thead");
        const headerRow = document.createElement("tr");

        ["ID", "NAME", "PRICE"].forEach(text => {
            const th = document.createElement("th");
            th.textContent = text;
            headerRow.appendChild(th);
        });

        thead.appendChild(headerRow);
        table.appendChild(thead);

        const tbody = document.createElement("tbody");

        // Loop through each product and create rows
        data.forEach(product => {
            const row = document.createElement("tr");

            const cellId = document.createElement("td");
            cellId.textContent = product.id;

            const cellName = document.createElement("td");
            cellName.textContent = formatProductName(product.name);

            const cellPrice = document.createElement("td");
            cellPrice.textContent = `$ ${product.price}`;

            row.appendChild(cellId);
            row.appendChild(cellName);
            row.appendChild(cellPrice);
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        modalContent.appendChild(table); // Show table in the modal
    } catch (error) {
        console.error("Error fetching products:", error);

        modalContent.innerHTML = ""; // Clear modal content
        const errorMsg = document.createElement("p");
        errorMsg.textContent = "Error getting products";
        errorMsg.classList.add("error-message");
        modalContent.appendChild(errorMsg);
    }
};

// Function to generate checkboxes for deleting products
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
        list.className = "delete-product-list";

        // Generate list items with checkboxes for each product
        products.forEach(product => {
            const item = document.createElement("li");
            item.className = "delete-product-item";

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

const confirmButton = document.getElementById('confirm-button');

// Handles CRUD operation selected by the user
function selectCrud(accion) {
    choseCrud = accion;

    switch (accion) {
        case "show":
            modalTitle.textContent = "Show products";
            modalContent.innerHTML = "<p>Loading products...</p>";
            confirmButton.style.display = "none"; // Hide confirm button for read-only
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
    openModal(); // Open the modal window
}

// Show the modal and overlay background
function openModal() {
    modalOverlay.style.display = 'block';
    modalCrud.showModal();
}

// Close the modal and hide overlay
function closeModal() {
    modalCrud.close();
    modalOverlay.style.display = 'none';
}

// Utility: formats product names (e.g., "toothPASTE" → "Toothpaste")
function formatProductName(name) {
    return name
        .toLowerCase()
        .replace(/\b\w/g, char => char.toUpperCase())
        .trim();
}

// Handle form submission for new, update, or delete actions
modalForm.addEventListener('submit', async (e) => {
    e.preventDefault(); // Prevent default form behavior

    // Handle creating a new product
    if (choseCrud === 'new') {
        try {
            const res = await fetch(BASE_API_URL);
            const products = await res.json();

            const lastId = products.length > 0
                ? Math.max(...products.map(p => Number(p.id)))
                : 0;

            const newProduct = {
                id: String(lastId + 1), // Auto-generated ID
                name: formatProductName(document.getElementById('newName').value),
                price: Number(document.getElementById('newPrice').value)
            };

            await fetch(BASE_API_URL, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(newProduct),
            });

            console.log("Product added:", newProduct);
            document.getElementById('newName').value = "";
            document.getElementById('newPrice').value = "";
            closeModal();
        } catch (error) {
            console.error("Error adding product:", error);
        }
    }

    // Handle updating an existing product
    if (choseCrud === 'update') {
        const updatedName = formatProductName(document.getElementById('updateName').value);
        const updatedPrice = Number(document.getElementById('updatePrice').value);

        try {
            const res = await fetch(BASE_API_URL);
            const products = await res.json();

            const productToUpdate = products.find(product =>
                product.name.toLowerCase() === updatedName.toLowerCase()
            );

            if (!productToUpdate) {
                alert(`Product with the name "${updatedName}" not found`);
                return;
            }

            const updateProduct = {
                id: productToUpdate.id,
                name: updatedName,
                price: updatedPrice || productToUpdate.price
            };

            const response = await fetch(`${BASE_API_URL}/${updateProduct.id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updateProduct),
            });

            const data = await response.json();
            console.log("Product updated:", data);
            closeModal();
        } catch (error) {
            console.error("Error updating:", error);
        }
    }

    // Handle deleting selected products
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
            closeModal();
        } catch (error) {
            console.error("Error deleting products:", error);
        }
    }
});
