package com.example.ADMS_praksa.dto;

public class RegistrationDTO {
    //ovo nam sve treba kada se registrujemo
    private String username;
    private String password;
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
