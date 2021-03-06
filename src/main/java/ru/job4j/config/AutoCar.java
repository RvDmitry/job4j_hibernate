package ru.job4j.config;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class Car
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "cars")
public class AutoCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String model;

    private Timestamp created;

    private String owner;

    public static AutoCar of(String model, Timestamp created, String owner) {
        AutoCar car = new AutoCar();
        car.model = model;
        car.created = created;
        car.owner = owner;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutoCar car = (AutoCar) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
