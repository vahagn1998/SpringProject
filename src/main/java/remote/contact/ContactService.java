package remote.contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    Contact findByFirstNameAndLastName(String firstName, String lastName);
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
}
