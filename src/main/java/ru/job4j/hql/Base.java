package ru.job4j.hql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Base
 * Класс описывает базу вакансий.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "base")
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String candidateName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    public static Base of(String name) {
        Base base = new Base();
        base.candidateName = name;
        return base;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", candidateName='" + candidateName + '\''
                + ", vacancies=" + vacancies
                + '}';
    }
}
