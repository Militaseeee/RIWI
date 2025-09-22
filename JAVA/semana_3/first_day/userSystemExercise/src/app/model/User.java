package app.model;

public abstract class User {

    private String name;
    private String email;
    private String rol;
    private String password;
    private String status;

    public User(String name, String email, String rol, String password, String status) {
        this.name = name;
        this.email = email;
        this.rol = rol;
        this.password = rol;
        this.status = status;
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
        if (email == null || !email.contains("@") || email.indexOf("@") > email.lastIndexOf(".")) {
            throw new IllegalArgumentException("The email must be in a valid format");
        }
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
        if (password == null || password.length() > 6 ) {
            throw new IllegalArgumentException("The password must be major than six digits");
        }
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