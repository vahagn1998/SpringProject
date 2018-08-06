package aop.conposablePointcut;

import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class GetterMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().startsWith("get");
    }
}
