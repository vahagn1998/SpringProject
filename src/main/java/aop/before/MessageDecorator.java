package aop.before;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MessageDecorator implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("hello");
        Object proceed = invocation.proceed();
        invocation.getClass().asSubclass(invocation.getClass());
        System.out.println("!");
        return proceed;
    }
}
