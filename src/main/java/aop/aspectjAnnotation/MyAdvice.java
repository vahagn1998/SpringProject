package aop.aspectjAnnotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(* foo*(int)) && args(value)")
    public void fooExecution(int value) {
    }

    @Pointcut("bean(myDependency*)")
    public void inMyDependency() {
    }

    @Before(value = "inMyDependency() && fooExecution(value)", argNames = "joinPoint,value")
    public void before(JoinPoint joinPoint, int value) {
        if(value != 100) {
            System.out.println(joinPoint.toString());
            System.out.println(value);
        }
    }

    @Around(value = "fooExecution(value) && inMyDependency()", argNames = "joinPoint,value")
    public Object around(ProceedingJoinPoint joinPoint, int value) throws Throwable {
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(value);
        return joinPoint.proceed(new Object[]{value});
    }
}
