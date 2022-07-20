package javabean_components;

public class User {
    private Long id;
    private String name;
    private String email;
    private Integer roleID;
    private Boolean isBlocked;

    public User() {
    }

    public User(Long id, String name, String email, Integer roleID, Boolean isBlocked) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roleID = roleID;
        this.isBlocked = isBlocked;
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

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
