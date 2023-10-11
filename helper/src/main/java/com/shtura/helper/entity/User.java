package com.shtura.helper.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String email;
    private String password;

    @ElementCollection(targetClass = Role.class, fetch=FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "users_roles")
    @Column(name = "role")
    private List<Role> roles = new ArrayList<>();
    @Column(name = "banned")
    private boolean banned;

    public User() {
    }

    public User(String login, String email, String password) {
        super();
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(String login, String email, String password, List<Role> roles) {
        super();
        this.login = login;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return login.equals(user.login);
    }
    
    @Override
    public String toString() {
        return "**********   '" + this.id + "'   '" + this.login + "'   '" + this.email + "'   '" + this.password + "'   '" + this.roles + "'   '" + this.banned + "'  **********";
    }
}
