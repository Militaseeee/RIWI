import { getUsers, createUser, getRoles } from "./js/api";
import { setDateInputValidation, formatDateInput, formatDateToSave, setListeners } from "./js/form";
import { renderUsers } from "./js/usersTable";

export let counterId = 0;

// INIT APP
export async function initApp() {
  console.log("🚀 initApp executed from script.js");

  const users = await getUsers();

  if (users.length > 0) {
    const lastUser = users[users.length - 1];
    counterId = Number(lastUser.id) + 1;
  } else {
    counterId = 1;
  }

  renderUsers(users);
  setListeners();
}

function random14Digits() {
  return Math.floor(Math.random() * 9e13 + 1e13).toString();
}

function isAuth() {
  const result = localStorage.getItem("Auth") || null;
  const resultBool = result === 'true'
  return resultBool;
}

async function setupLoginForm() {
  
  const roles = await getRoles();

  const form = document.getElementById("login");
  form.addEventListener("submit", (e) => {
    e.preventDefault()

    const email = document.getElementById("user").value;
    const pass = document.getElementById("password").value
    let userVal = false;

    roles.forEach((showRole) => {
      const userEmail = showRole.email;

      const psw = showRole.password;
      
      if (email === userEmail && pass === psw) {
          localStorage.setItem("Auth", "true");
          localStorage.setItem("UserData", JSON.stringify(showRole));
          navigate("/home");
          userVal = true;
        } 
    });
    if (!userVal){
      alert("username or password is incorrect");
    }
  });
}

const buttonCloseSession = document.getElementById("logout");
buttonCloseSession.addEventListener("click", () => {
  localStorage.setItem("Auth", "false");
  localStorage.removeItem("UserData");
  navigate("/login");
});

// SPA routerB
const routes = {
  "/": "./index.html",
  "/users": "./views/users.html",
  "/home": "./views/home.html",
  "/login": "./views/login.html",
  "/add_student": "./views/add_student.html",
};

document.body.addEventListener("click", (e) => {
  if (e.target.closest("[data-link]")) {
    e.preventDefault();
    const path = e.target.closest("[data-link]").getAttribute("href");
    navigate(path);
  }
});

export async function navigate(pathname) {

  //Bloque para reemplazar los valores de los elementos HTML con su respectivo rol de usuario 
  const userData = await JSON.parse(localStorage.getItem("UserData"));
  let valRol = false;
  if (userData) {
    document.getElementById("nameUser").textContent = userData.name;
    document.getElementById("role").textContent = userData.role;
  }

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

  if (pathname === "/home") {
    
    const changeImg = document.getElementById('changePicture');

    if (userData.role === "User") {
      changeImg.src = './assets/img/user.jpg';
    } else if (userData.role === "Admin") {
      changeImg.src = './assets/img/profile-imagen.jpg';
    }
  }

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
    const changeImg = document.getElementById('changePicture');

    if (userData.role === "User") {
      changeImg.src = './assets/img/user.jpg';
    } else if (userData.role === "Admin") {
      changeImg.src = './assets/img/profile-imagen.jpg';
    }
    switch(userData.role){
      case 'Admin':
        await initApp()

        const deleteWrapper = document.getElementById('addUserBtn')
        deleteWrapper.style.display = 'block'

        break;
      case 'User':
        await initApp()
        const editButton = document.querySelectorAll('.edit-btn').forEach(btnEdit => {
          btnEdit.style.display = 'none';
        })
        const deleteButton = document.querySelectorAll('.delete-btn').forEach(deleteBtn => {
          deleteBtn.style.display = 'none';
        })

        const actionDelete = document.getElementById('actionDeleteUser');
        actionDelete.style.display = 'none';
        
      default:
        break
    }
  }

  if (pathname === "/add_student") {
    setDateInputValidation();

    const form = document.getElementById("addStudentForm");
    form.addEventListener("submit", async (e) => {
      e.preventDefault();

      const newStudent = {
        "id": counterId,
        "name": form.name.value,
        "email": form.email.value,
        "phone": form.phone.value,
        "enrollNumber": random14Digits(),
        "dateOfAdmission": formatDateToSave(form.dateOfAdmission.value),
      };
      counterId++;

      await createUser(newStudent);
      navigate("/users");
    });
  }
}

window.addEventListener("popstate", () => {
  navigate(location.pathname);
});

// navigate(location.pathname);
const initialPath = isAuth() ? location.pathname : "/login";
navigate(initialPath);