import { getBooks, deleteBook, updateBook } from "./services";
import { openModalEdit } from "./modal";

// Función para renderizar la tabla de libros
// Render users
export function renderBooks(books) {
    const tbody = document.getElementById("bookTableBody");
    if (!tbody) return; // Si no existe el tbody, salimos de la función

    tbody.innerHTML = "";

    // ✅ Obtenemos el rol actual
    const userData = JSON.parse(localStorage.getItem("UserData"));
    const isAdmin = userData && userData.role === "Admin";

    // Recorremos la lista de libros
    books.forEach((book) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td><img src="./assets/img/books.png" alt="Avatar" /></td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.isbn}</td>
            <td>${book.dateOfEntry}</td>
            <td>
                ${
                    isAdmin
                        ? (Array.isArray(book.borrowedBy) && book.borrowedBy.length === 0
                            ? `<span style="color:green;">Available</span>`
                            : `<span style="color:red;">Borrowed</span>`)
                        : (Array.isArray(book.borrowedBy) && book.borrowedBy.length === 0
                            ? `<button class="borrow-btn style-borrow" data-id="${book.id}">Borrow</button>`
                            : "Borrowed")
                }
            </td>
            ${isAdmin ? `
                <td>
                    <button class="edit-btn" data-id="${book.id}">
                        <img src="./assets/icons/pencil.png" alt="Edit" class="edit-icon"/>
                    </button>
                    <button class="delete-btn" data-id="${book.id}">
                        <img src="./assets/icons/trash.png" alt="Delete" class="delete-icon"/>
                    </button>
                </td>
            ` : ""}
            `;
        tbody.appendChild(row); // Agregamos la fila al cuerpo de la tabla
    });
    addRowListeners(); // Activamos los listeners para los botones
}

// Función que añade los listeners a los botones de cada fila
export function addRowListeners() {
    // Listener para botón de editar
    document.querySelectorAll(".edit-btn").forEach((btn) => {
        btn.addEventListener("click", () => {
            const id = btn.dataset.id;
            openModalEdit(id);
        });
    });

    // Listener para botón de eliminar
    document.querySelectorAll(".delete-btn").forEach((btn) => {
        btn.addEventListener("click", async () => {
            const id = btn.dataset.id;
            if (confirm("Are you sure you want to delete this book?")) {
                await deleteBook(id);
                const books = await getBooks();
                renderBooks(books);
            }
        });
    });

    // Listener para botón de prestar libro
    document.querySelectorAll(".borrow-btn").forEach((btn) => {
        btn.addEventListener("click", async () => {

            const id = btn.dataset.id;
            const userData = JSON.parse(localStorage.getItem("UserData")); // Obtenemos los datos del usuario

            if (!userData) return alert("You must be logged in");
            const books = await getBooks();
            const book = books.find((b) => b.id == id);
            if (!book) return;

            if (book.borrowedBy && book.borrowedBy.length > 0) {
                alert("Book already borrowed!");
                return;
            }

            // Creamos un nuevo objeto de libro actualizado con el nombre del usuario
            const updatedBook = {
                ...book,
              borrowedBy: [userData.name], // ✅ lo dejamos como array para que sea compatible con tu db.json
            };

            await updateBook(book.id, updatedBook); // ✅ este debe hacer un PUT o PATCH

            const updatedBooks = await getBooks();
            renderBooks(updatedBooks);
            updateAvailableBooksCount(updatedBooks);
        });
    });
}

// Función que actualiza el contador de libros disponibles
export function updateAvailableBooksCount(books) {
    const available = books.filter(book => !book.borrowedBy || book.borrowedBy.length === 0).length;
    const countSpan = document.getElementById("availableCount");
    if (countSpan) countSpan.textContent = available;
}

// Función para configurar la barra de búsqueda
// Search bar logic
export function setupSearch() {
    const searchInput = document.getElementById("searchInput");
    if (!searchInput) return;

    searchInput.addEventListener("input", async () => {
        const searchTerm = searchInput.value.toLowerCase();
        const allBooks = await getBooks(); // Obtenemos todos los libros

        // Filtramos los libros cuyo título coincida con lo buscado
        const filtered = allBooks.filter((book) =>
            book.title.toLowerCase().includes(searchTerm)
        );

        renderBooks(filtered);
    });
}