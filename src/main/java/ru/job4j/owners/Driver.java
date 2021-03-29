package ru.job4j.owners;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Class Driver
 * Класс описывает владельца автомобиля.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "driver")
public class Driver {
    /**
     * Идентификатор водителя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Список автомобилей, которыми владеет водитель.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "driver_id", nullable = false, updatable = false) },
            inverseJoinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false) })
    private List<Car> cars;

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
     * Метод возвращает список автомобилей.
     * @return Список автомобилей.
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Метод задает список автомобилей.
     * @param cars Список автомобилей.
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Driver driver = (Driver) o;
        return id == driver.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
