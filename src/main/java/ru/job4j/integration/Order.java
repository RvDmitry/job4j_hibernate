package ru.job4j.integration;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class Order
 * Класс описывает заказ.
 * @author Dmitry Razumov
 * @version 1
 */
public class Order {
    /**
     * Идентификатор.
     */
    private int id;
    /**
     * Наименование.
     */
    private String name;
    /**
     * Описание.
     */
    private String description;
    /**
     * Дата создания.
     */
    private Timestamp created;

    public Order() {
    }

    /**
     * Конструктор создает заказ.
     * @param id Идентификатор.
     * @param name Наименование.
     * @param description Описание.
     * @param created Дата создания.
     */
    public Order(int id, String name, String description, Timestamp created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    /**
     * Фабрика создает заказ.
     * @param name Наименование.
     * @param description Описание.
     * @return Заказ.
     */
    public static Order of(String name, String description) {
        Order o = new Order();
        o.name = name;
        o.description = description;
        o.created = new Timestamp(System.currentTimeMillis());
        return o;
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
     * Метод возвращает описание.
     * @return Описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод возвращает дату создания.
     * @return Дата создания.
     */
    public Timestamp getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
