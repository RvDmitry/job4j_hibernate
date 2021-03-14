package ru.job4j.toone;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Role
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "f_role")
public class FirstRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static FirstRole of(String name) {
        FirstRole role = new FirstRole();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirstRole role = (FirstRole) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
