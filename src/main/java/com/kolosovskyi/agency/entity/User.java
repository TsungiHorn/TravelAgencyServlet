package com.kolosovskyi.agency.entity;

import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Boolean isBlocked;

    public User() {
    }

    public User(Long id, String name, String email, String password, Role role, Boolean isBlocked) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.isBlocked = isBlocked;

    }

    public User(String name, String email, String password, Role role, Boolean isBlocked) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && Objects.equals(isBlocked, user.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, role, isBlocked);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
