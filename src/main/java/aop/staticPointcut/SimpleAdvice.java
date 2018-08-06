package aop.staticPointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("imna " + invocation.getMethod().getName());
        Object proceed = invocation.proceed();
        System.out.println("verj");
        return proceed;
    }
}
