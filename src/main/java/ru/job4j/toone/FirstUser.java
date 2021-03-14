package ru.job4j.toone;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class User
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "f_user")
public class FirstUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private FirstRole role;

    public static FirstUser of(String name, FirstRole role) {
        FirstUser user = new FirstUser();
        user.name = name;
        user.role = role;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FirstRole getRole() {
        return role;
    }

    public void setRole(FirstRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstUser user = (FirstUser) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
