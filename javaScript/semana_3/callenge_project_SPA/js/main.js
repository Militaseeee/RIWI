// json-server --watch db.json --port 3000

import { getUsers } from "./api.js";
import { renderUsers } from "./ui.js";
import { openModalCreate } from "./modal.js";

// Load all users on startup
document.addEventListener("DOMContentLoaded", async () => {
    const users = await getUsers();
    renderUsers(users);
});

// Open modal to create user
document.getElementById("addUserBtn").addEventListener("click", () => {
    openModalCreate();
});