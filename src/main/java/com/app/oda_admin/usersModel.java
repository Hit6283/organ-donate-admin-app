package com.app.oda_admin;

public class usersModel {

    private String name;

    private usersModel() {
    }

    usersModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
