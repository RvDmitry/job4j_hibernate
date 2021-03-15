package ru.job4j.publish;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Book
 * Класс характеризует книгу.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "books")
public class Book {
    /**
     * Идентификатор книги.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Название книги.
     */
    private String name;

    /**
     * Фабричный метод, создает книгу.
     * @param name Название.
     * @return Книга.
     */
    public static Book of(String name) {
        Book book = new Book();
        book.name = name;
        return book;
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
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
