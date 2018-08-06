package aop.dynamicPointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println();
        System.out.println("matches " + method.getName());
        System.out.println("matches " + targetClass.getSimpleName());
        System.out.println();
        return Objects.equals("foo", method.getName());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        Integer arg = (Integer) args[0];
        System.out.println();
        System.out.println("metches " + arg);
        System.out.println();
        return arg != 100;
    }

    @Override
    public ClassFilter getClassFilter() {
        return cls -> cls == SampleBean.class;
    }
}
