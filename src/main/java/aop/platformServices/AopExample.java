package aop.platformServices;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/aop/app-config-aopnames.xml");
        MyBean myBean = genericXmlApplicationContext.getBean("myBean", MyBean.class);
        myBean.execute();
    }
}
