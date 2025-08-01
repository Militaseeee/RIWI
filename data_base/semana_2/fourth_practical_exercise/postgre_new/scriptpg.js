import express, { json } from 'express';
import pkg from 'pg';
import cors from 'cors';

const { Pool } = pkg;

const app = express();
app.use(cors());
app.use(json());

// Configuración de PostgreSQL con datos reales
const db = new Pool({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
  port: process.env.DB_PORT,
  ssl: { rejectUnauthorized: false } // Supabase requiere SSL
});

// Obtener todos los empleados
app.get('/empleado2', async (req, res) => {
  try {
    const { rows } = await db.query('SELECT * FROM empleados');
    res.json(rows);
  } catch (err) {
    res.status(500).json(err);
  }
});

// Contar empleados
app.get('/count', async (req, res) => {
  try {
    const { rows } = await db.query('SELECT COUNT(*) AS numUsers FROM empleados');
    res.json(rows);
  } catch (err) {
    res.status(500).json(err);
  }
});

// Agregar empleado
app.post('/empleado2', async (req, res) => {
  const { nombre, apellido, departamento, edad, salario, fecha_ingreso } = req.body;
  try {
    await db.query('INSERT INTO public.empleados (nombre, apellido, departamento, edad, salario, fecha_ingreso) VALUES ($1, $2, $3, $4, $5, $6)', [nombre, apellido, departamento, edad, salario, fecha_ingreso]);
    res.json({ message: 'Empleado agregado' });
  } catch (err) {
    res.status(500).json(err);
  }
});

// Actualizar empleado
app.put('/empleados/:id', async (req, res) => {
  const { id } = req.params;
  const { nombre, apellido, departamento, edad, salario, fecha_ingreso } = req.body;
  try {
    const result = await db.query(
      'UPDATE public.empleados SET nombre = $1, apellido = $2, departamento = $3, edad = $4, salario = $5, fecha_ingreso = $6 WHERE id = $7',
      [nombre, apellido, departamento, edad, salario, fecha_ingreso, id]
    );
    if (result.rowCount === 0) return res.status(404).json({ message: 'Usuario no encontrado' });
    res.json({ message: 'Empleado actualizado' });
  } catch (err) {
    res.status(500).json(err);
  }
});

// Eliminar empleado
app.delete('/empleados/:id', async (req, res) => {
  const { id } = req.params;
  try {
    const result = await db.query('DELETE FROM empleados WHERE id = $1', [id]);
    if (result.rowCount === 0) return res.status(404).json({ message: 'Empleado no encontrado' });
    res.json({ message: 'Empleado eliminado' });
  } catch (err) {
    res.status(500).json(err);
  }
});

app.listen(3000, () => console.log('Servidor corriendo en http://localhost:3000'));