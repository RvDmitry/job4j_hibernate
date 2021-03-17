package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Class HbmRun
 *
 * @author Dmitry Razumov
 * @version 1
 */
public class HbmRun {

    public static void main(String[] args) {
        List<Category> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Category category = Category.of("Consulting");
            Task first = Task.of("Consultation on Hibernate", category);
            Task second = Task.of("Consultation on Spring", category);
            Task third = Task.of("Consultation on Servlet", category);
            session.save(category);
            session.save(first);
            session.save(second);
            session.save(third);
            list = session.createQuery(
                    "select distinct c from Category c join fetch c.tasks"
            ).list();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Task task : list.get(0).getTasks()) {
            System.out.println(task);
        }
    }
}
