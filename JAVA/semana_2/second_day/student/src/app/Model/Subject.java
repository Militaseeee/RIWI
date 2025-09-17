package app.Model;

public class Subject {
    private String nameSubject;
    private double note;

    public Subject(String nameSubject, double note) {
        this.nameSubject = nameSubject;
        this.note = note;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String statusNote() {
        return note >= 3.5 ? "Approved" : "Failed";
    }
}
