package app.service;

import app.model.User;
import app.security.IAuthenticable;
import app.model.Administrator;
import app.model.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceUser implements IUser, IAuthenticable {
    // Lista de usuarios creada en memoria (no se reasigna por ser final, pero sí se puede modificar)
    private final List<User> userList = new ArrayList<>();

    @Override
    public User createUser(String name, String email, String password, String rol) {
        User newUser; //  Variable que guardará la instancia del usuario (Admin o Client)
        if ("admin".equalsIgnoreCase(rol)) {
            newUser = new Administrator(name, email, "Admin", password, "active");
        } else {
            newUser = new Client(name, email, "Client", password, "active");
        }
        userList.add(newUser);
        return newUser; // Retorna el objeto
    }

    @Override
    public Optional<User> loginUser(String email, String password) {
        // Busca en la lista un usuario -> email (ignora mayús/minús) y password coincidan
        return userList.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password))
                // Devuelve el primero encontrado en un Optional (puede estar vacío si no existe)
                .findFirst();
        // return Optional.empty();
    }

    @Override
    public String listAllUsers() {
        if (userList.isEmpty()) {
            return "There are no registered users";
        }

        StringBuilder listado = new StringBuilder("--- LIST OF USERS ---\n");
        for (User user : userList) {
            listado.append("Name: ").append(user.getName())
                    .append(" | Rol: ").append(user.getRol())
                    .append(" | Status: ").append(user.getStatus())
                    .append("\n");
        }
        return listado.toString();
    }

    @Override
    public String blockUser(String email) {
        Optional<User> userToBlock = userList.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (userToBlock.isPresent()) {
            userToBlock.get().setStatus("blocked");
            return "The user " + email + " has been blocked";
        } else {
            return "User not found";
        }
    }

    @Override
    public String updateClient(Client client, String newTelephone, String newAddress) {
        try {
            client.setTelephone(newTelephone);
            client.setAddress(newAddress);
            return "Information updated successfully!";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }


}
