package aop.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified, Savable {
    private boolean isModified = false;

    private final Map<Method, Method> methodCache = new HashMap<>();

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Method method = mi.getMethod();
        if(!isModified) {
            if(method.getName().startsWith("set") && method.getParameterCount() == 1){
                Method getter = getter(method);
                if(getter != null) {
                    Object oldVal = getter.invoke(mi.getThis());
                    Object newVal = mi.getArguments()[0];
                    isModified = !Objects.equals(newVal, oldVal);
                }
            }
        }
        return super.invoke(mi);
    }

    private Method getter(Method setter)  {
        Method getter = methodCache.get(setter);
        if(getter != null) {
            return getter;
        }
        String getterName = setter.getName().replace("set", "get");
        try {
            getter = setter.getDeclaringClass().getMethod(getterName);
            synchronized (methodCache) {
                methodCache.put(setter, getter);
            }
            return getter;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Override
    public boolean save() {
        return isModified = false;
    }
}
