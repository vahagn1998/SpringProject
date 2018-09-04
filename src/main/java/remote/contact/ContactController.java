package remote.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller("contact")
@RequestMapping(value = "/contact")
public class ContactController {

    private final ContactService contactService;
    private final RestTemplate restTemplate;

    @Autowired
    public ContactController(ContactService contactService, RestTemplate restTemplate) {
        this.contactService = contactService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/findAll")
    public String findAll(Model model) {
        List<Contact> all = contactService.findAll();
        model.addAttribute("contacts", all);
        return "findAll";
    }

    @GetMapping(value = "/listData")
    @ResponseBody
    public Contacts listData() {
        List<Contact> all = contactService.findAll();

        Contacts contacts = new Contacts();
        contacts.setContacts(all);

        return contacts;
    }

    @PostMapping(value = "/")
    @ResponseBody
    public Contact create(@RequestBody Contact contact) {
        contactService.save(contact);
        return contact;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Contact findContactById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Contact contact, @PathVariable Long id) {
        contactService.save(contact);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        Contact contact = contactService.findById(id);
        contactService.delete(contact);
    }
}
