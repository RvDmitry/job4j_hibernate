package ru.job4j.hql;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Vacancy
 * Класс описывает вакансию.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "vacancies")
public class Vacancy {
    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Наименование.
     */
    private String name;

    /**
     * Фабрика создает вакансию.
     * @param name Наименование.
     * @return Вакансия.
     */
    public static Vacancy of(String name) {
        Vacancy vacancy = new Vacancy();
        vacancy.name = name;
        return vacancy;
    }

    /**
     * Метод возвращает идентификатор.
     * @return Идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод задает идентификатор.
     * @param id Идентификатор.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возвращает наименование.
     * @return Наименование.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод задает наименование.
     * @param name Наименование.
     */
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
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
