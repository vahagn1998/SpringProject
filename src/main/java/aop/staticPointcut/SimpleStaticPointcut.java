package aop.staticPointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println();
        System.out.println("matches " + method.getName());
        System.out.println("matches " + targetClass.getSimpleName());
        System.out.println();
        return (Objects.equals("foo", method.getName()));
    }

    @Override
    public ClassFilter getClassFilter() {
        return (cls) -> {
            System.out.println();
            System.out.println("filter " + cls.getSimpleName());
            System.out.println();
            return cls == BeanOne.class;
        };
    }
}
