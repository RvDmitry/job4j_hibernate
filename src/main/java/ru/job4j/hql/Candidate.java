package ru.job4j.hql;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Candidate
 * Класс описывает кандидата.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "candidates")
public class Candidate {
    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Опыт.
     */
    private int experience;
    /**
     * Желаемая зарплата.
     */
    private int salary;

    /**
     * Фабрика создает кандидата.
     * @param name Имя.
     * @param experience Опыт.
     * @param salary Зарплата.
     * @return Кандидат.
     */
    public static Candidate of(String name, int experience, int salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
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
     * Метод возвращает имя.
     * @return Имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод задает имя.
     * @param name Имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает опыт.
     * @return Опыт.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Метод задает опыт.
     * @param experience Опыт.
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Метод возвращает зарплату.
     * @return Зарплата.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Метод задает зарплату.
     * @param salary Зарплата.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Candidate: "
                + "id= " + id
                + ", name= '" + name + '\''
                + ", experience= " + experience
                + ", salary= " + salary;
    }
}
