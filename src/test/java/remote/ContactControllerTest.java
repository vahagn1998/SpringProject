package remote;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.ui.ExtendedModelMap;
import remote.contact.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactControllerTest {
    private final List<Contact> contacts = new ArrayList<>();

    @Before
    public void init() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("Vahagn");
        contact.setLastName("Kostandyan");
        contacts.add(contact);
    }

    @Test
    public void testList() {
        ContactService contactService = mock(ContactService.class);
        when(contactService.findAll()).thenReturn(contacts);

        ContactController contactController = new ContactController(contactService);

        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("contacts", contactController.listData());

        Contacts modelContacts = ((Contacts) uiModel.get("contacts"));
        assertEquals(1, modelContacts.getContacts().size());
    }

    @Test
    public void testCreate() {
        final Contact contact = new Contact();
        contact.setId(2L);
        contact.setLastName("Saruxanyan");
        contact.setFirstName("Vahan");

        ContactService contactService = mock(ContactService.class);
        when(contactService.save(contact)).thenAnswer((Answer<Contact>) invocation -> {
            contacts.add(contact);
            return contact;
        });

        ContactController contactController = new ContactController(contactService);
        Contact contact1 = contactController.create(contact);
        assertEquals(Long.valueOf(2), contact1.getId());
        assertEquals("Saruxanyan", contact1.getLastName());
        assertEquals("Vahan",contact1.getFirstName());
        assertEquals(2, contacts.size());
    }
}
