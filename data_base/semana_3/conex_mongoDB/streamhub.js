import { Schema, model } from "mongoose";

const userSchema = new Schema({
  id: {
    type: String,
    required: true,
  },
  nombre: {
    type: String,
    required: true,
  },
  email: {
    type: String,
    required: true,
  },
  country: {
    type: String,
    required: true,
  },
  prefergenre: {
    type: Array,
  }
});

let user;
try {
  user = model("users");
} catch {
  user = model("users", userSchema);
}

export { user };