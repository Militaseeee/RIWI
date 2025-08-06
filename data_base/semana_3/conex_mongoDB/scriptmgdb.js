import mongoose from 'mongoose';
import express, { json } from 'express';
import cors from 'cors';
import { user } from './streamhub.js';

const app = express();
app.use(cors());
app.use(json());

const dbConnection = async () => {
  try {
    const mongodbAtlas = "mongodb+srv://camilitaacosta2001:I2WYDcRsY3doAzUA@practice.3r6mgta.mongodb.net/streamhub?retryWrites=true&w=majority"
    await mongoose.connect(mongodbAtlas);
  } catch (error) {
    throw new Error("Error en la base de datos")
  }
}

app.get('/watchUser', async (req, res) => {
  try {
    await dbConnection();
    const users = await user.find();
    res.json(users);
  } catch (err) {
    res.status(500).json(err);
  }
});

app.listen(3000, () => console.log('Servidor corriendo en http://localhost:3000'));