package jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PersonWithDetailExtractor implements ResultSetExtractor<List<Person>> {
    @Override
    public List<Person> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Person> personMap = new HashMap<>();
        Person person;
        while (rs.next()) {
            long id = rs.getLong("person.id");
            person = personMap.get(id);
            if (person == null) {
                person = new Person();
                person.setId(id);
                person.setName(rs.getString("name"));
                person.setLastName(rs.getString("lastName"));
                person.setBirthday(rs.getDate("birthday").toLocalDate());
                person.setContacts(new HashSet<>());
                personMap.put(id, person);
            }
            long contactId = rs.getLong("contact.id");
            if (contactId > 0) {
                Contact contact = new Contact();
                contact.setId(contactId);
                contact.setPhone(rs.getString("phone"));
                contact.setPersonId(rs.getLong("person_id"));
                person.getContacts().add(contact);
            }
        }
        return new ArrayList<>(personMap.values());
    }
}
