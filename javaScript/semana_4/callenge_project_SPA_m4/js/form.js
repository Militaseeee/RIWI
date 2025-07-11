import { counterId, navigate } from "../script";
import { createUser, getUsers, updateUser } from "./api";
import { closeModal } from "./modal";
import { renderUsers } from "./usersTable";

// FORM LOGIC
export function setListeners() {
  const addBtn = document.getElementById("addUserBtn");
  const closeBtn = document.querySelector(".close-btn");
  const modal = document.getElementById("userModal");
  const form = document.getElementById("userForm");




  //   // ⛔ BLOQUEAR FECHAS FUTURAS
  // const dateInput = document.getElementById("dateOfAdmission");
  // if (dateInput) {
  //   const today = new Date().toISOString().split("T")[0];
  //   dateInput.max = today;

  //   // BONUS: validación adicional si escriben manualmente una fecha futura
  //   dateInput.addEventListener("input", () => {
  //     if (dateInput.value > today) {
  //       alert("No puedes seleccionar una fecha futura.");
  //       dateInput.value = today; // forzar valor actual
  //     }
  //   });
  // }







  if (addBtn) {
    addBtn.addEventListener("click", () => navigate("/add_student"));
  }

  if (closeBtn) {
    closeBtn.addEventListener("click", closeModal);
  }

  if (modal) {
    window.addEventListener("click", (e) => {
      if (e.target === modal) closeModal();
    });
  }

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

export const formatDateToSave = (inputDate) => {
  const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
                  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

  const [year, month, day] = inputDate.split("-");
  const monthAbbr = months[parseInt(month, 10) - 1];

  return `${day}-${monthAbbr}-${year}`;
}

export function setDateInputValidation() {
  const dateInput = document.getElementById("dateOfAdmission");
  if (dateInput) {
    const today = new Date().toISOString().split("T")[0];
    dateInput.max = today;

    dateInput.addEventListener("input", () => {
      if (dateInput.value > today) {
        alert("You cannot select a future date");
        dateInput.value = today;
      }
    });
  }
}

export function formatDateInput(dateStr) {
  setDateInputValidation();
  const date = new Date(dateStr);
  return date.toISOString().split("T")[0]; // para que funcione en inputs type="date"
}