const tareas = [];

function agregarTarea() {
  const input = document.getElementById("nuevaTarea");
  const texto = input.value.trim();

  if (texto === "") return;

  const tarea = {
    id: Date.now(),
    task: texto,
    isDone: false
  };

  console.log(tarea);

  tareas.push(tarea);
  input.value = "";
  mostrarTareas();
}

function marcarHecho(id) {
  for (let i = 0; i < tareas.length; i++) {
    if (tareas[i].id === id) {
      tareas[i].isDone = !tareas[i].isDone;
    }
  }
  mostrarTareas();
}

function eliminarTarea(id) {
  for (let i = 0; i < tareas.length; i++) {
    if (tareas[i].id === id) {
      tareas.splice(i, 1);
      break;
    }
  }
  mostrarTareas();
}

function mostrarTareas() {
  const lista = document.getElementById("listaTareas");
  lista.innerHTML = "";

  for (let i = 0; i < tareas.length; i++) {
    const tarea = tareas[i];
    const li = document.createElement("li");

    li.className = tarea.isDone ? "hecho" : "";
    li.innerHTML = `
      ${tarea.task}
      <button onclick="marcarHecho(${tarea.id})">
        ${tarea.isDone ? "Desmarcar" : "Hecho"}
      </button>
      <button onclick="eliminarTarea(${tarea.id})">Eliminar</button>
    `;

    lista.appendChild(li);
  }
}
