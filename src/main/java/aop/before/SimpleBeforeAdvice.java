package aop.before;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service("sim")
public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    private SecurityManager securityManager;

    @Autowired
    public SimpleBeforeAdvice(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        Person person = securityManager.getLoggedOnPerson();
        if(person == null) {
            throw new SecurityException("nulla");
        } else if("vahag".equalsIgnoreCase(person.getLogin())) {
            System.out.println("toshniya");
        } else {
            System.out.println("Vahagy chi ape, es ova?" + person.getLogin());
            throw new SecurityException("davay");
        }
    }
}
