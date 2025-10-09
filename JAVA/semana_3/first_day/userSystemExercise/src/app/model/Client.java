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
        if (telephone == null || telephone.trim().isEmpty()) {
            throw new IllegalArgumentException("The phone can't be empty");
        }
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("The address cannot be empty");
        }
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
