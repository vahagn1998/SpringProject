package IoC.hhh;

import IoC.Events.Publisher;
import IoC.groove.Contact;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class Main {
//    public static void main(String[] args) {
//        GenericXmlApplicationContext a = new GenericXmlApplicationContext();
//        StopWatch stopWatch = new StopWatch();
//        a.load("classpath:../../resources/main/IoC/app-context.xml");
//        a.refresh();
//        stopWatch.start();
//        Person p = (Person) a.getBean("person");
//        p.getAddress().setStreet("esim");
//        for (Address address : p.getAddresses()) {
//            System.out.println(address.getNumberOfFlat() + " " + address.getStreet());
//        }
//        System.out.println(p.getName() + " " + p.getAddress().getStreet() + " " + p.getAddress().getNumberOfFlat() + " " + p.getAge());
//        stopWatch.stop();
//        System.out.println(stopWatch.getLastTaskTimeMillis());
//        p.digest("hello");
//        MessageDigestFactoryBean bean = (MessageDigestFactoryBean) a.getBean("&messageDigest");
//        try {
//            MessageDigest object = bean.getObject();
//            System.out.println(object.digest("hello".getBytes()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println();
//        MessageDigest digester = (MessageDigest) a.getBean("digester");
//        System.out.println(digester.digest("Hello World!".getBytes()));
////        try {
////            a.close();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }

//    public static void main(String[] args) {
//        GenericXmlApplicationContext a = new GenericXmlApplicationContext();
//        a.load("classpath:../../resources/main/IoC/app-context.xml");
//        a.refresh();
//        PropertyEditorBean builtInSample = a.getBean("builtInSample", PropertyEditorBean.class);
//        System.out.println(builtInSample.getaClass().getName());
//        System.out.println(builtInSample.getDate());
//        for (String s : builtInSample.getList()) {
//            System.out.println(s);
//        }
//        Properties properties = builtInSample.getProperties();
//        Set<String> strings = properties.stringPropertyNames();
//        for (String string : strings) {
//            System.out.println(string);
//        }
//        Person p = (Person) a.getBean("person");
//        System.out.println(p.getLocalDate().getDayOfMonth() + " " + p.getLocalDate().getYear());
//    }

//    public static void main(String[] args) {
//        GenericXmlApplicationContext a = new GenericXmlApplicationContext();
//        a.load("classpath:../../resources/main/IoC/app-context.xml");
//        a.refresh();
//        System.out.println(a.getMessage("hello", null, Locale.ENGLISH));
//        System.out.println(a.getMessage("hello", null, new Locale("am", "AM")));
//        System.out.println(a.getMessage("helloAra", new Object[]{"Esh", "Ez"}, new Locale("am", "AM")));
//        System.out.println(a.getMessage("helloAra", new Object[]{"Esh", "Ez"}, "chka", Locale.ENGLISH));
//
//        Publisher publisher = (Publisher) a.getBean("publisher");
//        publisher.publish("hello");
//    }

//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
//        File tempFile;
//        try {
//            tempFile = File.createTempFile("test", ".txt");
//            tempFile.deleteOnExit();
//            System.out.println(tempFile.getPath());
//            Resource res1 = applicationContext.getResource("file://" + tempFile.getPath());
//            displayinfo(res1);
//            /*Resource res2 = applicationContext.getResource("classpath:test.txt");
//            displayinfo(res2);*/
//            Resource res3 = applicationContext.getResource("http://google.com");
//            displayinfo(res3);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    private static void displayinfo(Resource res) throws Exception {
//        System.out.println(res.getClass());
//        System.out.println(res.getFilename());
//        System.out.println(res.getInputStream());
////        System.out.println(res.getURL().getContent());
//        System.out.println();
//    }
//
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        ConfigurableEnvironment environment = applicationContext.getEnvironment();
//        Address address = applicationContext.getBean("add", Address.class);
//        System.out.println(address.getStreet());
//        MutablePropertySources propertySources = environment.getPropertySources();
//        Map<String, Object> appMap = new HashMap<>() ;
//        appMap.put("application.home", "application_home");
//        propertySources.addLast(new MapPropertySource( "PROSPRING4_MAP", appMap));
//        System.out.println("user.home: "+ System.getProperty("user.home"));
//        System.out.println("JAVA_HOME: "+ System.getenv("JAVA_HOME"));
//        System.out.println("user.home: "+ environment.getProperty("user.home"));
//        System.out.println("JAVA_HOME: "+ environment.getProperty("JAVA_HOME"));
//        System.out.println("application.home: " + environment.getProperty("application.home"));
//    }

    public static void main(String[] args) {
        GenericGroovyApplicationContext applicationContext = new GenericGroovyApplicationContext("classpath:../../resources/main/IoC/Config.groove");
        Contact contact = applicationContext.getBean("contact", Contact.class);
        System.out.println(contact.getAge() + " " + contact.getFirstName() + " " + contact.getLastName());
    }
}
