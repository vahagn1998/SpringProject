package aop.aspectjAnnotation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/aop/app-config-aopannotation.xml");
        MyBean myBean = genericXmlApplicationContext.getBean("myBean", MyBean.class);
        myBean.execute();
    }
}
