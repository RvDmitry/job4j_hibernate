package ru.job4j.owners;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Class Car
 * Класс описывает автомобиль.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "car")
public class Car {
    /**
     * Идентификатор автомобиля.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Двигатель, которым оборудован данный автомобиль.
     */
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;
    /**
     * Список водителей, которые владеют данным автомобилем.
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false) },
            inverseJoinColumns = {
            @JoinColumn(name = "driver_id", nullable = false, updatable = false) })
    private List<Driver> drivers;

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
     * Метод возвращает двигатель.
     * @return Двигатель.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Метод задает двигатель.
     * @param engine Двигатель.
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Метод возвращает список водителей.
     * @return Список водителей.
     */
    public List<Driver> getDrivers() {
        return drivers;
    }

    /**
     * Метод задает список водителей.
     * @param drivers Список водителей.
     */
    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
