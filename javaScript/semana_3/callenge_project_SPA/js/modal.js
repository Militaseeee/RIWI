import { createUser, getUsers, updateUser } from "./api.js";
import { renderUsers } from "./ui.js";

const modal = document.getElementById("userModal");
const closeBtn = document.querySelector(".close-btn");
const form = document.getElementById("userForm");
const modalTitle = document.getElementById("modalTitle");
const userIdField = document.getElementById("userId");

// Open modal for creating
export function openModalCreate() {
    modalTitle.textContent = "Add User";
    form.reset();
    userIdField.value = "";
    showModal();
}

// Open modal for editing
export async function openModalEdit(id) {
    modalTitle.textContent = "Edit User";
    const users = await getUsers();
    const user = users.find((u) => u.id == id);
    if (!user) return;

    userIdField.value = user.id;
    document.getElementById("name").value = user.name;
    document.getElementById("email").value = user.email;
    document.getElementById("phone").value = user.phone;
    document.getElementById("enrollNumber").value = user.enrollNumber;
    document.getElementById("dateOfAdmission").value = formatDateInput(user.dateOfAdmission);

    showModal();
}

// Show modal
function showModal() {
    modal.style.display = "flex";
}

// Close modal
function closeModal() {
    modal.style.display = "none";
}

closeBtn.addEventListener("click", closeModal);
window.addEventListener("click", (e) => {
    if (e.target === modal) closeModal();
});

// Form submit
form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const user = {
        name: form.name.value,
        email: form.email.value,
        phone: form.phone.value,
        enrollNumber: form.enrollNumber.value,
        dateOfAdmission: form.dateOfAdmission.value,
    };

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

// Helper: Format date to yyyy-mm-dd
function formatDateInput(dateStr) {
    const date = new Date(dateStr);
    return date.toISOString().split("T")[0];
}
