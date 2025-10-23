const API = "http://localhost:8080/api/task";
const list = document.getElementById("taskList");
const input = document.getElementById("taskInput");
const addButton = document.getElementById("addBtn");

async function loadTasks() {
  const res = await fetch(API);
  const tasks = await res.json();
  list.innerHTML = "";
  tasks.forEach(todo => {
    const li = document.createElement("li");
    li.className = "list-group-item d-flex justify-content-between align-items-center";
    li.innerHTML = `
      <span class="${todo.done ? 'text-decoration-line-through' : ''}">${todo.title}</span>
      <div>
        <button class="btn btn-sm btn-success me-2" onclick="toggleTask(${todo.id})">Toggle</button>
        <button class="btn btn-sm btn-danger" onclick="confirmDelete(${todo.id}, '${todo.title}')">Delete</button>
      </div>`;
    list.appendChild(li);
  });
}

addButton.onclick = async () => {
  const title = input.value.trim();
  if (!title) return alert("Please enter a task title.");

  const res = await fetch(API, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title })
  });

  if (res.ok) {
    input.value = "";
    loadTasks();
  } else {
    const err = await res.json();
    alert(err.error);
  }
};

async function toggleTask(id) {
  await fetch(`${API}/${id}/toggle`, { method: "PUT" });
  loadTasks();
}

async function confirmDelete(id, title) {
  const confirmed = confirm(`Are you sure you want to delete the task: "${title}"?`);
  if (confirmed) {
    await deleteTask(id);
  }
}

async function deleteTask(id) {
  const res = await fetch(`${API}/${id}`, { method: "DELETE" });
  if (res.ok) {
    loadTasks();
  } else {
    const err = await res.json();
    alert(err.error || "Error deleting task");
  }
}

loadTasks();
