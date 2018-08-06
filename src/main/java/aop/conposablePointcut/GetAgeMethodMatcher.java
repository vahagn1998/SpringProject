package aop.conposablePointcut;

import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;
import java.util.Objects;

public class GetAgeMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return Objects.equals(method.getName(), "getAge");
    }
}
