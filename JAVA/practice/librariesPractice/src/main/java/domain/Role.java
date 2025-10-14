package domain;

public class Role {
    private int idRole;
    private String typeRol;

    public Role() {
    }

    public Role(int idRole, String typeRol) {
        this.idRole = idRole;
        this.typeRol = typeRol;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getTypeRol() {
        return typeRol;
    }

    public void setTypeRol(String typeRol) {
        this.typeRol = typeRol;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", typeRol='" + typeRol + '\'' +
                '}';
    }
}
