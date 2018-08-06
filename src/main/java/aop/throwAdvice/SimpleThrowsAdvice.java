package aop.throwAdvice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class SimpleThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception e) {
        System.out.println(e.getMessage());
    }

    public void afterThrowing(Method method, Object[] arguments, Object target, RuntimeException e) {
        System.out.println(method.getName());
        for (Object argument : arguments) {
            System.out.println(argument);
        }
        System.out.println(target.getClass().getSimpleName());
        System.out.println(e.getMessage());
    }
}
