package app.model;

import app.util.Validation;

public abstract class User {
    private String name;
    private String email;
    private String rol;
    private String password;
    private String status;

    public User(String name, String email, String rol, String password, String status) {
//        this.name = name;
//        this.email = email;
//        this.rol = rol;
//        this.password = password;
//        this.status = status;

        this.setName(name);
        this.setEmail(email);
        this.setRol(rol);
        this.setPassword(password);
        this.setStatus(status);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Validation.checkEmail(email);
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Validation.checkPassword(password);
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String showProfile();

    public abstract String rolDescription();
}