package ru.job4j.lazy.auto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Class HbmRun
 * Класс создает марку и модели автомобилей к ней и сохраняет информацию о них в базе данных.
 * Затем загружает список марок из БД. И из первой выводит на консоль информацию о моделях.
 * @author Dmitry Razumov
 * @version 1
 */
public class HbmRun {
    /**
     * Главный метод программы.
     * @param args Параметры командной строки.
     */
    public static void main(String[] args) {
        List<Brand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Brand brand = create(Brand.of("BMW"), sf);
            create(Model.of("X1", brand), sf);
            create(Model.of("X2", brand), sf);
            create(Model.of("X3", brand), sf);
            create(Model.of("X4", brand), sf);
            create(Model.of("X5", brand), sf);
            list = findBrands(sf);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Model model : list.get(0).getModels()) {
            System.out.println(model);
        }
    }

    /**
     * Метод сохраняет информацию о марке или модели в БД.
     * @param model Объект, который нужно сохранить в БД.
     * @param sf Экземпляр конфигурации.
     * @param <T> Тип объекта.
     * @return Объект.
     */
    public static <T> T create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        session.close();
        return model;
    }

    /**
     * Метод возвращает список марок из БД.
     * @param sf Экземпляр конфигурации.
     * @return Список марок.
     */
    public static List<Brand> findBrands(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List list = session.createQuery("select distinct b from Brand b join fetch b.models")
                .list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
