import { getUsers } from "./api";
import { formatDateInput } from "./form";

export async function openModalEdit(id) {
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

export function closeModal() {
  const modal = document.getElementById("userModal");
  modal.style.display = "none";
}