import { getUsers, createUser, updateUser, deleteUser } from "./js/api";

// script.js
// const BASE_URL = "http://localhost:3000/users";

// INIT APP
export async function initApp() {
  console.log("🚀 initApp executed from script.js");

  const users = await getUsers();
  renderUsers(users);
  setListeners();
}

// FORM LOGIC
function setListeners() {
  const addBtn = document.getElementById("addUserBtn");
  const closeBtn = document.querySelector(".close-btn");
  const modal = document.getElementById("userModal");
  const form = document.getElementById("userForm");

  if (addBtn) {
    addBtn.addEventListener("click", openModalCreate);
  }

  if (closeBtn) {
    closeBtn.addEventListener("click", closeModal);
  }

  if (modal) {
    window.addEventListener("click", (e) => {
      if (e.target === modal) closeModal();
    });
  }

  let counterId = 3;
  if (form) {
    form.addEventListener("submit", async (e) => {
      e.preventDefault();

      const user = {
        "id": counterId,
        "name": form.name.value,
        "email": form.email.value,
        "phone": form.phone.value,
        "enrollNumber": form.enrollNumber.value,
        "dateOfAdmission": formatDateToSave(form.dateOfAdmission.value),
      };
      counterId++
      const id = form.userId.value;

      if (id) {
        await updateUser(id, user);
      } else {
        await createUser(user);
      }

      const users = await getUsers();
      renderUsers(users);
      closeModal();
    });
  }
}

// Modal functions
function openModalCreate() {
  const form = document.getElementById("userForm");
  const modal = document.getElementById("userModal");
  const modalTitle = document.getElementById("modalTitle");
  const userIdField = document.getElementById("userId");

  modalTitle.textContent = "Add User";
  form.reset();
  userIdField.value = "";
  modal.style.display = "flex";
}

async function openModalEdit(id) {
  const users = await getUsers();
  const user = users.find((u) => u.id == id);
  if (!user) return;

  document.getElementById("userId").value = user.id;
  document.getElementById("name").value = user.name;
  document.getElementById("email").value = user.email;
  document.getElementById("phone").value = user.phone;
  document.getElementById("enrollNumber").value = user.enrollNumber;
  document.getElementById("dateOfAdmission").value = formatDateInput(user.dateOfAdmission);

  document.getElementById("modalTitle").textContent = "Edit User";
  document.getElementById("userModal").style.display = "flex";
}

function closeModal() {
  const modal = document.getElementById("userModal");
  modal.style.display = "none";
}

function formatDateInput(dateStr) {
  const date = new Date(dateStr);
  return date.toISOString().split("T")[0];
}

// Render users
function renderUsers(users) {
  const tbody = document.getElementById("userTableBody");
  if (!tbody) return;

  tbody.innerHTML = "";

  users.forEach((user) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td><img src="./assets/img/admin.avif" alt="Avatar" /></td>
      <td>${user.name}</td>
      <td>${user.email}</td>
      <td>${user.phone}</td>
      <td>${user.enrollNumber}</td>
      <td>${user.dateOfAdmission}</td>
      <td>
        <button class="edit-btn" data-id="${user.id}">
          <img src="./assets/icons/pencil.svg" alt="Edit" class="edit-icon"/>
        </button>
        <button class="delete-btn" data-id="${user.id}">
          <img src="./assets/icons/trash.svg" alt="Delete" class="delete-icon"/>
        </button>
      </td>`;
    tbody.appendChild(row);
  });

  addRowListeners();
}

function addRowListeners() {
  document.querySelectorAll(".edit-btn").forEach((btn) => {
    btn.addEventListener("click", () => {
      const id = btn.dataset.id;
      openModalEdit(id);
    });
  });

  document.querySelectorAll(".delete-btn").forEach((btn) => {
    btn.addEventListener("click", async () => {
      const id = btn.dataset.id;
      if (confirm("Are you sure you want to delete this user?")) {
        await deleteUser(id);
        const users = await getUsers();
        renderUsers(users);
      }
    });
  });
}

function formatDateToSave(dateStr) {
  const date = new Date(dateStr);
  const day = String(date.getDate()).padStart(2, "0");
  const month = date.toLocaleString("en-US", { month: "short" });
  const year = date.getFullYear();
  return `${day}-${month}-${year}`;
}


function isAuth() {
  const result = localStorage.getItem("Auth") || null;
  const resultBool = result === 'true'
  return resultBool;
}

function setupLoginForm() {
  const userAuth = "admin";
  const passAuth = "1234";

  const form = document.getElementById("login");

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const user = document.getElementById("user").value;
    const pass = document.getElementById("password").value;

    if (user === userAuth && pass === passAuth) {
      localStorage.setItem("Auth", "true");
      navigate("/home");
    } else {
      alert("usuario o contraseña son incorrectos");
    }
  });
}

const buttonCloseSession = document.getElementById("logout");
buttonCloseSession.addEventListener("click", () => {
  localStorage.setItem("Auth", "false");
  navigate("/login");
});

// SPA router
const routes = {
  "/": "./index.html",
  "/users": "./views/users.html",
  "/home": "./views/home.html",
  "/login": "./views/login.html",
};

document.body.addEventListener("click", (e) => {
  if (e.target.closest("[data-link]")) {
    e.preventDefault();
    const path = e.target.closest("[data-link]").getAttribute("href");
    navigate(path);
  }
});

async function navigate(pathname) {

  if (!isAuth()) { pathname = "/login"; }

  const route = routes[pathname];
  if (!route) return console.error("Invalid route");

  const html = await fetch(route).then((res) => res.text());

  const parser = new DOMParser();
  const doc = parser.parseFromString(html, "text/html");
  const newContent = doc.getElementById("content");
  const content = document.getElementById("content");

  content.innerHTML = newContent ? newContent.innerHTML : doc.body.innerHTML;

  history.pushState({}, "", pathname);

  // if (pathname === "/login") {
  //   const main = document.getElementById('content')
  //   const sidebar = document.getElementById("sidebar")
  //   sidebar.style.display = "none"
  //   setupLoginForm();
  // } else {
  //   sidebar.style.display = "flex"
  // }

  if (pathname === "/login") {
    const main = document.getElementById('content');
    const sidebar = document.getElementById("sidebar");
    sidebar.style.display = "none";
    main.classList.add("login-centered");
    setupLoginForm();
  } else {
    const main = document.getElementById('content');
    const sidebar = document.getElementById("sidebar");
    sidebar.style.display = "flex";
    main.classList.remove("login-centered");
  }

  // Update the .active class in the sidebar
  document.querySelectorAll(".sidebar nav ul li").forEach((li) => {
    const a = li.querySelector("a");
    if (a && a.getAttribute("href") === pathname) {
      li.classList.add("active");
    } else {
      li.classList.remove("active");
    }
  });

  if (pathname === "/users") {
    initApp();
  }
}

window.addEventListener("popstate", () => {
  navigate(location.pathname);
});

// navigate(location.pathname);
const initialPath = isAuth() ? location.pathname : "/login";
navigate(initialPath);

