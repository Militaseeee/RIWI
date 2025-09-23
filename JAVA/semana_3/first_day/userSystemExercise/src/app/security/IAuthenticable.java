package app.security;

import app.model.User;
import java.util.Optional;

public interface IAuthenticable {
    // Ahora el contrato requiere que se devuelvan los parámetros y el Usuario creado
    User createUser(String name, String email, String password, String rol);
    // El uso de Optional<User> es una práctica moderna para evitar devolver `null`
    Optional<User> loginUser(String email, String password);
}
