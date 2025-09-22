package app.model;

public class Administrator extends User{

    public Administrator(String name, String email, String rol, String password, String status) {
        super(name, email, rol, password, status);
    }

    @Override
    public String showProfile() {
        return "Admin";
    }

    @Override
    public String rolDescription() {
        return "I have the power :v";
    }
}
