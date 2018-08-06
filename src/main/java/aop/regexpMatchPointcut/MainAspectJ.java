package aop.regexpMatchPointcut;

import aop.staticPointcut.SimpleAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MainAspectJ {
    public static void main(String[] args) {
        AspectjexpBean aspectjexpBean = new AspectjexpBean();

        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* foo*(Integer))");
        Advisor advisor = new DefaultPointcutAdvisor(aspectJExpressionPointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(aspectjexpBean);
        AspectjexpBean proxy = (AspectjexpBean) proxyFactory.getProxy();

        proxy.foo1();
        proxy.foo2(5);
        proxy.bar();
    }
}
