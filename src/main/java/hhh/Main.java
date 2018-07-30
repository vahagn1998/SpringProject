package hhh;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext a = new GenericXmlApplicationContext();
        a.load("classpath:../../resources/main/spring/app-context.xml");
        a.refresh();
        Person p = (Person) a.getBean("person");
        System.out.println(p.getName());
    }
}
