package domain;

public class Status {
    private int idStatus;
    private String name;

    public Status() {
    }

    public Status(int idStatus, String name) {
        this.idStatus = idStatus;
        this.name = name;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "idStatus=" + idStatus +
                ", name='" + name + '\'' +
                '}';
    }
}
