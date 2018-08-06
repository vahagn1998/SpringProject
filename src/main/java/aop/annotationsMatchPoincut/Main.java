package aop.annotationsMatchPoincut;

import aop.staticPointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class Main {
    public static void main(String[] args) {
        SampleAnnotationBean sampleAnnotationBean = new SampleAnnotationBean();
        AnnotationMatchingPointcut annotationMatchingPointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(annotationMatchingPointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(sampleAnnotationBean);
        proxyFactory.addAdvisor(advisor);
        SampleAnnotationBean proxy = (SampleAnnotationBean) proxyFactory.getProxy();

        proxy.foo(5);
        proxy.bar();
    }
}
