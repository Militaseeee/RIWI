package app.service;

import app.model.Client;

// Define operaciones de negocio (listar, bloquear, actualizar cliente)
public interface IUser {
    String listAllUsers();
    String blockUser(String email);
    String updateClient(Client client, String phone, String address);
}
