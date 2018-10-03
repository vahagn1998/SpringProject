package remote;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import remote.contact.Contact;
import remote.contact.ContactService;
import remote.realTest.DataSets;
import remote.realTest.ServiceTestExecutionListener;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
@TestExecutionListeners({ServiceTestExecutionListener.class})
@ActiveProfiles("test")
public class ContactServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    ContactService contactService;

    @PersistenceContext
    private EntityManager em;

    @DataSets(setUpDataSet = "/META-INF/remote/ContactServiceImplTest.xls")
    @Test
    public void testFindAll() {
        List<Contact> result = contactService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @DataSets(setUpDataSet = "/META-INF/remote/ContactServiceImplTest.xls")
    @Test
    public void testFindByFirstNameAndLastName_1() {
        Contact result = contactService.findByFirstNameAndLastName("Chris", "Schaefer");
        Assertions.assertNotNull(result);
    }

    @DataSets(setUpDataSet = "/META-INF/remote/ContactServiceImplTest.xls")
    @Test
    public void testFindByFirstNameAndLastName_2() {
        Contact result = contactService.findByFirstNameAndLastName("Peter", "Chan");
        Assertions.assertNull(result);
    }

    @Test
    public void testAddContact() {
        deleteFromTables("CONTACT");

        Contact contact = new Contact();
        contact.setFirstName("Rod");
        contact.setLastName("Johnson");

        contactService.save(contact);
        em.flush();

        List<Contact> contacts = contactService.findAll();
        Assertions.assertEquals(1, contacts.size());
    }

    @Test
    public void testAddContactWithJSR349Error() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            deleteFromTables("CONTACT");

            Contact contact = new Contact();

            contactService.save(contact);
            em.flush();

            List<Contact> contacts = contactService.findAll();
            Assertions.assertEquals(0, contacts.size());
        });
    }
}
