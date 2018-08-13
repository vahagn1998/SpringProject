package hibernate;

import java.util.List;

public interface PersonDao {
    List<Person> findAll();
    List<Person> findAllWithDetail();
    Person findById(Long id);
    Person save(Person contact);
    void delete(Person contact);
}
