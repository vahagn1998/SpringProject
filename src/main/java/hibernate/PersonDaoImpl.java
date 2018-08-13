package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("personDao")
public class PersonDaoImpl implements PersonDao {
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return sessionFactory.getCurrentSession().createQuery("select p from Person p", Person.class).list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAllWithDetail() {
        return sessionFactory.getCurrentSession().createNamedQuery("findAllWithDetail", Person.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById(Long id) {
        return null;
    }

    @Override
    public Person save(Person person) {
        sessionFactory.getCurrentSession().saveOrUpdate(person);
        return person;
    }

    @Override
    public void delete(Person contact) {
        sessionFactory.getCurrentSession().delete(contact);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
