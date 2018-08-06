package aop.afterReturn;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
import java.util.Objects;

public class AfterReturningCheckKey implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if((target instanceof KeyGenerator) && Objects.equals(method.getName(), "getKey")) {
            long key = (Long) returnValue;
            if(key == KeyGenerator.WEAK_KEY) {
                throw new SecurityException("lav key chi");
            }
        }
    }
}
