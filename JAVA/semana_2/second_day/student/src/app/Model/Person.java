package app.Model;

import javax.swing.*;

public class Person {

    private String name;
    private byte age;

    public Person(String name, byte age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    // This part is polymorphism

    public void showInformation() {
        JOptionPane.showMessageDialog(null,
                "Name: " + name + "\nAge: " + age);
    }




}
