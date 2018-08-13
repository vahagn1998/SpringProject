package hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(HibernateConf.class);
        PersonDao personDao = applicationContext.getBean("personDao", PersonDao.class);
        Person per = new Person();
        per.setName("bnfhd");
        per.setLastName("fdffggh");
        per.setBirthday(new Date());
        Set<Contact> contacts = new HashSet<>();
        Contact contact1 = new Contact();
        contact1.setPhone("4515156");
        contact1.setPerson(per);
        contacts.add(contact1);
        per.setContacts(contacts);
        personDao.save(per);
        personDao.delete(per);
        System.out.println(per.getId());
        for (Person person : personDao.findAll()) {
            System.out.println(person.getName());
            System.out.println();
            person.setName("hghhh");
            personDao.save(person);
            System.out.println(person.getName());
        }
        for (Person person : personDao.findAllWithDetail()) {
            System.out.println(person.getName());
            for (Contact contact : person.getContacts()) {
                System.out.println(contact.getId());
                System.out.println(contact.getPhone());
            }
            System.out.println();
        }
    }
}
