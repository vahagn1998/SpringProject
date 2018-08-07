package jdbc;

import java.util.List;

public interface PersonDao {
    String findLastNameById(Long id);
    String findNameById(Long id);
    List<Person> findAll();
    List<Person> findAllWithDetail();
    List<Person> findPersonsByName(String name);
    void update(Person person);
    void insert(Person person);
    void insertWithDetail(Person person);
}
