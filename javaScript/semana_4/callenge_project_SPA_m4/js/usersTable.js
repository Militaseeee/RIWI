import { deleteUser, getUsers } from "./api";
import { openModalEdit } from "./modal";

// Render users
export function renderUsers(users) {
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

export function addRowListeners() {
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