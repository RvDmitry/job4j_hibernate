package ru.job4j.manytomany.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class HbmRun
 *
 * @author Dmitry Razumov
 * @version 1
 */
public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            City one = City.of("Минск");
            City two = City.of("Москва");
            City three = City.of("Лондон");
            City four = City.of("Кейптаун");
            City five = City.of("Оттава");
            Human first = Human.of("Human Test");
            first.addCity(one);
            first.addCity(two);
            first.addCity(three);
            first.addCity(four);
            first.addCity(five);
            session.persist(first);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
