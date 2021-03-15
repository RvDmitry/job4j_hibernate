package ru.job4j.publish;

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
            Book one = Book.of("Travel story");
            Book two = Book.of("Moto Sport");
            Author first = Author.of("Ivan");
            first.getBooks().add(one);
            first.getBooks().add(two);
            Author second = Author.of("Petr");
            second.getBooks().add(two);
            session.persist(first);
            session.persist(second);
            Author author = session.get(Author.class, 1);
            session.remove(author);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
