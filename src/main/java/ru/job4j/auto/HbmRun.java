package ru.job4j.auto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class HbmRun
 * Класс создает марку и модели автомобилей к ней и сохраняет информацию о них в базе данных.
 * @author Dmitry Razumov
 * @version 1
 */
public class HbmRun {
    /**
     * Главный метод программы.
     * @param args Параметры командной строки.
     */
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            CarBrand brand = CarBrand.of("BMW");
            CarModel model = null;
            for (int i = 1; i <= 5; i++) {
                model = CarModel.of("X" + i);
                session.save(model);
                brand.addModel(session.load(CarModel.class, i));
            }
            session.save(brand);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
