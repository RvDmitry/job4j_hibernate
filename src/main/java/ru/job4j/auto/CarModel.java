package ru.job4j.auto;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class CarModel
 * Класс характеризует модель автомобиля.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "car_model")
public class CarModel {
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
     * Фабрика создает модель.
     * @param name Наименование модели.
     * @return Модель.
     */
    public static CarModel of(String name) {
        CarModel model = new CarModel();
        model.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarModel carModel = (CarModel) o;
        return id == carModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
