package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Class HbmRun
 *
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
            create(Candidate.of("Ivan", 3, 100), sf);
            create(Candidate.of("Petr", 4, 150), sf);
            create(Candidate.of("Alex", 5, 200), sf);
            List<Candidate> candidates = findCandidates(sf);
            candidates.forEach(System.out::println);
            Candidate candidate = findCandidateById(1, sf);
            System.out.println(candidate);
            candidates = findCandidateByName("Petr", sf);
            candidates.forEach(System.out::println);
            updateCandidateById(1, 4, 150, sf);
            deleteCandidateById(3, sf);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Метод сохраняет информацию о кандидате в БД.
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
     * Метод возвращает список кандидатов из БД.
     * @param sf Экземпляр конфигурации.
     * @return Список кандидатов.
     */
    public static List<Candidate> findCandidates(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List list = session.createQuery("from Candidate")
                .list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    /**
     * Метод находит кандидата по его идентификатору.
     * @param id Идентификатор кандидата.
     * @param sf Экземпляр конфигурации.
     * @return Кандидат.
     */
    public static Candidate findCandidateById(int id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Candidate c where c.id = :fId");
        query.setParameter("fId", id);
        Candidate candidate = (Candidate) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return candidate;
    }

    /**
     * Метод находит кандидатов по имени.
     * @param name Имя кандидата.
     * @param sf Экземпляр конфигурации.
     * @return Кандидат.
     */
    public static List<Candidate> findCandidateByName(String name, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Candidate c where c.name = :fName");
        query.setParameter("fName", name);
        List candidates = query.list();
        session.getTransaction().commit();
        session.close();
        return candidates;
    }

    /**
     * Метод обновляет данные кандидата по его идентификатору.
     * @param id Идентификатор кандидата.
     * @param exp Опыт кандидата.
     * @param salary Зарплата кандидата.
     * @param sf Экземпляр конфигурации.
     */
    public static void updateCandidateById(int id, int exp, int salary, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("update Candidate c set "
                + "c.experience = :newExp, "
                + "c.salary = :newSalary "
                + "where c.id = :fId")
                .setParameter("newExp", exp)
                .setParameter("newSalary", salary)
                .setParameter("fId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Метод удаляет кандидата по его идентификатору.
     * @param id Идентификатор кандидата.
     * @param sf Экземпляр конфигурации.
     */
    public static void deleteCandidateById(int id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("delete from Candidate where id = :fId")
                .setParameter("fId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
