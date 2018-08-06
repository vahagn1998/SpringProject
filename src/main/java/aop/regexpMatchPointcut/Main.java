package aop.regexpMatchPointcut;

import aop.staticPointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class Main {
    public static void main(String[] args) {
        RegexpBean regexpBean = new RegexpBean();

        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPattern(".*foo*.");
        Advisor advisor = new DefaultPointcutAdvisor(jdkRegexpMethodPointcut, new SimpleAdvice());

        ProxyFactory proxyFactory =new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(regexpBean);
        RegexpBean proxy = (RegexpBean) proxyFactory.getProxy();

        proxy.foo1();
        proxy.foo2();
        proxy.bar();
    }
}
