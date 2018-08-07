package jdbc;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/jdbc/app-context.xml");
        JdbcDao jdbcDao = genericXmlApplicationContext.getBean("personDao", JdbcDao.class);
        System.out.println(jdbcDao.findNameById(1L));
        System.out.println(jdbcDao.countOfPersons());
//        Person person = new Person();
//        person.setName("Petros");
//        person.setLastName("Karapetyan");
//        person.setBirthday(LocalDate.of(1999,8,12));
//        Contact contact1 = new Contact();
//        contact1.setPhone("055986998");
//        Contact contact2 = new Contact();
//        contact2.setPhone("044589636");
//        person.setContacts(new HashSet<>(Arrays.asList(contact1, contact2)));
//        jdbcDao.insertWithDetail(person);
//
//        for (Person person1 : jdbcDao.findAllWithDetail()) {
//            System.out.println("1 "+person1.getId());
//            System.out.println("2 "+person1.getName());
//            System.out.println("3 "+person1.getLastName());
//            System.out.println("4 "+person1.getBirthday());
//            Set<Contact> contacts = person1.getContacts();
//            for (Contact contact : contacts) {
//                System.out.println("5 "+contact.getId());
//                System.out.println("6 "+contact.getPhone());
//                System.out.println("7 "+contact.getPersonId());
//            }
//        }
    }
}
