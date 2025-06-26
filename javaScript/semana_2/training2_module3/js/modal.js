const showProductTableModal = (products, mapProducts) => {
// function showProductTableModal(products, mapProducts) {
    // In this part, the dark background layer is created so the modal looks good
    const overlay = document.createElement("div");
    overlay.classList.add("dark-modal");

    // In this part, the modal itself is created
    const modal = document.createElement("div");
    modal.classList.add("modal-container");

    // I have a title for the modal
    const title = document.createElement("h2");
    title.textContent = "Products Table";
    modal.appendChild(title);

    // In this part of the code, I create the table element and the header row
    const table = document.createElement("table");

    const headerRow = document.createElement("tr");
    ["ID", "Name", "Price"].forEach(text => {
        const th = document.createElement("th");
        th.textContent = text;
        headerRow.appendChild(th);
    });
    table.appendChild(headerRow);

    // Loop through the products object and create a row for each product
    for (const id in products) {
        const product = products[id];
        const row = document.createElement("tr");

        [product.id, product.nameProduct, `$${product.price}`].forEach(value => {
            const td = document.createElement("td");
            td.textContent = value;
            row.appendChild(td);
        });

        table.appendChild(row);
    }
    // This part of the code is important because it adds the modal table
    modal.appendChild(table);


    /* Second table (MAP) */

    // shows product categories and their respective products
    const mapTitle = document.createElement("h2");
    mapTitle.textContent = "Categories and Products";
    mapTitle.classList.add("cat-prod-title");
    modal.appendChild(mapTitle);

    // Create the table element for categories and products
    const mapTable = document.createElement("table");

    // Add header row to the category-product table
    const mapHeaderRow = document.createElement("tr");
    ["Category", "Product"].forEach(text => {
        const th = document.createElement("th");
        th.textContent = text;
        mapHeaderRow.appendChild(th);
    });
    mapTable.appendChild(mapHeaderRow);

    // Populate the table by iterating over the Map of categories
    mapProducts.forEach((productList, category) => {
        productList.forEach(product => {
            const row = document.createElement("tr");

            // Each row contains the category and its product
            [category, product].forEach(value => {
                const td = document.createElement("td");
                td.textContent = value;
                row.appendChild(td);
            });
            mapTable.appendChild(row);
        });
    });
    modal.appendChild(mapTable);

    // Create the close button using an image for aesthetic purposes
    const closeButton = document.createElement("button");
    closeButton.classList.add("modal-close-button");

    const closeIcon = document.createElement("img");
    closeIcon.src = "img/arrow.png"; 
    closeIcon.alt = "Close";
    closeIcon.classList.add("close-icon-img");

    closeButton.appendChild(closeIcon);
    closeButton.addEventListener("click", () => document.body.removeChild(overlay));

    modal.appendChild(closeButton);

    // Add the modal to the overlay and display it in the document
    overlay.appendChild(modal);
    document.body.appendChild(overlay);
}

// Export the function to the global scope
window.showProductTableModal = showProductTableModal;