package com.cpfire.Crud_ResponseEntity.inputs;

// This DTO will be used to receive data when a user is created
public class UserInputDto {

    private String name;
    private String email;
    private String password;

    public UserInputDto() {

    }

    public UserInputDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
