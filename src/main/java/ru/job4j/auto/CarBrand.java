package ru.job4j.auto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class CarBrand
 * Класс характеризует марку автомобиля.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "car_brand")
public class CarBrand {
    /**
     * Идентификатор марки.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Наименование марки.
     */
    private String name;
    /**
     * Список содержит модели, которые относятся к данной марке.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> models = new ArrayList<>();

    /**
     * Фабрика создает марку.
     * @param name Наименование марки.
     * @return Марка.
     */
    public static CarBrand of(String name) {
        CarBrand brand = new CarBrand();
        brand.name = name;
        return brand;
    }

    /**
     * Метод возвращает идентификатор марки.
     * @return Идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод задает идентификатор марки.
     * @param id Идентификатор.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возвращает наименование марки.
     * @return Наименование.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод задает наименование марки.
     * @param name Наименование.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает список моделей марки.
     * @return Список моделей.
     */
    public List<CarModel> getModels() {
        return models;
    }

    /**
     * Метод задает список моделей.
     * @param models Список моделей.
     */
    public void setModels(List<CarModel> models) {
        this.models = models;
    }

    /**
     * Метод добавляет модель в список.
     * @param model Модель.
     */
    public void addModel(CarModel model) {
        this.models.add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarBrand carBrand = (CarBrand) o;
        return id == carBrand.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
