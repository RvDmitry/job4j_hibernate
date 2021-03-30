package ru.job4j.hql;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class BookLibrary
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "library")
public class BookLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String publishingHouse;

    public static BookLibrary of(String name, String publishingHouse) {
        BookLibrary b = new BookLibrary();
        b.name = name;
        b.publishingHouse = publishingHouse;
        return b;
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

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookLibrary book = (BookLibrary) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookLibrary{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", publishingHouse='" + publishingHouse + '\''
                + '}';
    }
}
