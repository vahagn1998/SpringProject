package transaction;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/transaction/tx-jta-app-context.xml");
        ContactService contactService = genericXmlApplicationContext.getBean("contactService", ContactService.class);
        Contact contactA = new Contact();
        contactA.setFirsName("GGG");
        contactA.setLastName("hhh");
        contactService.save(contactA);
    }
}
