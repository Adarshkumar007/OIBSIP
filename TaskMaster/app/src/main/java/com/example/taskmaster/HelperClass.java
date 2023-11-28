package com.example.taskmaster;

public class HelperClass {
    String name,email,username,password,online;
    public HelperClass() {
    }

    public HelperClass(String name, String email, String username, String password, String online) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.online = online;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
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
}
