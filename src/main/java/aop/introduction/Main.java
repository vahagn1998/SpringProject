package aop.introduction;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        TargetBean targetBean = new TargetBean();
//
//        IsModifiedAdvisor isModifiedAdvisor = new IsModifiedAdvisor();
//        ProxyFactory proxyFactory = new ProxyFactory();
//        proxyFactory.setTarget(targetBean);
//        proxyFactory.addAdvisor(isModifiedAdvisor);
//        proxyFactory.setOptimize(true);
//        TargetBean proxy = (TargetBean) proxyFactory.getProxy();
        GenericXmlApplicationContext genericXmlApplicationContext =
                new GenericXmlApplicationContext("classpath:../../resources/main/aop/introduction.xml");
        TargetBean proxy = genericXmlApplicationContext.getBean("targetBean", TargetBean.class);
        TargetBean proxy1 = genericXmlApplicationContext.getBean("targetBean", TargetBean.class);

        proxy.setName("Vahag");
        System.out.println(proxy.getName());
        System.out.println(proxy1.getName());
        System.out.println(((IsModified) proxy).isModified());
        System.out.println(((IsModified) proxy1).isModified());

        proxy.setName("Davo");
        System.out.println(proxy.getName());
        System.out.println(proxy1.getName());
        System.out.println(((IsModified) proxy).isModified());
        System.out.println(((IsModified) proxy1).isModified());
        ((Savable) proxy).save();

        proxy.setName("Davo");
        System.out.println(proxy.getName());
        System.out.println(proxy1.getName());
        System.out.println(((IsModified) proxy).isModified());
        System.out.println(((IsModified) proxy1).isModified());
    }
}
