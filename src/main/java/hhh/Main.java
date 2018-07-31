package hhh;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

import java.security.MessageDigest;

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
        System.out.println(p.getName() + " " + p.getAddress().getStreet() + " " + p.getAddress().getNumberOfFlat() + " " + p.getAge());
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        p.digest("hello");
        MessageDigestFactoryBean bean = (MessageDigestFactoryBean) a.getBean("&messageDigest");
        try {
            MessageDigest object = bean.getObject();
            System.out.println(object.digest("hello".getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        MessageDigest digester = (MessageDigest) a.getBean("digester");
        System.out.println(digester.digest("Hello World!".getBytes()));
//        try {
//            a.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
