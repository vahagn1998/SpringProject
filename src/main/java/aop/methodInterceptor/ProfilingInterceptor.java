package aop.methodInterceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.DynamicIntroductionAdvice;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(invocation.getMethod().getName());
        Object object = invocation.proceed();
        stopWatch.stop();
        dumpInfo(invocation, stopWatch.getTotalTimeMillis());
        return object;
    }

    private void dumpInfo(MethodInvocation invocation, long totalTimeMillis) {
        Method method = invocation.getMethod();
        Object aThis = invocation.getThis();
        Object[] arguments = invocation.getArguments();
        System.out.println(method.getName());
        System.out.println(aThis.getClass().getSimpleName());
        for (Object argument : arguments) {
            System.out.println(argument);
        }
        System.out.println(totalTimeMillis);
    }
}
