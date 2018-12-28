package com.dznfit.entity;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer permission;

    public Integer getId() {
        return id;
    }

    public User() {
    }

    public User(Integer id, String name, String password, Integer permission) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}