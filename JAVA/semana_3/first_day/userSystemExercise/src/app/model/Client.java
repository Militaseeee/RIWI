package app.model;

public class Client extends User{

    private String telephone;
    private String address;

    public Client(String name, String email, String rol, String password, String status, String telephone, String address) {
        super(name, email, rol, password, status);
        this.telephone = telephone;
        this.address = address;
    }

    public Client(String name, String email, String rol, String password, String status) {
        super(name, email, rol, password, status);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String showProfile() {
        return "User";
    }

    @Override
    public String rolDescription() {
        return "I am just a user :v";
    }
}
