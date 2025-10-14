package domain;

// socio (It is the person who requests loans)
public class Member {
    private int idMember;
    private String fullName;
    private String email;
    private String phone;
    private boolean active;

    public Member() {
    }

    public Member(int idMember, String fullName, String email, String phone, boolean active) {
        this.idMember = idMember;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Member{" +
                "idMember=" + idMember +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                '}';
    }
}
