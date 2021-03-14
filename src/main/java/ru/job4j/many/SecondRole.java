package ru.job4j.many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Role
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "s_role")
public class SecondRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SecondUser> users = new ArrayList<>();

    public static SecondRole of(String name) {
        SecondRole role = new SecondRole();
        role.name = name;
        return role;
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

    public List<SecondUser> getUsers() {
        return users;
    }

    public void setUsers(List<SecondUser> users) {
        this.users = users;
    }

    public void addUser(SecondUser u) {
        this.users.add(u);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecondRole role = (SecondRole) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
