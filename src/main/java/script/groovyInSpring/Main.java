package script.groovyInSpring;

import org.joda.time.format.DateTimeFormat;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext applicationContext =
                new GenericXmlApplicationContext("classpath:../resources/groovyInSpring/app-context.xml");
        ContactService contactService = applicationContext.getBean("contactService", ContactService.class);

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstNarne("Vahagn");
        contact.setLastNarne("Kostandyan");
        contact.setBirthDate(DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime("1998-08-18"));

        contactService.applyRule(contact);
        System.out.println(contact.getAgeCategory());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contactService.applyRule(contact);
        System.out.println(contact.getAgeCategory());
    }
}
