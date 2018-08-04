package aop.platformServices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdviceWithoutInterface {
    public void beforeAdvice(JoinPoint joinPoint, int value){
        if(value != 100) {
            System.out.println(joinPoint.getTarget().getClass().getSimpleName());
            System.out.println(joinPoint.toString());
            System.out.println(joinPoint.toLongString());
            System.out.println(joinPoint.toShortString());
            System.out.println(joinPoint.getThis().getClass().getSimpleName());
            for (Object arg : joinPoint.getArgs()) {
                System.out.println(arg.getClass().getSimpleName());
            }
            System.out.println(joinPoint.getKind());
            System.out.println(joinPoint.getSignature().getName());
            System.out.println(joinPoint.getSignature().getDeclaringTypeName());
            System.out.println(joinPoint.getStaticPart().toString());
        }
    }

    public Object around(ProceedingJoinPoint joinPoint, int value) throws Throwable {
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(joinPoint.getSignature().getDeclaringTypeName());
        System.out.println(value);
        Object proceed = joinPoint.proceed(new Object[]{value});
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(joinPoint.getSignature().getDeclaringTypeName());
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(joinPoint.getSignature().getDeclaringTypeName());
        System.out.println(value);
        System.out.println(proceed);
        return proceed;
    }
}
