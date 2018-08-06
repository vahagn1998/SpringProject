package aop.controlFlowPointcut;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        TestBean testBean = new TestBean();

        Pointcut controlFlowPointcut = new ControlFlowPointcut(Main.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(controlFlowPointcut, new SimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(testBean);
        proxyFactory.addAdvisor(advisor);
        TestBean proxy = (TestBean) proxyFactory.getProxy();
        proxy.foo();
        test(proxy);
    }

    private void test(TestBean proxy) {
        proxy.foo();
    }
}
