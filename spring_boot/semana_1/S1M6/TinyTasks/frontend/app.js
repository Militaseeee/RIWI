const API = "http://localhost:8080/api/todos";
const list = document.getElementById("taskList");
const input = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");

async function loadTasks() {
  const res = await fetch(API);
  const todos = await res.json();
  list.innerHTML = "";
  todos.forEach(todo => {
    const li = document.createElement("li");
    li.className = "list-group-item d-flex justify-content-between align-items-center";
    li.innerHTML = `
      <span class="${todo.done ? 'text-decoration-line-through' : ''}">${todo.title}</span>
      <div>
        <button class="btn btn-sm btn-success me-2" onclick="toggleTask(${todo.id})">Toggle</button>
        <button class="btn btn-sm btn-danger" onclick="deleteTask(${todo.id})">Delete</button>
      </div>`;
    list.appendChild(li);
  });
}

addBtn.onclick = async () => {
  const title = input.value.trim();
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

async function deleteTask(id) {
  await fetch(`${API}/${id}`, { method: "DELETE" });
  loadTasks();
}

loadTasks();
