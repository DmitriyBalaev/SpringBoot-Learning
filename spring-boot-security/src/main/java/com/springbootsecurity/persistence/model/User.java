package com.springbootsecurity.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @Column(length = 60)
    private String password;

    public String email;

    private boolean enable;
    @ManyToMany
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, roles);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=")
                .append(id)
                .append(", userName=").append(username)
                .append(", email=").append(email)
                .append(", enable=").append(enable)
                .append(", roles=").append(roles)
                .append("]");
        return builder.toString();
    }
}

