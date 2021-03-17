package ru.job4j.lazy.auto;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Model
 * Класс характеризует модель автомобиля.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "models")
public class Model {
    /**
     * Идентификатор модели.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Наименование модели.
     */
    private String name;
    /**
     * Марка, к которой относится модель.
     */
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    /**
     * Фабрика создает модель.
     * @param name Наименование модели.
     * @param brand Марка.
     * @return Модель.
     */
    public static Model of(String name, Brand brand) {
        Model model = new Model();
        model.name = name;
        model.brand = brand;
        return model;
    }

    /**
     * Метод возвращает идентификатор модели.
     * @return Идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод задает идентификатор модели.
     * @param id Идентификатор.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возвращает наименование модели.
     * @return Наименование.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод задает наименование модели.
     * @param name Наименование.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает марку.
     * @return Марка.
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Метод задет марку.
     * @param brand Марка.
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Model model = (Model) o;
        return id == model.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Model{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", brand=" + brand
                + '}';
    }
}
