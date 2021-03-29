package ru.job4j.owners;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Engine
 * Класс описывает двигатель автомобиля.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "engine")
public class Engine {
    /**
     * Идентификатор двигателя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
