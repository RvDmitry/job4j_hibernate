package ru.job4j.many;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class User
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "s_user")
public class SecondUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static SecondUser of(String name) {
        SecondUser user = new SecondUser();
        user.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecondUser user = (SecondUser) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
