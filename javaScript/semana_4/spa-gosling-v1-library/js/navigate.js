import { setupSearch } from "./booksTable";
import { formatDateToSave, setDateInputValidation } from "./form";
import { counterId, hideActionColumn, initApp, isAuth, randomISBN, setupLoginForm } from "./script";
import { createBook, getBooks, updateBook } from "./services";

const routes = {
  "/": "./index.html",
  "/home": "./views/home.html",
  "/login": "./views/login.html",
  "/books": "./views/books.html",
  "/add_book": "./views/add_book.html",
  "/about": "./views/about.html",
  "/borrow_books": "./views/borrow_books.html",
  "/my_books": "./views/my_books.html",
};
// ✅ Función principal para cargar vistas
export async function navigate(pathname) {

  //Bloque para reemplazar los valores de los elementos HTML con su respectivo rol de usuario 
  const userData = await JSON.parse(localStorage.getItem("UserData"));
  let valRol = false;
  if (userData) {
    document.getElementById("nameUser").textContent = userData.name;
    document.getElementById("role").textContent = userData.role;
  }

  // Si el usuario no está autenticado, redirigimos a login
  if (!isAuth()) { pathname = "/login"; }

  const route = routes[pathname];
  if (!route) return console.error("Invalid route");

  // Cargamos la vista HTML correspondiente
  const html = await fetch(route).then((res) => res.text());
  const parser = new DOMParser();
  const doc = parser.parseFromString(html, "text/html");
  
  // Reemplazamos el contenido dinámicamente
  const newContent = doc.getElementById("content");
  const content = document.getElementById("content");

  content.innerHTML = newContent ? newContent.innerHTML : doc.body.innerHTML;
  history.pushState({}, "", pathname);

  // Mostrar u ocultar botón "Add Book" según el rol
  const linkAddBook = document.getElementById("addBook");
  if (userData && linkAddBook) {
    linkAddBook.style.display = userData.role === "Admin" ? "flex" : "none";
  }

  // Mostrar u ocultar enlace "Borrow Books" solo a Admin
  const borrowedMenu = document.querySelector('[href="/borrow_books"]');
  if (borrowedMenu) {
    borrowedMenu.style.display = userData?.role === "Admin" ? "flex" : "none";
  }

  // Cambiamos avatar e ítems según el rol en vistas generales
  if (pathname === "/home" || pathname === "/books" || pathname === "/add_book" || pathname === "/about" || pathname === "/my_books" || pathname === "/borrow_books") {
    
    const changeImg = document.getElementById('changePicture');

    if (userData.role === "User") {
      changeImg.src = './assets/img/user.png';
    } else if (userData.role === "Admin") {
      changeImg.src = './assets/img/admin2.png';
    }

    const userBooksLink = document.getElementById("userBooksLink");
    userBooksLink.style.display = userData.role === "User" ? "flex" : "none";
        
  }

  // Configuración especial para la vista de login
  if (pathname === "/login") {
    const main = document.getElementById('content');
    const sidebar = document.getElementById("sidebar");
    sidebar.style.display = "none";
    main.classList.add("login-centered");
    setupLoginForm();

    // Mostrar barra lateral si no estamos en login
  } else {
    const main = document.getElementById('content');
    const sidebar = document.getElementById("sidebar");
    sidebar.style.display = "flex";
    main.classList.remove("login-centered");
  }

  // ✅ Manejamos el resaltado de la opción activa del sidebar
  // Update the .active class in the sidebar
  document.querySelectorAll(".sidebar nav ul li").forEach((li) => {
    const a = li.querySelector("a");
    if (a && a.getAttribute("href") === pathname) {
      li.classList.add("active");
    } else {
      li.classList.remove("active");
    }
  });

  // Lógica especial para la vista de libros
  if (pathname === "/books") {
    const changeImg = document.getElementById('changePicture');

    if (userData.role === "User") {
      changeImg.src = './assets/img/user.png';
    } else if (userData.role === "Admin") {
      changeImg.src = './assets/img/admin2.png';
    }

    await initApp()

    setupSearch();

    switch(userData.role){
      case 'Admin':

        const deleteWrapper = document.getElementById('addBookBtn')
        deleteWrapper.style.display = 'block'

        // const borrowColumn = document.getElementById("actionBorrowBook");
        // if (borrowColumn) borrowColumn.style.display = "none";

        // Oculta los botones de "Borrow" para el admin
        document.querySelectorAll(".borrow-btn").forEach((btn) => {
          btn.style.display = "none";
        });

        break;
      case 'User':

        hideActionColumn();

        // Oculta botones de editar y eliminar
        const editButton = document.querySelectorAll('.edit-btn').forEach(btnEdit => {
          btnEdit.style.display = 'none';
        })
        const deleteButton = document.querySelectorAll('.delete-btn').forEach(deleteBtn => {
          deleteBtn.style.display = 'none';
        })

        const addBtn = document.getElementById('addBookBtn');
        if (addBtn) addBtn.style.display = 'none';

        const actionDelete = document.getElementById('actionDeleteBook');
        if (actionDelete) actionDelete.style.display = 'none';
        
        // const actionHide = document.getElementById('actionHide');
        // actionHide.style.display = 'none';

        break;
        
      default:
        break
    }
  }

  // Lógica para la vista de agregar libro
  if (pathname === "/add_book") {
    setDateInputValidation(); 

    const form = document.getElementById("addBookForm");
    
    form.addEventListener("submit", async (e) => {
      e.preventDefault();

      // Creamos un nuevo libro
      const newBook  = {
        "id": counterId,
        "title": form.title.value,
        "author": form.author.value,
        "isbn": randomISBN(),
        "dateOfEntry": formatDateToSave(form.dateOfEntry.value),
        "borrowedBy": []
      };
      
      await createBook(newBook);
      navigate("/books");
    });

    const goBackBtn = document.getElementById("goBackBtn");
    if (goBackBtn) {
      goBackBtn.addEventListener("click", () => {
        navigate("/books");
      });
    }
  }

  // Lógica para la vista de libros prestados (solo Admin)
  if (pathname === "/borrow_books") {
  if (!userData || userData.role !== "Admin") {
    navigate("/home");
    return;
  }

  const borrowedBooksTableBody = document.getElementById("borrowedBooksTableBody");
  if (!borrowedBooksTableBody) return;

  const books = await getBooks();
  const borrowedBooks = books.filter(
    (book) => Array.isArray(book.borrowedBy) && book.borrowedBy.length > 0
  );

  borrowedBooksTableBody.innerHTML = "";

  borrowedBooks.forEach((book) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td>${book.dateOfEntry}</td>
      <td>${book.borrowedBy}</td>
    `;
    borrowedBooksTableBody.appendChild(row);
  });
  }

  // Lógica para vista de libros prestados del usuario
  if (pathname === "/my_books") {
    if (!userData || userData.role !== "User") {
      navigate("/home");
      return;
    }

    const books = await getBooks();
    const userBooks = books.filter(book => 
      Array.isArray(book.borrowedBy) && book.borrowedBy.includes(userData.name)
    );

    const tableBody = document.getElementById("myBorrowedBooksTableBody");
    if (!tableBody) return;

    tableBody.innerHTML = "";

    userBooks.forEach(book => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${book.title}</td>
      <td>
        <button class="return-btn" data-id="${book.id}">Return</button>
      </td>
    `;
    tableBody.appendChild(row);
    });

    // Lógica para devolver un libro (remueve visualmente)
    document.querySelectorAll(".return-btn").forEach((btn) => {
      btn.addEventListener("click", async (e) => {
        e.preventDefault(); // Previene navegación inesperada

        const id = btn.dataset.id;
        const books = await getBooks();
        const book = books.find((b_books) => b_books.id == id);
        if (!book) return;
      
        const updatedBook = {
          ...book,
          borrowedBy: [], // quitamos al usuario
        };
      
        await updateBook(book.id, updatedBook);
      
        // 💥 Elimina visualmente la fila sin recargar nada
        btn.closest("tr").remove();
      });
    });

  }
}