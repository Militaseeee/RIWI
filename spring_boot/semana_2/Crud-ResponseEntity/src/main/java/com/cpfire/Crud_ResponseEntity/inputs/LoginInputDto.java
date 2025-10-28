package com.cpfire.Crud_ResponseEntity.inputs;

public class LoginInputDto {
    private String email;
    private String password;

    public LoginInputDto() {
    }

    public LoginInputDto(String email, String password) {
        this.email = email;
        this.password = password;
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
