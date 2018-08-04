package aop.platformServices;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/aop/app-config.xml");
        MyBean myBean1 = genericXmlApplicationContext.getBean("myBean1", MyBean.class);
        MyBean myBean2 = genericXmlApplicationContext.getBean("myBean2", MyBean.class);
        myBean1.execute();
        myBean2.execute();
    }
}
