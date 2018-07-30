package hhh;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext a = new GenericXmlApplicationContext();
        StopWatch stopWatch = new StopWatch();
        a.load("classpath:../../resources/main/spring/app-context.xml");
        a.refresh();
        stopWatch.start();
        Person p = (Person) a.getBean("person");
        p.getAddress().setStreet("esim");
        for (Address address : p.getAddresses()) {
            System.out.println(address.getNumberOfFlat() + " " + address.getStreet());
        }
        System.out.println(p.getName() + " " + p.getAddress().getStreet() + " " + p.getAddress().getNumberOfFlat());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }
}
