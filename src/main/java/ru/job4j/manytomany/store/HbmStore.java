package ru.job4j.manytomany.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.manytomany.model.City;
import ru.job4j.manytomany.model.Human;

import java.util.ArrayList;
import java.util.List;

/**
 * Class HbmStore
 *
 * @author Dmitry Razumov
 * @version 1
 */
public class HbmStore implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(HbmStore.class.getName());

    private SessionFactory sf;

    private HbmStore() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sf = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    private static final class Lazy {
        private static final HbmStore INSTANCE = new HbmStore();
    }

    public static HbmStore instanceOf() {
        return Lazy.INSTANCE;
    }

    public void addNewHuman(Human human, String[] ids) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            for (String id : ids) {
                City city = session.find(City.class, Integer.parseInt(id));
                human.addCity(city);
            }
            session.save(human);
            session.getTransaction().commit();
            LOG.info("Человек {} сохранен.", human);
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
            LOG.error("Ошибка записи.", e);
        }
    }

    public List<City> allCities() {
        List<City> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("select c from City c", City.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
            LOG.error("Ошибка запроса.", e);
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        sf.close();
    }
}
