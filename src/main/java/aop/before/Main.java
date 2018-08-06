package aop.before;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("aop");
        MessageWriter messageWriter = new MessageWriter();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(applicationContext.getBean("sim", SimpleBeforeAdvice.class));
        proxyFactory.setTarget(messageWriter);

        MessageWriter proxy = (MessageWriter) proxyFactory.getProxy();
        SecurityManager securityManager = new SecurityManager();

        Person vahag = new Person("Vahag", "123");
        securityManager.login(vahag);
        proxy.write(vahag);
        securityManager.logout();

        try {
            securityManager.login("aaa", "ddd");
            proxy.write(null);
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        } finally {
            securityManager.logout();
        }
    }
}
