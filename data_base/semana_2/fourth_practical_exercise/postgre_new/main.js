const api = 'http://localhost:3000';

document.addEventListener('DOMContentLoaded', () => {
  cargarEmpleados();
  contarEmpleados();
});

const form = document.getElementById('empleadoForm');
const lista = document.getElementById('empleadoLista');
const contador = document.getElementById('contador');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const empleado = {
    nombre: form.nombre.value,
    apellido: form.apellido.value,
    departamento: form.departamento.value,
    edad: parseInt(form.edad.value),
    salario: parseFloat(form.salario.value),
    fecha_ingreso: form.fecha_ingreso.value
  };

  const id = form.id.value;

  if (id) {
    await fetch(`${api}/empleados/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(empleado)
    });
  } else {
    await fetch(`${api}/empleado2`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(empleado)
    });
  }

  form.reset();
  cargarEmpleados();
  contarEmpleados();
});

async function cargarEmpleados() {
  const res = await fetch(`${api}/empleado2`);
  const empleados = await res.json();
  lista.innerHTML = '';
  empleados.forEach(emp => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
        <td>${emp.nombre}</td>
        <td>${emp.apellido}</td>
        <td>${emp.departamento}</td>
        <td>${emp.edad}</td>
        <td>${emp.salario}</td>
        <td>${new Date(emp.fecha_ingreso).toLocaleDateString()}</td>
        <td>
            <button class="edit">✏️</button>
            <button class="delete">🗑️</button>
        </td>
    `;

    // Botón editar
    tr.querySelector('.edit').addEventListener('click', () => editarEmpleado(emp));

    // Botón eliminar
    tr.querySelector('.delete').addEventListener('click', () => eliminarEmpleado(emp.id));

    lista.appendChild(tr);
      });
}

function editarEmpleado(emp) {
  form.id.value = emp.id;
  form.nombre.value = emp.nombre;
  form.apellido.value = emp.apellido;
  form.departamento.value = emp.departamento;
  form.edad.value = emp.edad;
  form.salario.value = emp.salario;
  form.fecha_ingreso.value = emp.fecha_ingreso.split('T')[0];

  modal.style.display = 'block';
}

async function eliminarEmpleado(id) {
  if (confirm('¿Estás seguro de eliminar este empleado?')) {
    await fetch(`${api}/empleados/${id}`, {
      method: 'DELETE'
    });
    cargarEmpleados();
    contarEmpleados();
  }
}

async function contarEmpleados() {
  const res = await fetch(`${api}/count`);
  const data = await res.json();
  contador.textContent = data[0]?.numusers || 0;
}

// Modal logic
const modal = document.getElementById('modal');
const abrirModal = document.getElementById('abrirModal');
const cerrar = document.querySelector('.cerrar');

// Abrir modal
abrirModal.addEventListener('click', () => {
  form.reset();
  form.id.value = '';
  modal.style.display = 'block';
});

// Cerrar modal
cerrar.addEventListener('click', () => {
  modal.style.display = 'none';
});

// Cerrar si se hace clic fuera del contenido
window.addEventListener('click', (e) => {
  if (e.target == modal) {
    modal.style.display = 'none';
  }
});

// Cierra modal cuando termina guardar
form.addEventListener('submit', () => {
  modal.style.display = 'none';
});