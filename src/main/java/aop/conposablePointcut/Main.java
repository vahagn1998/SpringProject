package aop.conposablePointcut;

import aop.controlFlowPointcut.SimpleBeforeAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.Pointcuts;

public class Main {
    public static void main(String[] args) {
        SampleBean sampleBean = new SampleBean();

        ComposablePointcut composablePointcut = new ComposablePointcut(ClassFilter.TRUE, new GetterMethodMatcher());
        System.out.println("1");
        SampleBean proxy = getProxy(composablePointcut, sampleBean);
        test(proxy);
        System.out.println("2");
        composablePointcut.union(new SetterMethodMatcher());
        proxy = getProxy(composablePointcut, sampleBean);
        test(proxy);
        System.out.println("3");
        composablePointcut.intersection(new GetAgeMethodMatcher());
        proxy = getProxy(composablePointcut, sampleBean);
        test(proxy);
//        Pointcuts.union();
    }

    private static SampleBean getProxy(ComposablePointcut composablePointcut, SampleBean target) {
        Advisor advisor = new DefaultPointcutAdvisor(composablePointcut, new SimpleBeforeAdvice());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        return (SampleBean) proxyFactory.getProxy();
    }

    private static void test(SampleBean sampleBean) {
        sampleBean.getAge();
        sampleBean.getName();
        sampleBean.setName();
    }
}
