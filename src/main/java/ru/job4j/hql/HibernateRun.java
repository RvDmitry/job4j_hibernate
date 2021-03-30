package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 * Class HibernateRun
 *
 * @author Dmitry Razumov
 * @version 1
 */
public class HibernateRun {
    /**
     * Главный метод программы.
     * @param args Параметры командной строки.
     */
    public static void main(String[] args) {
        Candidate rsl = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Base base = Base.of("Java Dev");
            Vacancy first = Vacancy.of("Vacancy One");
            Vacancy second = Vacancy.of("Vacancy Two");
            base.addVacancy(first);
            base.addVacancy(second);
            create(base, sf);
            Candidate candidate = Candidate.of("Ivan", 3, 100);
            candidate.setBase(base);
            create(candidate, sf);
            rsl = findCandidateById(1, sf);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl);
    }

    /**
     * Метод сохраняет информацию в БД.
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
     * Метод находит кандидата по его идентификатору.
     * @param id Идентификатор кандидата.
     * @param sf Экземпляр конфигурации.
     * @return Кандидат.
     */
    public static Candidate findCandidateById(int id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "select distinct c from Candidate c "
                        + "join fetch c.base b "
                        + "join fetch b.vacancies "
                        + "where c.id = :fId"
        );
        query.setParameter("fId", id);
        Candidate candidate = (Candidate) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return candidate;
    }
}
