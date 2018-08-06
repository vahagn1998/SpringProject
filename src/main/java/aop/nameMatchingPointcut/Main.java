package aop.nameMatchingPointcut;

import aop.staticPointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

public class Main {
    public static void main(String[] args) {
        NameBean nameBean = new NameBean();

        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.addMethodName("foo");
        nameMatchMethodPointcut.addMethodName("bar");
        Advisor advisor = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new SimpleAdvice());

        NameMatchMethodPointcutAdvisor advisor1 = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        advisor1.addMethodName("foo");
        advisor1.addMethodName("bar");

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor1);
        proxyFactory.setTarget(nameBean);

        NameBean proxy = (NameBean) proxyFactory.getProxy();
        proxy.foo();
        proxy.foo(4);
        proxy.bar();
        proxy.yup();
    }
}
